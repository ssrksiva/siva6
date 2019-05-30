package com.test.micro.demo3455;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.test.microservices.demo353453.controller.TestMicroServiceController;

@SpringBootApplication
@ComponentScan(basePackageClasses=TestMicroServiceController.class)
public class Demo3455Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3455Application.class, args);
	}
	
}
