package com.skt.apollo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	
	@RequestMapping("/main")
	public String main(
			HttpServletRequest request
			, Model model
		) throws Exception {
		
		return "main/main";
	}
	
}