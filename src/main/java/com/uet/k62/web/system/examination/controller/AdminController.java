package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.entity.Question;
import com.uet.k62.web.system.examination.paging.PageConstant;
import com.uet.k62.web.system.examination.service.ExamResultService;
import com.uet.k62.web.system.examination.service.impl.QuestionServiceImpl;
import com.uet.k62.web.system.examination.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	QuestionServiceImpl questionService;
	@Autowired
	ExamResultService examResultService;
	
	@GetMapping(value = "/dashboard")
	public String showDashboard() {
//		System.out.println("ADMIN DASHBOARD");
		return "/admin/Layout";
	}
	
	@GetMapping(value = "/courses")
	public String showCourses() {
		return "/admin/Courses";
	}

	@GetMapping(value = "/courses/{course}/exam")
	public String showExamInCourse(Model model, @PathVariable("course") Integer course) {
		model.addAttribute("courseId", course);
		return "/admin/CreateExam";
	}
	
	@GetMapping(value = "/history")
	public String showHistory(Model model) {
		System.out.println(examResultService.getAllResults());
		model.addAttribute("histories", examResultService.getAllResults().getData());
		return "/admin/history";
	}
	
	@GetMapping(value = "/manage-user")
	public String manageUser() {
		return "/admin/UsersData";
	}
	
	@GetMapping(value = "/questions")
	public String manageQuestion(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum){
		model.addAttribute("questions", questionService.getAllQuestion(pageNum, 25) );
		return "/admin/Questions";
	}
	
	
}
