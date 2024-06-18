package com.ecommerce.common.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.entity.CategoryEntity;
import com.ecommerce.common.entity.ProductDetailsEntity;
import com.ecommerce.common.entity.RecipesDetailsEntity;
import com.ecommerce.common.reposetory.CategoryRepository;
import com.ecommerce.common.reposetory.ProductDetailsRepository;
import com.ecommerce.common.reposetory.RecipeDetailsRepository;
import com.ecommerce.common.service.IEcommerceCommonService;

@Service
public class EcommerceCommonServiceImpl implements IEcommerceCommonService {
	
	@Autowired
	ProductDetailsRepository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	 RecipeDetailsRepository recipesRepo ;

	
	public List<ProductDetailsEntity> getAllProducts(Character flag){
		return productRepo.findByIsApproved(flag);
		
	}

	
	public List<CategoryEntity> getAllCategories(Character flag) {
		return  categoryRepo.findByIsApproved(flag);
	}

	@Override
	public List<ProductDetailsEntity> findByProductName(String productName) {
		return productRepo.findByProductNameLike("%" +productName+ "%");
	}


	@Override
	public List<RecipesDetailsEntity> getAllrecipes(Character flag) {
		return recipesRepo.findByIsApproved(flag);
	}


	@Override
	public List<ProductDetailsEntity> findProductByCategory(Integer categoryId) {
		
		return productRepo.findByCategoryId(categoryId);
	}


	@Override
	public Optional<ProductDetailsEntity> findProductById(Integer productId) {
		return productRepo.findById(productId);
	}

	
//	@Override
//	public List<ProductDetailsEntity> findProductByCategory(Integer categoryId) {
//	return productDetailsRepo.findByCategoryId(categoryId);
//
//	}



}
 