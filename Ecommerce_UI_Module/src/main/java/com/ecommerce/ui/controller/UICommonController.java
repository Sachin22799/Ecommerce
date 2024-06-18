package com.ecommerce.ui.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.ui.service.IUICommonService;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.RecipesDetailsUIVO;

@Controller
@RequestMapping("/home")
public class UICommonController {

	@Autowired
	IUICommonService commonService;

		  
//	        ProductDetailsUIVo productDetails = productResponseList.get(1);
//	        session.setAttribute("productDetails", productDetails);
    	        


	
	@GetMapping("/")
	public String loadHomePage(ModelMap map, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	    List<ProductDetailsUIVo> productResponseList = commonService.getAllProducts();
	    List<CategoryUIVO> categoriesResponseList = commonService.getAllCategories();
	    List<RecipesDetailsUIVO> recipeResponse = commonService.getAllRecipes();

	    map.addAttribute("recipes", recipeResponse);
	    map.addAttribute("productList", productResponseList);
	    map.addAttribute("categoryList", categoriesResponseList);  

	    session.setAttribute("productDetails", productResponseList);
	    
	    int count = productResponseList.size();
	    map.addAttribute("cartcount", count);

	    return "index";
	}


	@PostMapping("/") // for search product
	public String loadSearchResults(ModelMap map, @RequestParam("query") String query, HttpSession session,
			HttpServletRequest request, HttpServletResponse respons) {
		List<ProductDetailsUIVo> productResponseList = commonService.searchProducts(query);
		// List<CategoryUIVO> categoriesResponseList=commonService.getAllCategories();
		map.addAttribute("productList", productResponseList);

		// map.addAttribute("categoryList",categoriesResponseList);
		return "SearchResult";
	}
	

	@GetMapping("/load-Product")
	public String getLoadMyProduct(@RequestParam("id") Integer categoryId, ModelMap map, HttpSession session) {
	    List<ProductDetailsUIVo> productsResponseList = (List<ProductDetailsUIVo>) session.getAttribute("productDetails");
	    
	    List<ProductDetailsUIVo> productList = productsResponseList.stream().filter(prod -> prod.getCategoryId().equals(categoryId)).collect(Collectors.toList());
	    
	    map.addAttribute("productList", productList);
	    
	    return "Product";
	}

	
	@GetMapping("/Product-info")
	public String ProductInfo(@RequestParam("id")Integer productId, ModelMap map, HttpSession session, HttpServletRequest request) {
		session=request.getSession(false);

		List<ProductDetailsUIVo> productsResponseList = (List<ProductDetailsUIVo>) session.getAttribute("productDetails");
		Optional<ProductDetailsUIVo> productDetailObj = productsResponseList.stream().filter(prod -> prod.getId().equals(productId)).findAny();
		ProductDetailsUIVo productDetail = productDetailObj.isPresent() ? productDetailObj.get() : new ProductDetailsUIVo();
		map.addAttribute("productDetail", productDetail);
		//List<ProductDetailsUIVo> productInfo = commonService.getShowProductsInfo(productDetails.getId());
		//map.addAttribute("products", productInfo);

		return "ProductInfo";
	}

	
}




