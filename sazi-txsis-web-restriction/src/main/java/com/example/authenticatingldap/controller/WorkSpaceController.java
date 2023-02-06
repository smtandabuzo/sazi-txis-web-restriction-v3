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
    public String showBlockForm(Model model,@PathVariable("id") long id) {
        TransmissionRoles user = transmissionRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        if(user.getBlocked() == null){
            user.setBlocked("1");
        }
       switch (user.getBlocked()) {
           case "0":
               user.setBlocked("1");
               break;
           case "1":
           default:
                   user.setBlocked("0");
                   break;
       }

        transmissionRoleRepository.save(user);
        return "redirect:/view-users?blocked";
    }

    @GetMapping("view-users")
    public String viewUsers(Model model){
        List<TransmissionRoles> transmission_roles = transmissionRoleRepository.findAll();

            model.addAttribute("transmissionRolesList",transmission_roles);

       return "view-users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        TransmissionRoles user = transmissionRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        transmissionRoleRepository.delete(user);
        return "redirect:/view-users?deleted";
    }
    @GetMapping("update-user/{id}")
    public String updateTransmissionRoles(@PathVariable("id") Long id,
                                          Model model){
        var user = transmissionRoleRepository.findById(id);
        if(user.isPresent()){
            model.addAttribute("user",user.get());
        }
            return "update-user";
    }

    @PostMapping("update-user-info/{id}")
    public String changeUserInfo(@PathVariable  Long id, @ModelAttribute("user") TransmissionRoles transmissionRoles)  {
       var data = transmissionRoles;

       TransmissionRoles transRoles = transmissionRoleRepository.findById(id).get();

        transRoles.setRole(transmissionRoles.getRole());
        transRoles.setAdFirstName(transmissionRoles.getAdFirstName());
        transRoles.setTxSisRole(transmissionRoles.getTxSisRole());
        transRoles.setAdSurname(transmissionRoles.getAdSurname());
        transmissionRoleRepository.save(transRoles);
       return "redirect:/view-users?updated";
    }
}