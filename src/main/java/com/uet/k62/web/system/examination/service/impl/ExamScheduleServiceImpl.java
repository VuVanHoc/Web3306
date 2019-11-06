package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.ExamScheduleDTO;
import com.uet.k62.web.system.examination.model.entity.ExamSchedule;
import com.uet.k62.web.system.examination.repository.ExamScheduleRepository;
import com.uet.k62.web.system.examination.service.ExamScheduleService;
import com.uet.k62.web.system.examination.utils.Constant;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExamScheduleServiceImpl implements ExamScheduleService {
    private static final String NOT_FOUND_SCHEDULE = "Doesn't exist schedule of this course. Create a exam schedule first!!!";
    private ExamScheduleRepository examScheduleRepository;

    public ExamScheduleServiceImpl(ExamScheduleRepository examScheduleRepository) {
        this.examScheduleRepository = examScheduleRepository;
    }

    @Override
    public RestBody createExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            examSchedule = new ExamSchedule();
            //Create new exam schedule
            examSchedule.setCourseId(courseId);
            try {
                examSchedule.setStartTime(convertJsonTimestampToDate(examScheduleDTO.getStartTime()));
                examSchedule.setEndTime(convertJsonTimestampToDate(examScheduleDTO.getEndTime()));
            } catch (ParseException e) {
                return RestBody.error(e.getMessage());
            }
            examSchedule.setNote(examScheduleDTO.getNote());
            examScheduleRepository.save(examSchedule);
        } else {
            return RestBody.error("This course haved a exam schedule. Pls, update it!");
        }
        return RestBody.success(examSchedule);
    }

    @Override
    public RestBody updateExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            return RestBody.error(this.NOT_FOUND_SCHEDULE);
        } else {
            examSchedule.setCourseId(courseId);
            try {
                examSchedule.setStartTime(convertJsonTimestampToDate(examScheduleDTO.getStartTime()));
                examSchedule.setEndTime(convertJsonTimestampToDate(examScheduleDTO.getEndTime()));
            } catch (ParseException e) {
                return RestBody.error(e.getMessage());
            }
            examSchedule.setNote(examScheduleDTO.getNote());
            examSchedule.setUpdatedDate(new Date());
            examScheduleRepository.save(examSchedule);
        }
        return RestBody.success(examSchedule);
    }

    @Override
    public RestBody getExamSchedule(Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            return RestBody.error(this.NOT_FOUND_SCHEDULE);
        }
        return RestBody.success(examSchedule);
    }

    @Override
    public RestBody deleteExamSchedule(Integer courseId) {
        ExamSchedule examSchedule = examScheduleRepository.findFirstByCourseId(courseId);
        if (examSchedule == null) {
            return RestBody.error(this.NOT_FOUND_SCHEDULE);
        } else {
            examScheduleRepository.delete(examSchedule);
        }
        return RestBody.success("Deleted exam schedule");
    }

    private Date convertJsonTimestampToDate(String jsonTimestamp) throws ParseException {
        Date date = new Date(Long.parseLong(jsonTimestamp) * 1000);
        DateFormat formatter = new SimpleDateFormat(Constant.DATE_TIME_SCHEDULE_FORMAT);
        String dateString = formatter.format(date);
        return formatter.parse(dateString);
    }
}
