package com.greatlearning.library.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.library.config.JwtService;
import com.greatlearning.library.entity.Customer;
import com.greatlearning.library.entity.Role;
import com.greatlearning.library.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	private CustomerRepository repo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {

		var user = Customer.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
				.build();
		repo.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repo.findByUsername(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
	
	public AuthenticationResponse adminAuthentication(RegisterRequest request) {
		var admin = Customer.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(Role.ADMIN).build();
		repo.save(admin);
		var jwtToken = jwtService.generateToken(admin);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
	
}
