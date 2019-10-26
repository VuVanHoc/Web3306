package com.uet.k62.web.system.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	
	@GetMapping(value = {"/", "/login", "/logout"})
	public String index() {
		return "index";
	}
}
