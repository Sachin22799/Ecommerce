 package com.ecommerce.ui.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.ui.service.IUICommonService;
import com.ecommerce.ui.utils.ApiUtil;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.RecipesDetailsUIVO;
import com.ecommerce.ui.vo.SellerDetailsUIVo;
import com.ecommerce.ui.vo.UserDetailsUIVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UICommonServiceImpl implements IUICommonService {

	@Autowired
	RestTemplate restTemplate;
	
	 ApiUtil apiutil= new ApiUtil();


//	@Value("${common.module.url}")
//
//	String url ;
	
	
	
	String url = "http://localhost:8093/xpertit_ecommerce/common";

	@Override
	public List<ProductDetailsUIVo> getAllProducts() {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-products").build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToProductListResponseVo(response.getBody());
	}

	public List<ProductDetailsUIVo> convertJsonToProductListResponseVo(String body) {
		List<ProductDetailsUIVo> productDetailsResponse = new ArrayList<>();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				ProductDetailsUIVo[] productDetailsResponseArray = objectmaper.readValue(body,
						ProductDetailsUIVo[].class);
				productDetailsResponse = Arrays.asList(productDetailsResponseArray);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return productDetailsResponse;
	}

	
	@Override
	public List<CategoryUIVO> getAllCategories() {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-categories").build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToCategoriesListResponseVo(response.getBody());
	}
	

	public List<CategoryUIVO> convertJsonToCategoriesListResponseVo(String body) {
		List<CategoryUIVO> categoriesResponse = new ArrayList<>();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				CategoryUIVO[] categoryResponseArray = objectmaper.readValue(body, CategoryUIVO[].class);
				categoriesResponse = Arrays.asList(categoryResponseArray);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return categoriesResponse;
	}
 
	
	@Override
	public List<ProductDetailsUIVo> searchProducts(String query) {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/search-products").queryParam("productName",query ).build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToProductListResponseVo(response.getBody());
	}
	
	
	

	@Override
	public List<RecipesDetailsUIVO> getAllRecipes() {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-recipes").build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonTorecipesListResponseVo(response.getBody());
	}

	public List<RecipesDetailsUIVO> convertJsonTorecipesListResponseVo(String body) {
		List<RecipesDetailsUIVO> recieseDetailsResponse = new ArrayList<>();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				RecipesDetailsUIVO[] recipesDetailsResponseArray = objectmaper.readValue(body,
						RecipesDetailsUIVO[].class);
				recieseDetailsResponse = Arrays.asList(recipesDetailsResponseArray);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return recieseDetailsResponse;
	}

	

	@Override
	public List<ProductDetailsUIVo> getShowProducts(Integer categoryId) {
		ResponseEntity<String> response = null;
	    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/get-category-product")
	            .queryParam("categoryId", categoryId);

	    response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

	    List<ProductDetailsUIVo> productList = new ArrayList<>();
	    try {
	        if (null != response.getBody()) {
	            JSONArray jsonArray = new JSONArray(response.getBody());
	            ObjectMapper objectMapper = new ObjectMapper();
	            ProductDetailsUIVo[] products = objectMapper.readValue(jsonArray.toString(),
	                    ProductDetailsUIVo[].class);
	            productList = Arrays.asList(products);
	        }
	    } catch (JSONException | JsonProcessingException je) {
	        System.out.println(je);
	    }
	    return productList;
	}
	
	

	@Override
	public List<ProductDetailsUIVo> getShowProductsInfo(Integer id) {

	    ResponseEntity<String> response = null;
	    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/get-product-info")
	            .queryParam("id", id);

	    response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

	    List<ProductDetailsUIVo> productList = new ArrayList<>();
	    try {
	        if (null != response.getBody()) {
	            JSONArray jsonArray = new JSONArray(response.getBody());
	            ObjectMapper objectMapper = new ObjectMapper();
	            ProductDetailsUIVo[] products = objectMapper.readValue(jsonArray.toString(),
	                    ProductDetailsUIVo[].class);
	            productList = Arrays.asList(products);
	        }
	    } catch (JSONException | JsonProcessingException je) {
	        System.out.println(je);
	    }
	    return productList;
}
	
	
//	@Override
//	public List<ProductDetailsUIVo> getShowProductsInfo(Integer productId) {
//	ResponseEntity<String> response = null;
//	HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());
//
//	UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-product-info").build();
//
//	response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
//	return convertJsonToProductListResponseVo(response.getBody());
//}
//
//public List<ProductDetailsUIVo> convertJsonToproductListResponseVo(String body) {
//	List<ProductDetailsUIVo> productDetailsResponse = new ArrayList<>();
//	try {
//		if (null != body) {
//			// JSONObject json = new JSONObject(body);
//			ObjectMapper objectmaper = new ObjectMapper();
//
//			ProductDetailsUIVo[] recipesDetailsResponseArray = objectmaper.readValue(body,
//					ProductDetailsUIVo[].class);
//			productDetailsResponse = Arrays.asList(recipesDetailsResponseArray);
//		}
//	} catch (JsonProcessingException je) {
//		System.out.println(je);
//	}
//	return productDetailsResponse;
//}

	
}

	
