package com.uet.k62.web.system.examination.error;

public class CourseTypeNotFoundException extends RuntimeException {
    public CourseTypeNotFoundException(String message){
        super(message);
    }
}
