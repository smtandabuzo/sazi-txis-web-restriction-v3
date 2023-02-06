package com.example.authenticatingldap.controller;

import com.example.authenticatingldap.dto.UserLoginDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.service.TransmissionRolesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private TransmissionRolesServiceImpl transmissionRolesService;

    public AdminController(TransmissionRolesServiceImpl transmissionRolesService) {
        this.transmissionRolesService = transmissionRolesService;
    }

    @GetMapping
    public String admin () {
        return "admin";
    }

    @ModelAttribute("transmission_roles")
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @PostMapping
    public String admin (@ModelAttribute("transmission_roles") UserLoginDto userLoginDto) {
        TransmissionRoles trans = transmissionRolesService.findByAdUniqueNumber(userLoginDto.getAdUniqueNumber());
            if (trans != null && trans.getRole().equals("ADMIN") && trans.getBlocked().equals("0")) {
                return "login";
            }
            return "restrict";
    }
}
