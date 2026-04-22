package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	WelcomeController() {
		System.out.println("WelcomeController 객체 생성");
	}
	
	@GetMapping("/")
	public String home(Model model){
		model.addAttribute("greeting", "Welcome to BookMarket");
		model.addAttribute("strapline", "Welcome to Web Shopping Mall!");
		return "welcome";
	}
}
