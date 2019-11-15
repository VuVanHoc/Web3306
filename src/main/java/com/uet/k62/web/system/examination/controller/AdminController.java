package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//	@GetMapping(value = "/courses/{courseId}/courseDetail")
//	public String showCourseDetail(@PathVariable Integer courseId, Model model) {
//		model.addAttribute("courseId", courseId);
//		return "/admin/courses/CourseDetail";
//	}

	@GetMapping(value = "/courses/{courseId}/student")
	public String showStudentInCourse(@PathVariable Integer courseId, Model model) {
		model.addAttribute("courseId", courseId);
		return "/admin/courses/CourseDetail";
	}

	@GetMapping(value = "/courses/{courseId}/exam")
	public String showExamInCourse(@PathVariable Integer courseId, Model model) {
		model.addAttribute("courseId", courseId);
		return "/admin/courses/ExamInCourse";
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
