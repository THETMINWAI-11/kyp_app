package com.example.khayayphyu.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.khayayphyu.dao")
@EntityScan("com.khayayphyu.entity")
@ComponentScan(basePackages = {"com.khayayphyu.controller", "com.khayayphyu.service", "com.khayayphyu.dao","com.khayayphyu.dto"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableAspectJAutoProxy
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}