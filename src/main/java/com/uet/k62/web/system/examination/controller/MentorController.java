package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mentor")
public class MentorController {
	
	@GetMapping(value = "/dashboard")
	public String showDashboard(){
		System.out.println("MENTOR DASHBOARD");
		return "mentorDashboard";
	}
}
