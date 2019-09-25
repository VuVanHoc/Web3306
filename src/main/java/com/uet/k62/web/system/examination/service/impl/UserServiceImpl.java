package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;
import com.uet.k62.web.system.examination.model.entity.User;
import com.uet.k62.web.system.examination.repository.UserRepository;
import com.uet.k62.web.system.examination.service.UserService;
import com.uet.k62.web.system.examination.utils.Constant;
import com.uet.k62.web.system.examination.utils.RoleCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

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
            Date date = new Date(Long.parseLong(userFormRegistrationDTO.getBirthday()) * 1000);
            DateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_PATTERN);
            String dateString = formatter.format(date);
            newUser.setBirthday(formatter.parse(dateString));
        } catch (Exception e) {
            RestBody.error(e.getMessage());
        }

//        LOGGER.info(newUser.toString());
        userRepository.save(newUser);
        return RestBody.success(newUser);
    }

    @Override
    public RestBody getAllUser() {
        List<User> users = userRepository.findAllByDeletedIsFalse();
        return RestBody.success(users);
    }


}
