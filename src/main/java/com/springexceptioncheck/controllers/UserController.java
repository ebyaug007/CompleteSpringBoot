package com.springexceptioncheck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	ResourceBundleMessageSource rbm;
	
	@GetMapping("hello")
	public String getHello()
	{
		return "hello";
	}
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2() {
		return rbm.getMessage("label.hello", null, LocaleContextHolder.getLocale());
		
	}
}
