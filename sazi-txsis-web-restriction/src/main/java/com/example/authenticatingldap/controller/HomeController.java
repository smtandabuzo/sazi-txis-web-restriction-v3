package com.example.authenticatingldap.controller;

import com.example.authenticatingldap.dto.UserLoginDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.service.TransmissionRolesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class HomeController {

	private TransmissionRolesServiceImpl transmissionRolesService;

	public HomeController (TransmissionRolesServiceImpl transmissionRolesService) {
		this.transmissionRolesService = transmissionRolesService;
	}
	@ModelAttribute("transmission_roles")
	public UserLoginDto userLoginDto() {
	    return new UserLoginDto();
	}

	private int id;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = Optional.ofNullable(this.id).orElse(id);
	}


	@GetMapping
	public String login () {
		return "login";
	}
	@PostMapping
	public String login (@ModelAttribute("transmission_roles") UserLoginDto userLoginDto) {
		TransmissionRoles trans = transmissionRolesService.findByAdUniqueNumber(userLoginDto.getAdUniqueNumber());
		if (trans != null ) {
			if(trans.getRole().equalsIgnoreCase("ADMIN") && trans.getBlocked().equals("0")) {
				return "index";
			}else if(trans.getRole().equals("USER") && trans.getBlocked().equals("0")){
				return "index";
			}
				return "restrict";
		}
		return "welcome";
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/login.html";
	}

	/** Error page. */
	@RequestMapping("/error.html")
	public String error(HttpServletRequest request, Model model) {
		model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("<ul>");
		while (throwable != null) {
			errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
			throwable = throwable.getCause();
		}
		errorMessage.append("</ul>");
		model.addAttribute("errorMessage", errorMessage.toString());
		return "error";
	}
}
