package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CourseTypeRequestDTO;

import java.math.BigInteger;

public interface CourseTypeService {
    RestBody createCourseType(CourseTypeRequestDTO courseTypeRequestDTO);
    RestBody updateCourseType(CourseTypeRequestDTO courseTypeRequestDTO, BigInteger id);
    RestBody deleteCourseType(BigInteger id);
    RestBody getAllCourseTypes(Integer pageNo, Integer pageSize);
    RestBody getCourseType(BigInteger id);
}
