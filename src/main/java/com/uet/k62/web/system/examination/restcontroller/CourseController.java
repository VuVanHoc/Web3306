package com.uet.k62.web.system.examination.restcontroller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CourseDTO;
import com.uet.k62.web.system.examination.paging.PageConstant;
import com.uet.k62.web.system.examination.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @ApiOperation(value = "Get a page of course", response = RestBody.class)
    @GetMapping
    public ResponseEntity getListCourses(@RequestParam(defaultValue = PageConstant.PAGE_NO) Integer pageNo,
                                         @RequestParam(defaultValue = PageConstant.PAGE_SIZE) Integer pageSize){
        RestBody restBody = courseService.getAllCourses(pageNo, pageSize);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Get a course", response = RestBody.class)
    @GetMapping(value = "{id}")
    public ResponseEntity getCourse(@PathVariable Integer id){
        RestBody restBody = courseService.getCourse(id);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Create a new course", response = RestBody.class)
    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO){
        RestBody restBody = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Update course", response = RestBody.class)
    @PutMapping(value = "{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO){
        RestBody restBody = courseService.updateCourse(courseDTO, id);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Delete Course", response = RestBody.class)
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id){
        RestBody restBody = courseService.deleteCourse(id);
        return ResponseEntity.ok(restBody);
    }

}
