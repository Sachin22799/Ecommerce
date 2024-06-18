package com.ecommerce.ui.utils;

import org.springframework.http.HttpHeaders;

public class ApiUtil {
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "*/*");
		headers.add("content-type", "application/json; charset=utf-8");
		return headers;
	}


}
