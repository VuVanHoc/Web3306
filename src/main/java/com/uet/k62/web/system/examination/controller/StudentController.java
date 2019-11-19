package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {
	
	@GetMapping(value = {"/", "/login", "/logout"})
	public String index() {
		return "Signin";
	}
	
	@GetMapping(value = "/student/dashboard")
	public String showDashboard(){
		return "student/dashboard";
	}

	@GetMapping(value = "/student/courses")
	public String showCourses(){
		return "student/courses";
	}

	@GetMapping(value = "/student/courses/{courseId}/examDetail")
	public String showExamDetail(Model model, @PathVariable Integer courseId){
		model.addAttribute("courseId", courseId);
		return "student/examDetail";
	}

	@GetMapping(value = "/student/courses/{courseId}/exam")
	public String showExam(Model model, @PathVariable Integer courseId){
		model.addAttribute("courseId", courseId);
		return "student/exam";
	}

//	@GetMapping(value = "/authenticate")
//	public String authenticate(){
//		return "authenticate";
//	}
}
