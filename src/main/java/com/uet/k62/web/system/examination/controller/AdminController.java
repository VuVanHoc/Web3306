package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.dtos.ExamResultFullDTO;
import com.uet.k62.web.system.examination.model.entity.Answer;
import com.uet.k62.web.system.examination.model.entity.Course;
import com.uet.k62.web.system.examination.model.entity.ExamResult;
import com.uet.k62.web.system.examination.model.entity.Question;
import com.uet.k62.web.system.examination.repository.CourseRepository;
import com.uet.k62.web.system.examination.repository.ExamResultRepository;
import com.uet.k62.web.system.examination.service.CourseService;
import com.uet.k62.web.system.examination.service.ExamResultService;
import com.uet.k62.web.system.examination.service.impl.AnswerServiceImpl;
import com.uet.k62.web.system.examination.service.impl.QuestionServiceImpl;
import com.uet.k62.web.system.examination.view.ExcelReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	QuestionServiceImpl questionService;
	@Autowired
	CourseService courseService;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	AnswerServiceImpl answerService;
	@Autowired
	ExamResultService examResultService;
	@Autowired
	ExamResultRepository examResultRepository;

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
		model.addAttribute("histories", examResultService.getAllResults().getData());
		return "/admin/history";
	}

	@GetMapping(value = "/report")
	public ModelAndView getExcel() {
		List<ExamResultFullDTO> examResults = (ArrayList<ExamResultFullDTO>) examResultService.getFullResults().getData();
		return new ModelAndView(new ExcelReportView(), "examResults", examResults);
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
	
	@GetMapping(value = "/courses/{course}/list-student")
	public String showStudentInCourse(Model model, @PathVariable("course") int course){
		model.addAttribute("courseId", course);
		Course course1 = courseRepository.findByIdAndDeletedIsFalse(course);
		
		model.addAttribute("course", course1);
		return "/admin/AddUserToCourse";
		
	}
	
	
	@GetMapping(value = "/question/detail")
	public String seeDetailQuestion(@RequestParam("id") int id, Model model){
		Question question = questionService.getAQuestionById(id);
		List<Answer> answers = answerService.getListAnswerOfQuestion(id);
		model.addAttribute("answers", answers);
		model.addAttribute("question", question);
		if(question.getImageUrl() != null) {
			model.addAttribute("showImg", true);
		} else {
			model.addAttribute("showImg", false);
		}
		return "/admin/DetailQuestion";
	}
	
	@GetMapping(value = "/questions/create-new-question")
	public String createNewQuestion(){
		
		return "/admin/newQuestion";
	}
	
}
