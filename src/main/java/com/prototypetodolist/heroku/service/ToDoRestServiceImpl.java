package com.prototypetodolist.heroku.service;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prototypetodolist.heroku.entity.ToDo;
import com.prototypetodolist.heroku.handler.ClientStatusErrorHandler;
import com.prototypetodolist.heroku.rest.utils.HttpHeadersUtil;

@Service
public class ToDoRestServiceImpl implements ToDoRestService{
	
	private RestTemplate restTemplate;
	
	private String toDoRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public ToDoRestServiceImpl(RestTemplate restTemplate,
							  @Value("${todo.rest.url}") String url, HttpHeadersUtil httpHeadersUtil) {
		
		this.restTemplate = restTemplate;
		this.toDoRestUrl = url;
		
		restTemplate.setErrorHandler(new ClientStatusErrorHandler());
		logger.info("Loaded property: tooo.rest.url=" + toDoRestUrl);
	}
	
	@Override
	public List<ToDo> getUserToDos(HttpServletRequest request) {
		
		// construct http entity with authentication headers of current user session
		HttpEntity<?> httpEntity = HttpHeadersUtil.constructHttpEntity(request);
		
		ResponseEntity<List<ToDo>> responseEntity = restTemplate.exchange(toDoRestUrl+"/todos/users", HttpMethod.GET, httpEntity, 
				new ParameterizedTypeReference<List<ToDo>>() {});
		
		// get the list of todos from response
		List<ToDo> toDos = responseEntity.getBody();
		
		return toDos;
	}

	@Override
	public void saveUserToDo(ToDo toDo, HttpServletRequest request) {
		// construct http entity with authentication headers of current user session
		HttpEntity<?> httpEntity = HttpHeadersUtil.constructHttpEntity(toDo, request);
		
		int toDoId = toDo.getId();

		// make REST call to post or put toDo
		if (toDoId == 0) {
			// add todo
			ResponseEntity<ToDo> responseEntity = restTemplate.exchange(toDoRestUrl+"/todos/users", HttpMethod.POST, httpEntity, ToDo.class);
			logger.info("Saving ToDo: " + responseEntity.getBody());
		} else {
			// update ToDo
			ResponseEntity<ToDo> responseEntity = restTemplate.exchange(toDoRestUrl+"/todos/users", HttpMethod.PUT, httpEntity, ToDo.class);
			logger.info("Updating ToDo: " + responseEntity.getBody());
		}
	}

	@Override
	public ToDo getToDo(int id, HttpServletRequest request) {
		
		// construct http entity with authentication headers of current user session
		HttpEntity<?> httpEntity = HttpHeadersUtil.constructHttpEntity(request);
		
		ResponseEntity<ToDo> responseEntity = restTemplate.exchange(toDoRestUrl+"/todos/"+id, HttpMethod.GET, httpEntity, 
				ToDo.class);
		
		ToDo toDo;
		// get todo from response
		if (responseEntity.getStatusCode().series() == HttpStatus.Series.SUCCESSFUL) {
			toDo = responseEntity.getBody();
		} else {
			toDo = null;
		}
		
		return toDo;
	}

	@Override
	public void deleteToDo(int id, HttpServletRequest request) {
		
		// construct http entity with authentication headers of current user session
		HttpEntity<?> httpEntity = HttpHeadersUtil.constructHttpEntity(request);
		
		// make REST call to delete todo with this id
		restTemplate.exchange(toDoRestUrl+"/todos/users/"+id, HttpMethod.DELETE, httpEntity, String.class);
	}

	@Override
	public List<ToDo> getToDos(HttpServletRequest request) {
		// construct http entity with authentication headers of current user session
		HttpEntity<?> httpEntity = HttpHeadersUtil.constructHttpEntity(request);
		
		ResponseEntity<List<ToDo>> responseEntity = restTemplate.exchange(toDoRestUrl+"/todos", HttpMethod.GET, httpEntity, 
				new ParameterizedTypeReference<List<ToDo>>() {});
		
		// get the list of todos from response
		List<ToDo> toDos = responseEntity.getBody();
		return toDos;
	}

	
}
