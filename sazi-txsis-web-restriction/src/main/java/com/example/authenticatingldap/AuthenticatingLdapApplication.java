package com.example.authenticatingldap;

import com.example.authenticatingldap.core.*;
import com.example.authenticatingldap.dto.TransmissionRegistrationDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.service.TransmissionRolesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
//public class AuthenticatingLdapApplication extends SpringBootServletInitializer {
public class AuthenticatingLdapApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticatingLdapApplication.class, args);
	}

	@Bean
	TokenStore tokenStore () {
		return new InMemoryTokenStore();
	}

	/**@Bean
	Sender sender (JavaMailSender aJavaMailSender) {
		return new EmailSender(aJavaMailSender);
	}**/

	@Bean
	Authenticator authenticator () {
		return new SpringSecurityAuthenicator(tokenStore());
	}

//	@Bean
//	TransmissionRolesService transmissionRolesService(){
//		return new TransmissionRolesService() {
//			@Override
//			public TransmissionRoles findByAdUniqueNumber(String adUniqueNumber) {
//				return null;
//			}
//
//			@Override
//			public String saveUser(TransmissionRegistrationDto transmissionRegistrationDto) {
//				return "";
//			}
//		};
//	}
//

}
