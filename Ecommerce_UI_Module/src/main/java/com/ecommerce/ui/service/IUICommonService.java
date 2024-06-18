package com.ecommerce.ui.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.RecipesDetailsUIVO;

public interface IUICommonService {
	
	public 	List<ProductDetailsUIVo> getAllProducts();
	
	public	List<CategoryUIVO> getAllCategories();

	public List<ProductDetailsUIVo> searchProducts(String query);

	public List<RecipesDetailsUIVO> getAllRecipes();

	public List<ProductDetailsUIVo> getShowProducts(Integer categoryId);

	public List<ProductDetailsUIVo> getShowProductsInfo(Integer id);








  

}
