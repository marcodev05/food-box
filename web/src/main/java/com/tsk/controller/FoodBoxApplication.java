package com.tsk.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.tsk.*"})
@EntityScan(basePackages = {"com.tsk.*"})
@EnableJpaRepositories(basePackages = {"com.tsk.*"})
public class FoodBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodBoxApplication.class, args);
	}

}
