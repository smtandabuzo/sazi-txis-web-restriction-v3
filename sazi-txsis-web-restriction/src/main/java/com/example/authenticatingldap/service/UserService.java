package com.example.authenticatingldap.service;

import com.example.authenticatingldap.dto.TransmissionRegistrationDto;
import com.example.authenticatingldap.dto.UserLoginDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserService extends UserDetailsService{
	void saveUser(TransmissionRegistrationDto registrationDto);
}
