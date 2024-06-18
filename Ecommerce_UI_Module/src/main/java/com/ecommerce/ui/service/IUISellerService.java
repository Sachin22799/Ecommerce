package com.ecommerce.ui.service;

import java.util.List;

import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.RecipesDetailsUIVO;
import com.ecommerce.ui.vo.SellerDetailsUIVo;

public interface IUISellerService {

	public SellerDetailsUIVo checkLogin(String email, String password);

	public SellerDetailsUIVo forgotPassword(String emailContactNo);

	public LoginInputVo resetPassword(String string, String newPassword);

	public SellerDetailsUIVo saveSellerData(SellerDetailsUIVo uc);

	public List<CategoryUIVO> getAllCategories();

	public ProductDetailsUIVo addNewProduct(ProductDetailsUIVo productDetails);
	
	// public CategoryUIVO addNewCategory(CategoryUIVO categoryDetail);


	public List<ProductDetailsUIVo> getShowMyProducts(Integer sellerId);

	public ProductDetailsUIVo getProductById(Integer productId);

	public ProductDetailsUIVo updateProduct(ProductDetailsUIVo updatedProduct);

	public Integer deleteProductById(Integer productId);

	public RecipesDetailsUIVO addNewRecipe(RecipesDetailsUIVO recipeDetails);

	public List<RecipesDetailsUIVO> getShowMyrecipe(Integer sellerId);

	public CategoryUIVO findCategory(Integer categoryId);










	
	
	
	
	
}
