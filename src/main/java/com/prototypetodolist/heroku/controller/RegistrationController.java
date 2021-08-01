package com.prototypetodolist.heroku.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prototypetodolist.heroku.entity.User;
import com.prototypetodolist.heroku.service.UserRestService;
import com.prototypetodolist.heroku.user.ToDoUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserRestService userService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// add support to trim empty strings to null
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model model) {
		
		model.addAttribute("ToDoUser", new ToDoUser());
		
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("ToDoUser") ToDoUser toDoUser,
			BindingResult bindingResult,
			Model model) {
		
		// form validation
		if(bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		String userName = toDoUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		logger.info("Fetched Username: " + existing);
		if (existing != null) {
			model.addAttribute("ToDoUser", new ToDoUser());
			model.addAttribute("registrationError", "User name already exists.");
			
			logger.warning("User name already exists.");
			return "registration-form";
		}
		
		// create user account
		userService.save(toDoUser);
		
		logger.info("Successfully create user: " + userName);
		
		return "redirect:/register/registrationConfirmation";
	}
	@GetMapping("/registrationConfirmation")
	public String registrationConfirmation() {
		return "registration-confirmation";
	}
	
}
