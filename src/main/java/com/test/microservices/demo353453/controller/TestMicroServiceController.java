package com.test.microservices.demo353453.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestMicroServiceController {
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
