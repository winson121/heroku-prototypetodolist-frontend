package com.prototypetodolist.heroku.rest.utils;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.prototypetodolist.heroku.entity.User;

@Component
public class HttpHeadersUtil {
	
	public static HttpHeaders getAuthHeaders(String username, String password) {
		String userCredentials = username+":"+password;
		String encodedCredentials = new String(Base64.encodeBase64(userCredentials.getBytes()));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Basic " + encodedCredentials);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return httpHeaders;
	}
	
	public static HttpEntity<?> constructHttpEntity(HttpServletRequest request) {
		// get the http session
		HttpSession httpSession = request.getSession();
		User user = (User)httpSession.getAttribute("user");
		
		// get http headers
		HttpHeaders httpHeaders = getAuthHeaders(user.getUserName(), user.getPassword());
		
		// create HttpEntity and make an http request
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		return httpEntity;
	}
	
	public static <T> HttpEntity<?> constructHttpEntity(T object, HttpServletRequest request) {
		// get the http session
		HttpSession httpSession = request.getSession();
		User user = (User)httpSession.getAttribute("user");
		
		// get http headers
		HttpHeaders httpHeaders = getAuthHeaders(user.getUserName(), user.getPassword());
		
		// create HttpEntity and make an http request
		HttpEntity<?> httpEntity = new HttpEntity<>(object, httpHeaders);
		
		return httpEntity;
	}
}
