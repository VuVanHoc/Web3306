package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
	
	@GetMapping(value = {"/", "/login", "/logout"})
	public String index() {
		return "Signin";
	}
	
	@GetMapping(value = "/student/dashboard")
	public String showDashboard(){
		System.out.println("STUDENT DASHBOARD");
		
		return "exam";
	}
	@GetMapping(value = "/authenticate")
	public String authenticate(){
		return "authenticate";
	}
}
