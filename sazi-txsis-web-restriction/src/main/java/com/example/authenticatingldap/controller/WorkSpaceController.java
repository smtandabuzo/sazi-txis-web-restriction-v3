package com.example.authenticatingldap.controller;//package com.example.authenticatingldap;

import com.example.authenticatingldap.dto.TransmissionBlockDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.repository.TransmissionRoleRepository;
import com.example.authenticatingldap.service.TransmissionRolesService;
import com.example.authenticatingldap.service.TransmissionRolesServiceImpl;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("/block")
public class WorkSpaceController {

    @Autowired
    private TransmissionRoleRepository transmissionRoleRepository;

    @Autowired
    private TransmissionRolesService transmissionRolesService;

    private TransmissionRolesServiceImpl transRolesServiceImpl;
    private TransmissionRoles transRoles;

   public WorkSpaceController(TransmissionRolesService transmissionRolesService, TransmissionRolesServiceImpl transRolesServiceImpl) {
//        super();
        this.transmissionRolesService = transmissionRolesService;
        this.transRolesServiceImpl = transRolesServiceImpl;
    }

    @ModelAttribute("transmission_roles")
    public TransmissionBlockDto transmissionBlockDto() {
        return new TransmissionBlockDto();
    }

    @GetMapping("/welcome")
    public String welcome () {
        return "welcome";
    }

    @GetMapping("/restrict")
    public String restrict () {
       return "restrict";
   }

    @GetMapping("/block/{id}")
    public String showBlockForm(Model model, @PathVariable("id") long id, RedirectAttributes redirAttrs) {
        TransmissionRoles user = transmissionRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        if(user.getBlocked() == null){
            user.setBlocked("1");
        }
       switch (user.getBlocked()) {
           case "0":
               user.setBlocked("1");
               redirAttrs.addFlashAttribute("message","The user has been blocked successfully");
               break;
           case "1":
           default:
                   user.setBlocked("0");
               redirAttrs.addFlashAttribute("message","The user has been unlocked successfully");
                   break;
       }

        transmissionRoleRepository.save(user);

        return "redirect:/view-users";
    }

    @GetMapping("view-users")
    public String viewUsers(Model model){
        List<TransmissionRoles> transmission_roles = transmissionRoleRepository.findAll();

            model.addAttribute("transmissionRolesList",transmission_roles);

       return "view-users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model,RedirectAttributes redirAttrs) {
        TransmissionRoles user = transmissionRoleRepository.findByUniqueNumber(id);
        if(user.getRole().equals( "USER")){
            transmissionRoleRepository.delete(user);
            redirAttrs.addFlashAttribute("message","The user has been deleted successfully");
        }else{
            redirAttrs.addFlashAttribute("error","Cannot delete Admin");
        }

        return "redirect:/view-users";
    }
    @GetMapping("update-user/{id}")
    public String updateTransmissionRoles(@PathVariable("id") Long id,
                                          Model model){
        var user = transmissionRoleRepository.findByUniqueNumber(id);
       // if(user.isPresent()){
            model.addAttribute("user",user);
        //}
            return "update-user";
    }

    @PostMapping("update-user-info/{id}")
    public String changeUserInfo(@PathVariable  Long id,
                                 @ModelAttribute("user") TransmissionRoles transmissionRoles,
                                 RedirectAttributes redirAttrs)  {
       var data = transmissionRoles;

       TransmissionRoles transRoles = transmissionRoleRepository.findByUniqueNumber(id);

        transRoles.setRole(transmissionRoles.getRole());
        transRoles.setAdFirstName(transmissionRoles.getAdFirstName());
        transRoles.setTxSisRole(transmissionRoles.getTxSisRole());
        transRoles.setAdSurname(transmissionRoles.getAdSurname());
        transmissionRoleRepository.save(transRoles);
        redirAttrs.addFlashAttribute("success","The user has been updated successful");
       return "redirect:/view-users";
    }
}
