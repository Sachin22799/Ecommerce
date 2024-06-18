package com.ecommerce.ui.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.ui.service.IUICustomerService;
import com.ecommerce.ui.service.IUISellerService;
import com.ecommerce.ui.utils.ApiUtil;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.RecipesDetailsUIVO;
import com.ecommerce.ui.vo.SellerDetailsUIVo;
import com.ecommerce.ui.vo.UserDetailsUIVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class UISellerServiceImpl implements IUISellerService {

	@Autowired
	RestTemplate restTemplate;

//	@Value("${seller.module.url}")
//	String url;

	ApiUtil apiutil = new ApiUtil();

	String url = "http://localhost:8095/xpertit_ecommerce/seller";

	@Override
	public SellerDetailsUIVo checkLogin(String email, String password) {
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

			response = restTemplate.exchange(url + "/check-seller-login", HttpMethod.POST, requestEntity, String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return convertJsonToResponseVo(response.getBody());
	}

	public SellerDetailsUIVo convertJsonToResponseVo(String body) {
		SellerDetailsUIVo sellerDetailsResponse = new SellerDetailsUIVo();
		try {
			if (null != body) {
				JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();
				sellerDetailsResponse = objectmaper.readValue(json.toString(), SellerDetailsUIVo.class);
			}
		} catch (JSONException | JsonProcessingException je) {
			System.out.println(je);
		}
		return sellerDetailsResponse;
	}

	@Override
	public SellerDetailsUIVo forgotPassword(String emailContactNo) {
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
	public SellerDetailsUIVo saveSellerData(SellerDetailsUIVo uc) {

		ResponseEntity<SellerDetailsUIVo> response = null;
		HttpEntity<SellerDetailsUIVo> requestEntity = new HttpEntity<>(uc, apiutil.getHeaders());
		response = restTemplate.exchange(url + "/saveSeller", HttpMethod.POST, requestEntity, SellerDetailsUIVo.class);

		SellerDetailsUIVo sellerDetailsResponse = new SellerDetailsUIVo();
		try {
			JSONObject json = new JSONObject(response.getBody());
			ObjectMapper objectMapper = new ObjectMapper();
			sellerDetailsResponse = objectMapper.readValue(json.toString(), SellerDetailsUIVo.class);
			// userDetailsResponse.setFirstName(json.getString("fristName"));
		} catch (JSONException | JsonProcessingException je) {

		}
		return sellerDetailsResponse;

	}

	
	@Override
	public List<CategoryUIVO> getAllCategories() {
		ResponseEntity<String> response = null;

		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/load-categories");
		
		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToCategoriesListResponseVo(response.getBody());
//		try {
//			JSONObject json = new JSONObject(response.getBody());
//			ObjectMapper objectMapper = new ObjectMapper();
//			productDetailsResponse = objectMapper.readValue(json.toString(), ProductDetailsUIVo.class);
//		} catch (JSONException | JsonProcessingException je) {
//
//		}
//		return productDetailsResponse;
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
	public ProductDetailsUIVo addNewProduct(ProductDetailsUIVo productDetails) {
		ResponseEntity<String> response = null;

		HttpEntity<ProductDetailsUIVo> requestEntity = new HttpEntity<>(productDetails, apiutil.getHeaders());
		response = restTemplate.exchange(url + "/add-product", HttpMethod.POST, requestEntity, String.class);

		ProductDetailsUIVo productDetailsResponse = null;
		try {
			if(response.getBody() != null) {
				JSONObject json = new JSONObject(response.getBody());
				ObjectMapper objectMapper = new ObjectMapper();
				productDetailsResponse = objectMapper.readValue(json.toString(), ProductDetailsUIVo.class);
				}
			
		} catch (JSONException | JsonProcessingException je) {

		}
		return productDetailsResponse;
	}
	
//	@Override
//	public CategoryUIVO addNewCategory(CategoryUIVO category) {
//		ResponseEntity<String> response = null;
//		 
//		HttpEntity<CategoryUIVO> requestEntity = new HttpEntity<>(category,apiutil.getHeaders());
//		response = restTemplate.exchange(url + "/add-category", HttpMethod.POST, requestEntity, String.class);
//
//		CategoryUIVO categoryDetailsResponse = new CategoryUIVO();
//		try {
//			JSONObject json = new JSONObject(response.getBody());
//			ObjectMapper objectMapper = new ObjectMapper();
//			categoryDetailsResponse = objectMapper.readValue(json.toString(), CategoryUIVO.class);
//		} catch (JSONException | JsonProcessingException je) {
//
//		}
//		return categoryDetailsResponse;
//	}

	@Override
	public CategoryUIVO findCategory(Integer categoryId) {
		ResponseEntity<String> response = null;
		HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url + "/find-by-category-id")
				.queryParam("categoryId", categoryId).build();

		// bridge between 2 micro services
		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
		return convertJsonToCategoryResponseVo(response.getBody());
	}

	public CategoryUIVO convertJsonToCategoryResponseVo(String body) {

		CategoryUIVO categoryDetail = new CategoryUIVO();
		try {
			if (null != body) {
				JSONObject json = new JSONObject(body);
				ObjectMapper objectmaper = new ObjectMapper();
				categoryDetail = objectmaper.readValue(json.toString(), CategoryUIVO.class);
			}
		} catch (JSONException | JsonProcessingException je) {
			System.out.println(je);
		}
		return categoryDetail;
	}


	
	
	public List<ProductDetailsUIVo> getShowMyProducts(Integer sellerId) {
	    ResponseEntity<String> response = null;
	    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/get-seller-product")
	            .queryParam("sellerId", sellerId);

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
	public ProductDetailsUIVo getProductById(Integer productId) {
		ResponseEntity<String> response = null;
	    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/edit-product")
	            .queryParam("productId", productId);

	    response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

	    ProductDetailsUIVo productDetailsUIVo = null;
	    try {
	        if (null != response.getBody()) {
	            JSONObject jsonObject = new JSONObject(response.getBody());
	            ObjectMapper objectMapper = new ObjectMapper();
	            productDetailsUIVo = objectMapper.readValue(jsonObject.toString(), ProductDetailsUIVo.class);
	        }
	    } catch (JSONException | JsonProcessingException je) {
	        System.out.println(je);
	    }
	    return productDetailsUIVo;
	}

	@Override
	public ProductDetailsUIVo updateProduct(ProductDetailsUIVo updatedProduct) {
	    ResponseEntity<String> response = null;
	    HttpEntity<ProductDetailsUIVo> requestEntity = new HttpEntity<>(updatedProduct, apiutil.getHeaders());

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/update-product");

	    response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, String.class);

	    ProductDetailsUIVo productDetailsUIVo = null;
	    try {
	        if (null != response.getBody()) {
	            JSONObject jsonObject = new JSONObject(response.getBody());
	            ObjectMapper objectMapper = new ObjectMapper();
	            productDetailsUIVo = objectMapper.readValue(jsonObject.toString(), ProductDetailsUIVo.class);
	        }
	    } catch (JSONException | JsonProcessingException je) {
	        System.out.println(je);
	    }
	    return productDetailsUIVo;
	}

	@Override
	public Integer deleteProductById(Integer productId) {
	    ResponseEntity<Integer> response = null;
	    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/delete-product")
	            .queryParam("productId", productId);

	    response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, requestEntity, Integer.class);

	//    ProductDetailsUIVo productDetailsUIVo = null;
//	    Integer conut= 0;
//	    try {
//	        if (null != response.getBody()) {
//	            JSONObject jsonObject = new JSONObject(response.getBody());
//	            ObjectMapper objectMapper = new ObjectMapper();
//	            conut = objectMapper.readValue(jsonObject.toString(), Integer.class);
//	            //productDetailsUIVo = objectMapper.readValue(jsonObject.toString(), ProductDetailsUIVo.class);
//	        }
//	    } catch (JSONException | JsonProcessingException je) {
//	        System.out.println(je);
//	    }
	    return response.getBody();
	}

	@Override
	public RecipesDetailsUIVO addNewRecipe(RecipesDetailsUIVO recipeDetails) {
		ResponseEntity<String> response = null;

		HttpEntity<RecipesDetailsUIVO> requestEntity = new HttpEntity<>(recipeDetails, apiutil.getHeaders());
		response = restTemplate.exchange(url + "/add-recipe", HttpMethod.POST, requestEntity, String.class);

		RecipesDetailsUIVO recpesDetailsResponse = null;
		try {
			if(response.getBody() != null) {
				JSONObject json = new JSONObject(response.getBody());
				ObjectMapper objectMapper = new ObjectMapper();
				recpesDetailsResponse = objectMapper.readValue(json.toString(), RecipesDetailsUIVO.class);
				}
			
		} catch (JSONException | JsonProcessingException je) {

		}
		return recpesDetailsResponse;
	}

	@Override
	public List<RecipesDetailsUIVO> getShowMyrecipe(Integer sellerId) {
		    ResponseEntity<String> response = null;
		    HttpEntity<String> requestEntity = new HttpEntity<>(null, apiutil.getHeaders());

		    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/get-seller-recipes")
		            .queryParam("sellerId", sellerId);

		    response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

		    List<RecipesDetailsUIVO> recipeList = new ArrayList<>();
		    try {
		        if (null != response.getBody()) {
		            JSONArray jsonArray = new JSONArray(response.getBody());
		            ObjectMapper objectMapper = new ObjectMapper();
		            RecipesDetailsUIVO[] recipes = objectMapper.readValue(jsonArray.toString(),
		            		RecipesDetailsUIVO[].class);
		            recipeList = Arrays.asList(recipes);
		        }
		    } catch (JSONException | JsonProcessingException je) {
		        System.out.println(je);
		    }
		    return recipeList;
		}
	}
	
	



	
	

