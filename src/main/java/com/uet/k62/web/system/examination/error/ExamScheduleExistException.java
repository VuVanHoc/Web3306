package com.uet.k62.web.system.examination.error;

public class ExamScheduleExistException extends RuntimeException {
    public ExamScheduleExistException(String msg){
        super(msg);
    }
}
