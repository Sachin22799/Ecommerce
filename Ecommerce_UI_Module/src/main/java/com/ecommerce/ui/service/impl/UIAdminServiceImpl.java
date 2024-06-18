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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.ui.service.IUIAdminService;
import com.ecommerce.ui.utils.ApiUtil;
import com.ecommerce.ui.vo.AdminDetailsUIVo;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.SellerDetailsUIVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UIAdminServiceImpl implements IUIAdminService {

	@Autowired
	RestTemplate restTemplate;

	ApiUtil apiutil = new ApiUtil();

//	@Value("${admin.module.url}")
//
//	String url ;

	String url = "http://localhost:8092/xpertit_ecommerce/admin";

	@Override
	public List<CategoryUIVO> getAllCategories() {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-unapproved-categories").build();

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
	public List<SellerDetailsUIVo> getAllSellers() {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-unapproved-seller").build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToSellerListResponseVo(response.getBody());
	}

	private List<SellerDetailsUIVo> convertJsonToSellerListResponseVo(String body) {
		List<SellerDetailsUIVo> sellerResponse = new ArrayList<>();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				SellerDetailsUIVo[] sellerResponseArray = objectmaper.readValue(body, SellerDetailsUIVo[].class);
				sellerResponse = Arrays.asList(sellerResponseArray);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return sellerResponse;

	}

	@Override
	public List<ProductDetailsUIVo> getAllProducts() {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/get-unapproved-product").build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToProductListResponseVo(response.getBody());
	}

	private List<ProductDetailsUIVo> convertJsonToProductListResponseVo(String body) {
		List<ProductDetailsUIVo> productResponse = new ArrayList<>();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				ProductDetailsUIVo[] productResponseArray = objectmaper.readValue(body, ProductDetailsUIVo[].class);
				productResponse = Arrays.asList(productResponseArray);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return productResponse;
	}

	@Override
	public SellerDetailsUIVo approveSeller(Integer sellerId) {
		ResponseEntity<String> response = null;

		ObjectMapper mapper = new ObjectMapper();

		HttpEntity<Integer> requestEntity = new HttpEntity<Integer>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/approve-seller")
				.queryParam("sellerId", sellerId).build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

		return convertJsonToSellerResponseVo(response.getBody());
	}

	private SellerDetailsUIVo convertJsonToSellerResponseVo(String body) {

		SellerDetailsUIVo sellerResponse = new SellerDetailsUIVo();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				sellerResponse = objectmaper.readValue(body, SellerDetailsUIVo.class);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return sellerResponse;
	}

	@Override
	public CategoryUIVO approvedCategory(Integer categoryId) {
		ResponseEntity<String> response = null;

		ObjectMapper mapper = new ObjectMapper();

		HttpEntity<Integer> requestEntity = new HttpEntity<Integer>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/approve-category")
				.queryParam("categoryId", categoryId).build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

		return convertJsonToCategoryResponseVo(response.getBody());
	}

	private CategoryUIVO convertJsonToCategoryResponseVo(String body) {

		CategoryUIVO categoryResponse = new CategoryUIVO();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				categoryResponse = objectmaper.readValue(body, CategoryUIVO.class);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return categoryResponse;
	}

	@Override
	public ProductDetailsUIVo approveProduct(Integer productId) {
		ResponseEntity<String> response = null;

		ObjectMapper mapper = new ObjectMapper();

		HttpEntity<Integer> requestEntity = new HttpEntity<Integer>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/approve-product")
				.queryParam("productId", productId).build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

		return convertJsonToProductResponseVo(response.getBody());
	}

	private ProductDetailsUIVo convertJsonToProductResponseVo(String body) {
		ProductDetailsUIVo productResponse = new ProductDetailsUIVo();
		try {
			if (null != body) {
				// JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();

				productResponse = objectmaper.readValue(body, ProductDetailsUIVo.class);
			}
		} catch (JsonProcessingException je) {
			System.out.println(je);
		}
		return productResponse;

	}

	@Override
	public AdminDetailsUIVo saveAdminData(AdminDetailsUIVo adminDetails) {
		ResponseEntity<AdminDetailsUIVo> response = null;
		HttpEntity<AdminDetailsUIVo> requestEntity = new HttpEntity<>(adminDetails, apiutil.getHeaders());
		response = restTemplate.exchange(url + "/saveAdmin", HttpMethod.POST, requestEntity, AdminDetailsUIVo.class);

		AdminDetailsUIVo adminDetailsResponse = new AdminDetailsUIVo();
		try {
			JSONObject json = new JSONObject(response.getBody());
			ObjectMapper objectMapper = new ObjectMapper();
			adminDetailsResponse = objectMapper.readValue(json.toString(), AdminDetailsUIVo.class);
		} catch (JSONException | JsonProcessingException je) {

		}
		return adminDetailsResponse;
	}

	@Override
	public AdminDetailsUIVo checkLogin(String email, String password) {
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

			response = restTemplate.exchange(url + "/check-admin-login", HttpMethod.POST, requestEntity, String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return convertJsonToResponseVo(response.getBody());
	}

	public AdminDetailsUIVo convertJsonToResponseVo(String body) {
		AdminDetailsUIVo adminDetailsResponse = new AdminDetailsUIVo();
		try {
			if (null != body) {
				JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();
				adminDetailsResponse = objectmaper.readValue(json.toString(), AdminDetailsUIVo.class);
			}
		} catch (JSONException | JsonProcessingException je) {
			System.out.println(je);
		}
		return adminDetailsResponse;
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
	public AdminDetailsUIVo forgotPassword(String emailContactNo) {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/forgot-pass")
				.queryParam("email", emailContactNo).build();

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToResponseVo(response.getBody());
	}

	@Override
	public CategoryUIVO addNewCategory(CategoryUIVO categoryDetail) {
		ResponseEntity<String> response = null;
		 
		HttpEntity<CategoryUIVO> requestEntity = new HttpEntity<>(categoryDetail,apiutil.getHeaders());
		response = restTemplate.exchange(url + "/add-category", HttpMethod.POST, requestEntity, String.class);

		CategoryUIVO categoryDetailsResponse = new CategoryUIVO();
		try {
			JSONObject json = new JSONObject(response.getBody());
			ObjectMapper objectMapper = new ObjectMapper();
			categoryDetailsResponse = objectMapper.readValue(json.toString(), CategoryUIVO.class);
		} catch (JSONException | JsonProcessingException je) {

		}
		return categoryDetailsResponse;
	}

//	@Override
//	public Integer deleteCategoryById(Integer categoryIdId) {
//		 ResponseEntity<Integer> response = null;
//		    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());
//
//		    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/delete-category")
//		            .queryParam("categoryIdId", categoryIdId);
//
//		    response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, requestEntity, Integer.class);
//
//		    return response.getBody();
//		}
	
	@Override
	public Integer deleteCategoryById(CategoryUIVO category) {
		ResponseEntity<Integer>response= null;
		HttpEntity<CategoryUIVO> requestEntity = new HttpEntity<>(category,apiutil.getHeaders());
		response = restTemplate.exchange(url+"/delete-category", HttpMethod.POST, requestEntity, Integer.class);
		return response.getBody();
	}



	
	}

	


	

