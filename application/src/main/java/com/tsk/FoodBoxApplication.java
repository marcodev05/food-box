package com.tsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@ComponentScan(basePackages = {"com.tsk.*"})
@SpringBootApplication
@EntityScan(basePackages = {"com.tsk.domain.*"})
@EnableJpaRepositories(basePackages = {"com.tsk.*"})
public class FoodBoxApplication {
//"com.tsk.application", "com.tsk.exception","com.tsk.domain","com.tsk.serviceImpl","com.tsk.security", "com.tsk.serviceImpl", "com.tsk.tools"
	public static void main(String[] args) {
		SpringApplication.run(FoodBoxApplication.class, args);
	}

}
