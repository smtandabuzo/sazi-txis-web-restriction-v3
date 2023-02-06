package com.example.authenticatingldap.controller;//package com.example.authenticatingldap;

import com.example.authenticatingldap.dto.TransmissionRegistrationDto;
import com.example.authenticatingldap.dto.UserLoginDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.repository.TransmissionRoleRepository;
import com.example.authenticatingldap.service.TransmissionRolesService;
import com.example.authenticatingldap.service.TransmissionRolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.*;

@Controller
@RequestMapping("/registration")
public class TransmissionRegistrationController {

	@Autowired
	private TransmissionRoleRepository transmissionRoleRepository;

	@Autowired
	private TransmissionRolesServiceImpl transmissionRolesService;

	public TransmissionRegistrationController(TransmissionRolesServiceImpl transmissionRolesService) {
		super();
		this.transmissionRolesService = transmissionRolesService;
	}
	
	@ModelAttribute("transmission_roles")
    public TransmissionRegistrationDto transmissionRegistrationDto() {
        return new TransmissionRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("transmission_roles") TransmissionRegistrationDto transmissionRegistrationDto, RedirectAttributes redirectAttributes) {
		transmissionRolesService.saveUser(transmissionRegistrationDto);
		redirectAttributes.addFlashAttribute("success","Registration successfull on TxSIS Web");
		return "redirect:/registration";
	}
}
