package com.prototypetodolist.heroku.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prototypetodolist.heroku.entity.ToDo;
import com.prototypetodolist.heroku.service.ToDoRestService;

@Controller
@RequestMapping("/todos")
public class ToDoController {
	
	@Autowired
	private ToDoRestService toDoService;
	
	@GetMapping("/userToDos")
	public String showUserToDoList(Model model, HttpServletRequest request) {
		// get todos from service
		List<ToDo> toDos = toDoService.getUserToDos(request);
		
		// add todos to the model
		model.addAttribute("todos", toDos);
		return "todolist";
	}
	
	@PostMapping("/saveUserToDo")
	public String saveUserToDo(@ModelAttribute("todo") ToDo toDo, HttpServletRequest request) {
		
		// save toDo using our service
		toDoService.saveUserToDo(toDo, request);
		
		return "redirect:/todos/userToDos";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("todoId") int id,
									Model model, HttpServletRequest request) {
		
		// get todo from our service
		ToDo toDo = toDoService.getToDo(id, request);
		
		// set ToDo as a model attribute to pre-populate the form
		model.addAttribute("todo", toDo);
		
		// send over to our form
		return "todo-form";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model, HttpServletRequest request) {
		
		ToDo toDo = new ToDo();
		
		model.addAttribute("todo", toDo);
		return "todo-form";
	}
	
	
	@GetMapping("/delete")
	public String deleteToDo(@RequestParam("todoId") int id, HttpServletRequest request) {
		
		// delete todo
		toDoService.deleteToDo(id, request);
		
		return "redirect:/todos/userToDos";
	}
	
	
	@GetMapping("/poweruser") 
	public String poweruserToDoList(Model model, HttpServletRequest request) {
		// get todos from service
		List<ToDo> toDos = toDoService.getToDos(request);
		
		// add todos to the model
		model.addAttribute("todos", toDos);
		return "todolist-poweruser";
	}
}
