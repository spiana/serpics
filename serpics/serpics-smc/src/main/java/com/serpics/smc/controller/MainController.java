package com.serpics.smc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/login" , method = RequestMethod.GET)
    public String sayHello() {
        return "login";
    }
	
	@RequestMapping(value="/smc/login" , method = RequestMethod.GET)
    public String smcLogin() {
        return "login";
    }
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public String runsmc(){
		return "redirect:smc";
	}
}
