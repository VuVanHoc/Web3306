package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.error.CourseTypeNotFoundException;
import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CourseTypeRequestDTO;
import com.uet.k62.web.system.examination.model.entity.CourseType;
import com.uet.k62.web.system.examination.repository.CourseTypeRepository;
import com.uet.k62.web.system.examination.service.CourseTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    private CourseTypeRepository courseTypeRepository;
    public CourseTypeServiceImpl(CourseTypeRepository courseTypeRepository){
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public RestBody createCourseType(CourseTypeRequestDTO courseTypeRequestDTO) {
        CourseType courseType = new CourseType();
        BeanUtils.copyProperties(courseTypeRequestDTO, courseType);

        CourseType courseTypeSaved = courseTypeRepository.save(courseType);
        return RestBody.success(courseTypeSaved);
    }

    @Override
    public RestBody updateCourseType(CourseTypeRequestDTO courseTypeRequestDTO, BigInteger id) {
        CourseType courseType = courseTypeRepository.findByIdAndDeletedIsFalse(id.intValue());
        BeanUtils.copyProperties(courseTypeRequestDTO, courseType);
        courseTypeRepository.save(courseType);
        return RestBody.success(courseType);
    }

    @Override
    public RestBody deleteCourseType(BigInteger id) {
        CourseType courseType = courseTypeRepository.findByIdAndDeletedIsFalse(id.intValue());
        if(courseType == null){
            throw new CourseTypeNotFoundException("Course Type Not Found!");
        }
        courseType.setDeleted(true);
        courseTypeRepository.save(courseType);
        return RestBody.success("Deleted!");
    }

    @Override
    public RestBody getAllCourseTypes(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<CourseType> courseTypes = courseTypeRepository.findAllByDeletedIsFalse(paging);
        if(courseTypes.hasContent()){
            return RestBody.success(courseTypes.getContent());
        }else{
            return RestBody.success("Không có loại nào");
        }
    }

    @Override
    public RestBody getCourseType(BigInteger id) {
        CourseType courseType = courseTypeRepository.findByIdAndDeletedIsFalse(id.intValue());
        return RestBody.success(courseType);
    }
}
