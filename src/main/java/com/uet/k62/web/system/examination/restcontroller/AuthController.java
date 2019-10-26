package com.uet.k62.web.system.examination.restcontroller;

import com.uet.k62.web.system.examination.config.JwtTokenProvider;
import com.uet.k62.web.system.examination.model.dtos.JwtAuthenticationResponse;
import com.uet.k62.web.system.examination.model.dtos.LoginRequest;
import com.uet.k62.web.system.examination.model.entity.CustomUserDetail;
import com.uet.k62.web.system.examination.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword())
				);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		String jwt = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
//		System.out.println("Auth"+authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}
