package com.ecommerce.seller.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.seller.entity.CategoryEntity;
import com.ecommerce.seller.entity.ProductDetailsEntity;
import com.ecommerce.seller.entity.RecipesDetailsEntity;

public interface ISellerService {

	public List<CategoryEntity> getAllCategories();

	public ProductDetailsEntity addNewProduct(ProductDetailsEntity productDetails);
	
	// public CategoryEntity addNewCategory(CategoryEntity categoryDetails);

	public Optional<CategoryEntity> findByCategoryId(String categoryId);

	public List<ProductDetailsEntity> findProductBySeller(Integer sellerId);

	public ProductDetailsEntity editProduct(ProductDetailsEntity productDetails);
	
	public Optional<ProductDetailsEntity> findByProductId(Integer productId);

	public ProductDetailsEntity updateProduct(ProductDetailsEntity productDetails);

	public Integer deleteProduct(Integer productId);

	public boolean isDuplicateProduct(String productName, Integer sellerId);

	public RecipesDetailsEntity addNewRecipe(RecipesDetailsEntity recipeDetails);

	public List<RecipesDetailsEntity> findRecipesBySeller(Integer sellerId);



}
