package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.error.CourseNotFoundException;
import com.uet.k62.web.system.examination.error.UserNotFoundException;
import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserDetailDTO;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;
import com.uet.k62.web.system.examination.model.entity.Course;
import com.uet.k62.web.system.examination.model.entity.User;
import com.uet.k62.web.system.examination.repository.UserRepository;
import com.uet.k62.web.system.examination.service.UserService;
import com.uet.k62.web.system.examination.utils.Constant;
import com.uet.k62.web.system.examination.utils.RoleCode;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
	
    @Autowired
	DozerBeanMapper dozerBeanMapper;
    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO) {

        User existUser = userRepository.findByUsername(userFormRegistrationDTO.getUsername());
        if (existUser != null) {
            return RestBody.error("User Already Exists!");
        }

        User newUser = new User();
        BeanUtils.copyProperties(userFormRegistrationDTO, newUser);
        newUser.setRoleId(RoleCode.STUDENT_ROLE);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        try {
            newUser.setBirthday(toDate(userFormRegistrationDTO.getBirthday()));
        } catch (ParseException e) {
            LOGGER.info(e.getMessage());
        }

        userRepository.save(newUser);

        return RestBody.success(newUser);
    }

    @Override
    public RestBody getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> users = userRepository.findAllByDeletedIsFalse(paging);

        if (users.hasContent()) {
            return RestBody.success(users.getContent());
        } else {

            return RestBody.success("Không có người dùng nào");
        }
    }

    @Override
    public RestBody getUser(Integer id) {
        User user = userRepository.findByIdAndDeletedIsFalse(id);
        if (user == null) {
            return RestBody.error("This account doesn't exist");
        }
        return RestBody.success(user);
    }

    @Override
    public RestBody deleteUser(Integer id) {
        User deleteUser = userRepository.findByIdAndDeletedIsFalse(id);
        if (deleteUser == null) {
            return RestBody.error("This account doesn't exist");
        }
        deleteUser.setDeleted(true);
        userRepository.save(deleteUser);
        return RestBody.success(null);
    }

    @Override
    public RestBody updateInfoUser(UserDetailDTO userDetailDTO, Integer id) {

        User updateUser = userRepository.findByIdAndDeletedIsFalse(id);
        if (updateUser == null) {
            return RestBody.error("This account doesn't exist");
        }

        updateUser.setFullName(userDetailDTO.getFullName());
        try {
            updateUser.setBirthday(toDate(userDetailDTO.getBirthday()));
        } catch (ParseException e) {
            LOGGER.info(e.getMessage());
        }
        updateUser.setEmail(userDetailDTO.getEmail());
        updateUser.setPhone(userDetailDTO.getPhone());
        updateUser.setPicture(userDetailDTO.getPicture());
        updateUser.setUpdatedDate(new Date());

        userRepository.save(updateUser);

        return RestBody.success(updateUser);
    }

    @Override
    public RestBody getCourses(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        Set<Course> courses =  user.getCourses();
        if(courses.isEmpty()){
            throw new CourseNotFoundException("Không có khóa học nào");
        }
        return RestBody.success(courses);
    }

    private Date toDate(String timeString) throws ParseException {
        Date date = new Date(Long.parseLong(timeString) * 1000);
        DateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_PATTERN);
        String dateString = formatter.format(date);
        return formatter.parse(dateString);
    }
}

