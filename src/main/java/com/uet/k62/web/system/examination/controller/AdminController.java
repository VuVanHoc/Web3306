package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@GetMapping(value = "/dashboard")
	public String showDashboard() {
//		System.out.println("ADMIN DASHBOARD");
		return "/admin/Layout";
	}
	
	@GetMapping(value = "/courses")
	public String showCourses() {
		return "/admin/Courses";
	}
	
	@GetMapping(value = "/history")
	public String showHistory() {
		return "/admin/history";
	}
	
	@GetMapping(value = "/manage-user")
	public String manageUser() {
		return "/admin/UsersData";
	}
	
	@GetMapping(value = "/questions")
	public String manageQuestion(){
		return "/admin/Questions";
	}
	
	
}
