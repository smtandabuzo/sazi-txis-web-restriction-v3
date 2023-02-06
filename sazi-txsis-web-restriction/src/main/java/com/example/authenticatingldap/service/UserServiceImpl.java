//package com.example.authenticatingldap.service;

//import com.example.authenticatingldap.model.Role;
//import com.example.authenticatingldap.model.User;
//import com.example.authenticatingldap.repository.UserRepository;
//import com.example.authenticatingldap.dto.UserRegistrationDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;

//import java.sql.*;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;

//@Service
//public class UserServiceImpl implements UserService{

//	private UserRepository userRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
//	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//		super();
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//	}

//	@Override
//	public User save(UserRegistrationDto registrationDto) {
//		User user = new User(registrationDto.getFirstName(),
//				registrationDto.getLastName(), registrationDto.getEmail(),
//				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
//		return userRepository.save(user);
//	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
//		User user = userRepository.findByEmail(username);
//		if(user == null) {
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

//	}

	/*@Override
	public String login(String adUniqueNumber){
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/txsis_registration",
					"root", "");

			PreparedStatement st = (PreparedStatement) connection
					.prepareStatement("Select txsis_role from TXSIS_WEB_RESTRICTION.transmission_roles where ad_unique_number=?");

			st.setString(1, adUniqueNumber);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return "index";
			} else {
				return "login";
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}
	
//}
