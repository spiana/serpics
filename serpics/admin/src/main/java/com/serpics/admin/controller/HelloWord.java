package com.serpics.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prova")
public class HelloWord {

	
    public String sayHello() {
        return "index";
    }
}
