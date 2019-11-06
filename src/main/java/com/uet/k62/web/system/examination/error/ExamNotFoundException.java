package com.uet.k62.web.system.examination.error;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException(String msg){
        super(msg);
    }
}
