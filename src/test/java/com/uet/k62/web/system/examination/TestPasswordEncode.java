package com.uet.k62.web.system.examination;

import com.uet.k62.web.system.examination.config.JwtTokenProvider;
import com.uet.k62.web.system.examination.model.entity.CustomUserDetail;
import com.uet.k62.web.system.examination.model.entity.Role;
import com.uet.k62.web.system.examination.model.entity.User;
import com.uet.k62.web.system.examination.repository.RoleRepository;
import com.uet.k62.web.system.examination.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.util.Date;

public class TestPasswordEncode {
	
	public static void main(String[] args) {
//		ApplicationContext context = SpringApplication.run(ExaminationApplication.class);
//
//		UserRepository userRepository = context.getBean(UserRepository.class);
//		RoleRepository roleRepository = context.getBean(RoleRepository.class);
//		User user = userRepository.findByUsername("admin");
//		CustomUserDetail customUserDetail = new CustomUserDetail(user, roleRepository);
//		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
//		System.out.println("Cus" + customUserDetail);
//		System.out.println("Token: " + jwtTokenProvider.generateToken(customUserDetail));
//		System.out.println("username: " + jwtTokenProvider.getUsernameFromToken(jwtTokenProvider.generateToken(customUserDetail)));
//
		
		
	}
}
