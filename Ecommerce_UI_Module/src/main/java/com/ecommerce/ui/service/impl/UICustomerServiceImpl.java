package com.ecommerce.ui.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.ui.service.IUICustomerService;
import com.ecommerce.ui.utils.ApiUtil;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.UserDetailsUIVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class UICustomerServiceImpl implements IUICustomerService {

	@Autowired
	RestTemplate restTemplate;
	
	 ApiUtil apiutil= new ApiUtil();


//	@Value("${customer.module.url}")
//
//	String url ;
	

	
	String url ="http://localhost:8094/xpertit_ecommerce/customer";

	@Override
	public UserDetailsUIVo checkLogin(String email, String password) { 
		ResponseEntity<String> response = null;
		try {

			Map<String, String> uriparam = new HashMap<>();
			uriparam.put("email", email);
			uriparam.put("password", password);

			LoginInputVo inputVo = new LoginInputVo();
			inputVo.setEmail(email);
			inputVo.setPassword(password);

			ObjectMapper mapper = new ObjectMapper();

			mapper.writeValueAsString(inputVo);

			HttpEntity<LoginInputVo> requestEntity = new HttpEntity<LoginInputVo>(inputVo, apiutil.getHeaders());

			response = restTemplate.exchange(url + "/check-user-login", HttpMethod.POST, requestEntity, String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return convertJsonToResponseVo(response.getBody());
	}

//	public HttpHeaders getHeaders1() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("accept", "*/*");
//		headers.add("content-type", "application/json; charset=utf-8");
//		return headers;
//	}

	public UserDetailsUIVo convertJsonToResponseVo(String body) {
		UserDetailsUIVo userDetailsResponse = new UserDetailsUIVo();
		try {
			if (null != body) {
				JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();
				userDetailsResponse = objectmaper.readValue(json.toString(), UserDetailsUIVo.class);
			}
		} catch (JSONException | JsonProcessingException je) {
			System.out.println(je);
		}
		return userDetailsResponse;
	}

	@Override
	public UserDetailsUIVo forgotPassword(String emailContactNo) {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/forgot-pass")
				.queryParam("email", emailContactNo).build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToResponseVo(response.getBody());
	}

	@Override
	public LoginInputVo resetPassword(String email, String password) {
		ResponseEntity<String> response = null;
		try {

			LoginInputVo inputVo = new LoginInputVo();
			inputVo.setEmail(email);
			inputVo.setPassword(password);

			ObjectMapper mapper = new ObjectMapper();

			mapper.writeValueAsString(inputVo);

			HttpEntity<LoginInputVo> requestEntity = new HttpEntity<LoginInputVo>(inputVo, apiutil.getHeaders());

			response = restTemplate.exchange(url + "/reset-pass", HttpMethod.POST, requestEntity, String.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return convertJsonToLoginInputVo(response.getBody());
	}

	

//	public HttpHeaders getHeaders() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("accept", "*/*");
//		headers.add("content-type", "application/json; charset=utf-8");
//		return headers;
//	}

	public LoginInputVo convertJsonToLoginInputVo(String body) {

		LoginInputVo loginInputeVo = new LoginInputVo();
		try {
			if (null != body) {
				JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();
				loginInputeVo = objectmaper.readValue(json.toString(), LoginInputVo.class);
			}
		} catch (JSONException | JsonProcessingException je) {
			System.out.println(je);
		}
		return loginInputeVo;
	}

	@Override
	public UserDetailsUIVo saveUserData(UserDetailsUIVo uc) {

		ResponseEntity<UserDetailsUIVo> response = null;
		HttpEntity<UserDetailsUIVo> requestEntity = new HttpEntity<>(uc, apiutil.getHeaders());
		response = restTemplate.exchange(url + "/saveUser", HttpMethod.POST, requestEntity, UserDetailsUIVo.class);

		UserDetailsUIVo userDetailsResponse = new UserDetailsUIVo();
		try {
			JSONObject json = new JSONObject(response.getBody());
			ObjectMapper objectMapper = new ObjectMapper();
			userDetailsResponse = objectMapper.readValue(json.toString(), UserDetailsUIVo.class);
			// userDetailsResponse.setFirstName(json.getString("fristName"));
		} catch (JSONException | JsonProcessingException je) {

		}
		return userDetailsResponse;

	}

	
	

}
