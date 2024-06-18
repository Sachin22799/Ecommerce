package com.ecommerce.common.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.entity.CategoryEntity;
import com.ecommerce.common.entity.ProductDetailsEntity;
import com.ecommerce.common.entity.RecipesDetailsEntity;
import com.ecommerce.common.reposetory.ProductDetailsRepository;
import com.ecommerce.common.service.IEcommerceCommonService;

@RestController
public class CommonController {

	@Autowired
	IEcommerceCommonService commonService;

	@Autowired
	ProductDetailsRepository productDetailsRepo;

	@GetMapping("/get-products")
	public ResponseEntity<?> getAllProducts() {
		return new ResponseEntity<List<ProductDetailsEntity>>(commonService.getAllProducts('Y'), HttpStatus.OK);

	}

	@GetMapping("/get-categories")
	public ResponseEntity<?> getAllCategories() {
		return new ResponseEntity<List<CategoryEntity>>(commonService.getAllCategories('Y'), HttpStatus.OK);
	}

	@GetMapping("/search-products")
	public ResponseEntity<?> searchProducts(@RequestParam("productName") String productName) {
		return new ResponseEntity<List<ProductDetailsEntity>>(commonService.findByProductName(productName), HttpStatus.OK);

	}
	
	@GetMapping("/get-recipes")
	public ResponseEntity<?> getAllrecipes() {
		return new ResponseEntity<List<RecipesDetailsEntity>>(commonService.getAllrecipes('Y'), HttpStatus.OK);

	}

	@GetMapping("/get-category-product")
	public ResponseEntity<?> showProduct(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId) {
		return new ResponseEntity<List<ProductDetailsEntity>>(commonService.findProductByCategory(categoryId),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/get-product-info")
	public ResponseEntity<?> showProductInfo(
			@RequestParam(name = "productId", required = false, defaultValue = "0") Integer productId) {
		return new ResponseEntity<Optional<ProductDetailsEntity>>(commonService.findProductById(productId),
				HttpStatus.OK);
	}
	

}  
