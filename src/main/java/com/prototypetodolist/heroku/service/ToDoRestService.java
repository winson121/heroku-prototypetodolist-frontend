package com.prototypetodolist.heroku.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prototypetodolist.heroku.entity.ToDo;

public interface ToDoRestService {

	List<ToDo> getUserToDos(HttpServletRequest request);

	void saveUserToDo(ToDo toDo, HttpServletRequest request);

	ToDo getToDo(int id, HttpServletRequest request);

	void deleteToDo(int id, HttpServletRequest request);

	List<ToDo> getToDos(HttpServletRequest request);

}
