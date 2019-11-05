package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	
	@GetMapping(value = {"/", "/login", "/logout"})
	public String index() {
		return "Signin";
	}
	
	@GetMapping(value = "/student/dashboard")
	public String showDashboard(){
		return "/student/home";
	}

	@GetMapping(value = "/student/examDetail")
	public String showExamDetail(){
		return "/student/examDetail";
	}
//	@GetMapping(value = "/authenticate")
//	public String authenticate(){
//		return "authenticate";
//	}
}
