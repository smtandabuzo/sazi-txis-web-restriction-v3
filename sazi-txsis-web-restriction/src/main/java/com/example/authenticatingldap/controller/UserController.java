package com.example.authenticatingldap.controller;

import com.example.authenticatingldap.dto.UserLoginDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.service.TransmissionRolesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private TransmissionRolesServiceImpl transmissionRolesService;
    @GetMapping
    public String user () {
        return "user";
    }
    @ModelAttribute("transmission_roles")
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }
    @PostMapping
    public String user (@ModelAttribute("transmission_roles") UserLoginDto userLoginDto) {

        TransmissionRoles trans = transmissionRolesService.findByAdUniqueNumber(userLoginDto.getAdUniqueNumber());
        if (trans != null && trans.getRole().equals("USER") && trans.getBlocked().equals("0")) {
            return "index";
        }
        return "restrict";

    }
}
