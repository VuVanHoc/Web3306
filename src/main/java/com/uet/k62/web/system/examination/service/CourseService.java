package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CourseDTO;

public interface CourseService {
    RestBody createCourse(CourseDTO courseDTO);
    RestBody updateCourse(CourseDTO courseDTO, Integer courseId);
    RestBody deleteCourse(Integer courseId);
    RestBody getAllCourses(Integer pageNo, Integer pageSize);
    RestBody getCourse(Integer id);
}
