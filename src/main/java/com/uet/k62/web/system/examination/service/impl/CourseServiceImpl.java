package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.error.CourseNotFoundException;
import com.uet.k62.web.system.examination.error.CourseTypeNotFoundException;
import com.uet.k62.web.system.examination.error.UserNotFoundException;
import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CourseDTO;
import com.uet.k62.web.system.examination.model.dtos.UserIdListDTO;
import com.uet.k62.web.system.examination.model.entity.Course;
import com.uet.k62.web.system.examination.model.entity.User;
import com.uet.k62.web.system.examination.repository.CourseRepository;
import com.uet.k62.web.system.examination.repository.CourseTypeRepository;
import com.uet.k62.web.system.examination.repository.UserRepository;
import com.uet.k62.web.system.examination.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private CourseTypeRepository courseTypeRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository,
                             CourseTypeRepository courseTypeRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public RestBody createCourse(CourseDTO courseDTO) {
        if(courseTypeRepository.findByIdAndDeletedIsFalse(courseDTO.getTypeId()) != null){
            Course course = new Course();
            BeanUtils.copyProperties(courseDTO, course);
            courseRepository.save(course);
            return RestBody.success(course);
        } else{
            throw new CourseTypeNotFoundException("Not found course type");
        }

    }

    @Override
    public RestBody updateCourse(CourseDTO courseDTO, Integer courseId) {
        Course course = courseRepository.findByIdAndDeletedIsFalse(courseId);
        if (course == null) {
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
        if (course == null) {
            throw new CourseNotFoundException("This course doesn't exist");
        }
        course.setDeleted(true);
        courseRepository.save(course);
        return RestBody.success("Deleted course!");
    }

    @Override
    public RestBody getAllCourses(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Course> pagedResult = courseRepository.findAllByDeletedIsFalse(paging);
        if (pagedResult.hasContent()) {
            return RestBody.success(pagedResult.getContent());
        } else {
            throw new CourseNotFoundException("Không có khóa học nào");
        }
    }

    @Override
    public RestBody getCourse(Integer id) {
        Course course = courseRepository.findByIdAndDeletedIsFalse(id);
        if (course == null) {
            throw new CourseNotFoundException("Course not found");
        }
        return RestBody.success(course);
    }

    @Override
    public RestBody registerCourse(Integer courseId, UserIdListDTO dto) {
        Course course = courseRepository.findByIdAndDeletedIsFalse(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course not found");
        } else {
            ArrayList<Integer> userIds = dto.getUserIds();
            userIds.forEach(userId -> {
                User user = userRepository.findByIdAndDeletedIsFalse(userId);
                if (user == null) {
                    throw new UserNotFoundException("Not found user has username: " + user.getUsername() + ". Try again!");
                } else {
                    course.getUsers().add(user);
                }
            });
            courseRepository.save(course);
        }

        return RestBody.success("Đăng kí khóa học thành công");
    }

    @Override
    public RestBody leaveCourse(Integer courseId, UserIdListDTO dto) {
        Course course = courseRepository.findByIdAndDeletedIsFalse(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course not found");
        } else {
            ArrayList<Integer> userIds = dto.getUserIds();
            userIds.forEach(userId -> {
                User user = userRepository.findByIdAndDeletedIsFalse(userId);
                if (user == null) {
                    throw new UserNotFoundException("Not found user has username: " + user.getUsername() + ". Try again!");
                } else {
                    course.getUsers().forEach(item -> {
                        if (item.getId() == userId) {
                            course.getUsers().remove(item);
                        }
                    });
                }
            });
            courseRepository.save(course);
        }
        return RestBody.success("Deleted");
    }

    @Override
    public RestBody getTotal() {
        return RestBody.success(courseRepository.countAllByDeletedIsFalse());
    }
}
