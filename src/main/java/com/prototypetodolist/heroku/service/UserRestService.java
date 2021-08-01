package com.prototypetodolist.heroku.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.prototypetodolist.heroku.entity.User;
import com.prototypetodolist.heroku.user.ToDoUser;

public interface UserRestService extends UserDetailsService{
	
	User findByUserName(String userName);
	
	void save(ToDoUser toDoUser);
}
