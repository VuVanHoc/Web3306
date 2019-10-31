package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.error.CourseNotFoundException;
import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CourseDTO;
import com.uet.k62.web.system.examination.model.entity.Course;
import com.uet.k62.web.system.examination.repository.CourseRepository;
import com.uet.k62.web.system.examination.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public RestBody createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        courseRepository.save(course);
        return RestBody.success(course);
    }

    @Override
    public RestBody updateCourse(CourseDTO courseDTO, Integer courseId) {
        Course course = courseRepository.findByIdAndDeletedIsFalse(courseId);
        if(course == null){
            throw new CourseNotFoundException("Course not found");
        }
        BeanUtils.copyProperties(courseDTO, course);
        course.setUpdatedDate(new Date());
        courseRepository.save(course);
        return RestBody.success(course);
    }

    @Override
    public RestBody deleteCourse(Integer courseId) {
        Course course = courseRepository.findByIdAndDeletedIsFalse(courseId);
        if(course == null){
            throw new CourseNotFoundException("This course doesn't exist");
        }
        course.setDeleted(true);
        courseRepository.save(course);
        return RestBody.success("Deleted course!");
    }

    @Override
    public RestBody getAllCourses() {
//        List<Course> courses = courseRepository.findAllByDeletedIsFalse();
        Page
        if(courses == null){
            
        }
        return null;
    }

    @Override
    public RestBody getCourse(Integer id) {
        return null;
    }
}
