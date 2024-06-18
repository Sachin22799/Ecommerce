package com.ecommerce.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.common.entity.CategoryEntity;
import com.ecommerce.common.entity.ProductDetailsEntity;
import com.ecommerce.common.entity.RecipesDetailsEntity;

public interface IEcommerceCommonService {


	public List<ProductDetailsEntity> getAllProducts(Character flag);

	public List<CategoryEntity> getAllCategories(Character flag);

	public List<ProductDetailsEntity> findByProductName(String productName);

	public List<RecipesDetailsEntity> getAllrecipes(Character flag);

	public List<ProductDetailsEntity> findProductByCategory(Integer categoryId);

	public Optional<ProductDetailsEntity> findProductById(Integer productId);


}
