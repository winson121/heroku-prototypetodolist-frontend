package com.prototypetodolist.heroku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("/")
	public String showLanding() {
		
		return "landing";
	}
	
	// add request mapping for main
	@GetMapping("/home")
	public String showHome(Model model) {
		
		return "home";
	}
	
}
