package com.uet.k62.web.system.examination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EntityScan("com.uet.k62.web.system.examination.model")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExaminationApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ExaminationApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.uet.k62.web.system.examination.restcontroller"))
				.paths(PathSelectors.any())
				.build();
	}
	
}
