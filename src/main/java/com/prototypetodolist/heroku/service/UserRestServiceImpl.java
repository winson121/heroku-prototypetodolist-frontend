package com.prototypetodolist.heroku.service;

import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prototypetodolist.heroku.dto.UserDTO;
import com.prototypetodolist.heroku.entity.Role;
import com.prototypetodolist.heroku.entity.User;
import com.prototypetodolist.heroku.handler.ClientStatusErrorHandler;
import com.prototypetodolist.heroku.user.ToDoUser;

@Service
public class UserRestServiceImpl implements UserRestService{
	
	private RestTemplate restTemplate;
	
	private String userRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// constructor injection for restTemplate
	@Autowired
	public UserRestServiceImpl(RestTemplate restTemplate , @Value("${user.rest.url}") String url) {
		this.restTemplate = restTemplate;
		this.userRestUrl = url;
		
		restTemplate.setErrorHandler(new ClientStatusErrorHandler());
		logger.info("Loaded property:  tdl.rest.url=" + userRestUrl);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public User findByUserName(String userName) {
		
		User user;
		// make REST call
		logger.info("Calling : " + userRestUrl+"/user/login/"+userName);
		ResponseEntity<User> responseEntity = 
				restTemplate.getForEntity(userRestUrl + "/user/login/" + userName, User.class);
		
		user = responseEntity.getBody();
		
		
		return user;
	}

	@Override
	public void save(ToDoUser toDoUser) {
		UserDTO userDto = new UserDTO();
		// assign user details to the user object
		userDto.setUsername(toDoUser.getUserName());
		userDto.setPassword(passwordEncoder.encode(toDoUser.getPassword()));
		userDto.setFirstName(toDoUser.getFirstName());
		userDto.setLastName(toDoUser.getLastName());
		userDto.setEmail(toDoUser.getEmail());
		
		// save user in the backend server by calling post method
		restTemplate.postForEntity(userRestUrl + "/user/signup", userDto, ResponseEntity.class);
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
