package com.uet.k62.web.system.examination.error;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg){
        super(msg);
    }
}
