package com.prototypetodolist.heroku.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.prototypetodolist.heroku.Exception.UserNotLoggedInException;

@ControllerAdvice
public class ToDoExceptionHandler {
	
	// Add an exception handler for ToDoNotFoundException
	
	@ExceptionHandler
	public String handleException(UserNotLoggedInException exc, HttpServletRequest request) {
		return "access-denied";
	}
}
