package com.ufc.br.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufc.br.mvp.service.SimpleEmailService;

@Controller
public class SimpleEmailController {
    
	@Autowired
	private SimpleEmailService service;
   
    @RequestMapping("/simpleemail")
    @ResponseBody
    String home() {
        try {
            service.sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
     
}