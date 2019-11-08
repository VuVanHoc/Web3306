package com.uet.k62.web.system.examination.restcontroller;

import com.uet.k62.web.system.examination.config.JwtTokenProvider;
import com.uet.k62.web.system.examination.config.MyAuthenticationSuccessHandler;
import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.JwtAuthenticationResponse;
import com.uet.k62.web.system.examination.model.dtos.LoginRequest;
import com.uet.k62.web.system.examination.model.entity.CustomUserDetail;
import com.uet.k62.web.system.examination.repository.UserRepository;
import com.uet.k62.web.system.examination.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
	                      UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}
	
	@PostMapping(value = "/authenticate", produces = {"application/json"}, consumes = {"application/x-www-form-urlencoded"})
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpServletRequest request,
	                                          HttpServletResponse response) throws IOException, ServletException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword())
				);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		String jwt = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
		System.out.println("Auth"+authentication);
		
		response.addHeader(Constant.HEADER_TOKEN, Constant.BEARER_TOKEN + jwt);
		response.addHeader("Content-type", "application/json");
		AuthenticationSuccessHandler authenticationSuccessHandler = new MyAuthenticationSuccessHandler();
		authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}
