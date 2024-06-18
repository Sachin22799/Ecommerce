package com.ecommerce.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.ui.service.IUICommonService;
import com.ecommerce.ui.service.IUISellerService;
import com.ecommerce.ui.utils.CommonUtil;
import com.ecommerce.ui.utils.RecipeCommonUtil;
import com.ecommerce.ui.utils.SecurityQuestion;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.RecipesDetailsUIVO;
import com.ecommerce.ui.vo.SellerDetailsUIVo;
import com.ecommerce.ui.vo.UserDetailsUIVo;

@Controller
@RequestMapping("/seller")
public class UISellerController {

	@Autowired
	IUISellerService sellerService;

	@Autowired
	IUICommonService commonService;

	RecipeCommonUtil recipeUtil = new RecipeCommonUtil();

	CommonUtil commonUtil = new CommonUtil();

	@GetMapping("/")
	public String loadHomePage() {

		return "index";
	}

	@GetMapping("/Registration")
	public String loadSellerRegistrationpage(ModelMap map) {
		List<String> Securityques = Stream.of(SecurityQuestion.values()).map(SecurityQuestion::toString)
				.collect(Collectors.toList());
		map.addAttribute("SecurityQuestion", Securityques);
//		for(String data:Securityques) {
//			System.out.println(data);
//		}
		return "seller/Seller_Registration";
	}

//	@PostMapping("/Registration")
//	public String saveData(@ModelAttribute SellerDetailsUIVo uc) {
//		System.out.println("seller Data:" + uc);
//		SellerDetailsUIVo seller = sellerService.saveSellerData(uc);
//		System.out.println("seller:" + seller);
//		System.out.println(seller);
//		return "seller/Seller_Login";
//	}
//	
	@PostMapping("/Registration")
	public String saveData(@ModelAttribute SellerDetailsUIVo SellerDetail, Model model) {
		System.out.println("seller Data:" + SellerDetail);
		SellerDetailsUIVo seller = sellerService.saveSellerData(SellerDetail);
//		if (seller == null) {
//			model.addAttribute("error", "Email is already registered. Please use a different email address.");
//			return "redirect:seller/sellerRegistration";
//		}

		return "seller/Seller_Login";
	}

	@GetMapping("/logout")
	public String getlogoutPage(ModelMap map) {
		return "seller/Seller_Login";
	}

	@GetMapping("/login")
	public String getloginPage(ModelMap map) {

		return "seller/Seller_Login";
	}

	@PostMapping("/login")
	public String checklogin(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		
		SellerDetailsUIVo sellerDetails = sellerService.checkLogin(email, password);
	
		if (null != sellerDetails && null != sellerDetails.getEmail()) {
			map.put("response1", sellerDetails.getFirstName());
			map.put("response2", sellerDetails.getLastName());
			map.put("phone", sellerDetails.getContactNo());
			map.put("address", sellerDetails.getAddress());
			map.put("pincod", sellerDetails.getPincod());
			
			
			session.setAttribute("email", email);
			session.setAttribute("sellerDetails", sellerDetails);
			response.setHeader("refresh", request.getContextPath() + "/");

			return "seller/MyAccount";

		} else {
			map.addAttribute("response", "Invalid email or password");
			response.setHeader("refresh", "5;url= /login");
			response.setHeader("refresh", request.getContextPath() + "/seller/login");

			return "seller/Error";
		}
	}

	@GetMapping("/forgot-pass")
	public String getForgotPasswordPage(Model map) {
		map.addAttribute("initialFlag", true);
		map.addAttribute("setPasswordFields", false);
		return "seller/forgot-password";
	}

	@PostMapping(value = "/forgot-pass", params = "forgotpassword")
	public String getForgotPasswordDetails(@RequestParam(name = "email") String email, ModelMap map,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		SellerDetailsUIVo sellerDetails = sellerService.forgotPassword(email);
		if (sellerDetails != null) {
			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", false);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", false);

			List<String> securityQuestion = new ArrayList<String>();
			List<String> securityAnswer = new ArrayList<String>();
			securityQuestion.add(sellerDetails.getSecurityQuestion1());
			securityQuestion.add(sellerDetails.getSecurityQuestion2());
			securityQuestion.add(sellerDetails.getSecurityQuestion3());
			map.addAttribute("sec_ques_list", securityQuestion);
			securityAnswer.add(sellerDetails.getSecurityAnswer1());
			securityAnswer.add(sellerDetails.getSecurityAnswer2());
			securityAnswer.add(sellerDetails.getSecurityAnswer3());
			session.setAttribute("securityQuestion", securityQuestion);
			session.setAttribute("securityAnswer", securityAnswer);
			session.setAttribute("email", email);
		} else {
			if (map.containsKey("errorMsg"))
				map.remove("errorMsg");
			map.addAttribute("errorMsg", "The email address you entered does not exist in our website.");
			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", true);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", false);
		}
		return "seller/forgot-password";
	}

	@PostMapping(value = "/forgot-pass", params = "securityques")
	public String verifysecurityAns(HttpServletRequest request, ModelMap map, HttpSession session) {
		session = request.getSession(false);
		String[] answers = request.getParameterValues("secAns");
		List<String> securityAnswer = (List<String>) session.getAttribute(("securityAnswer"));

		boolean errorsflag = false;

		String s[] = securityAnswer.toArray(new String[3]);
		if (s[0].trim().equalsIgnoreCase(answers[0]) && s[1].trim().equalsIgnoreCase(answers[1])
				&& s[2].trim().equalsIgnoreCase(answers[2])) {

			// System.out.println("converted array.." + answers[0] + answers[1] +
			// answers[2]);
			// System.out.println(securityAnswer);

			// System.out.println("inside if..");

			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", false);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", true);
			return "seller/forgot-password";
		} else {

			// System.out.println("inside else..");

			if (map.containsKey("errorMsg"))
				map.remove("errorMsg");
			map.addAttribute("errorMsg", "answer not match");
			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", false);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", false);
			map.addAttribute("sec_ques_list", session.getAttribute("securityQuestion"));
			return "seller/forgot-password";

		}
	}

	@PostMapping(value = "/forgot-pass", params = "resetpwd")
	public String resetPassword(@RequestParam(value = "newPassword") String newPassword,
			@RequestParam(value = "cfmpassword") String confirmPassword, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse resp) {
		session = request.getSession(false);

		if (newPassword.equals(confirmPassword)) {
			LoginInputVo response = sellerService.resetPassword(session.getAttribute("email").toString(), newPassword);
			System.out.println(response);
			if (null != response) {
				map.addAttribute("response", " password reset successful  " + session.getAttribute("email").toString());
				resp.setHeader("refresh", request.getContextPath() + "/seller/login");
				return "seller/PwdUpdateSucc";

			} else {

				if (map.containsKey("errorMsg"))
					map.remove("errorMsg");
				String error = "Faield to update password " + session.getAttribute("email").toString()
						+ " please try again";
				System.out.println("failure case.....");
				map.addAttribute("errorMsg", error);
				resp.setHeader("refresh", request.getContextPath() + "/seller/forgot-pass");

				return "seller/forgot-password";
			}

		} else {
			if (map.containsKey("errorMsg"))
				map.remove("errorMsg");
			map.addAttribute("errorMsg", "password not match enter again");
			System.out.println("success case...");
			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", false);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", true);
			return "seller/forgot-password";
		}
	}

//	@GetMapping("/add-category")
//	public String getloadCategory(@ModelAttribute CategoryUIVO category, HttpSession session) {
//		List<CategoryUIVO> categoriesResponseList = commonService.getAllCategories();
//		session.setAttribute("categoryList", categoriesResponseList);
//		category.setIsApproved('N');
//		return "seller/AddCategory";
//	}
//
//	@PostMapping("/add-category")
//	public String saveCategory(@ModelAttribute CategoryUIVO categoryDetail, Model model, HttpSession session,
//			HttpServletRequest request, ModelMap map) {
//		session = request.getSession(false);
//		SellerDetailsUIVo seller = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
//		categoryDetail.setSellerId(seller.getId());
//		CategoryUIVO category = sellerService.addNewCategory(categoryDetail);
//		if (category == null) {
//			model.addAttribute("error",
//					"category is already registered. Please use a different or choose existing category.");
//			return "seller/AddCategory";
//		}
//		map.addAttribute("response", " category added successfully!!!  ");
//		return "Success";
//	}

//	@GetMapping("/add-Product")
//	public String getloadPage(ModelMap map, HttpSession session) {
//		List<CategoryUIVO> categoriesResponseList = commonService.getAllCategories();
//		SellerDetailsUIVo sellerDetails = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
//		List<ProductDetailsUIVo> productList = sellerService.getShowMyProducts(sellerDetails.getId());
//		map.addAttribute("products", productList);
//		session.setAttribute("categoryList", categoriesResponseList);
//		return "seller/AddProduct";
//	}

	@GetMapping("/add-Product")
	public String getloadPage(ModelMap map, HttpSession session) {
		List<CategoryUIVO> categoriesResponseList = commonService.getAllCategories();
		SellerDetailsUIVo sellerDetails = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
		List<ProductDetailsUIVo> productList = sellerService.getShowMyProducts(sellerDetails.getId());
		map.addAttribute("products", productList);
		session.setAttribute("categoryList", categoriesResponseList);
		if (productList.isEmpty()) {
			map.addAttribute("message", "No products available.");
		}
		return "seller/AddProduct";
	}

	@PostMapping("/add-Product")
	public String saveNewProduct(@ModelAttribute("product") ProductDetailsUIVo productDetails,
			HttpServletRequest request, HttpSession session,HttpServletResponse response,
			@RequestParam(value = "prodImg") MultipartFile productImage, ModelMap map) {

		CategoryUIVO categoryDetail = sellerService.findCategory(productDetails.getCategoryId());

		session = request.getSession(false);

		// List<String>categoryListData=(List<String>)session.getAttribute(("categoryList"));
		String fileUploadResponse = commonUtil.uploadFile(categoryDetail, productImage, "productImage", session);

		if (fileUploadResponse.contains("Success"))
			productDetails.setProductImage(session.getAttribute("prd_img_path").toString());

		SellerDetailsUIVo seller = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
		productDetails.setSellerId(seller.getId());

		ProductDetailsUIVo productResponseList = sellerService.addNewProduct(productDetails);

		
		if (productResponseList != null) {

			map.addAttribute("response", "Product " + " Added successfully. Redirecting to the home page now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/seller/add-Product");
			return "Success";
		} else {
			map.addAttribute("response1", "Duplicate Product! This product already exists");
			map.addAttribute("response", "Failed to added the Product. Please try again. Redirecting now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/seller/add-Product");

			return "Error";
		}
		
		
		
		
	}

//	@GetMapping("/my-Product")
//	public String getloadMyProduct(ModelMap map, HttpSession session) {
//		List<ProductDetailsUIVo> productList = sellerService.getShowMyProducts(2);
//		map.addAttribute("products", productList);
//		// System.out.println(productList);
//		return "seller/MyProduct";
//}

	@GetMapping("/my-Product")
	public String getloadMyProduct(ModelMap map, HttpSession session) {
		
		SellerDetailsUIVo sellerDetails = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
		List<ProductDetailsUIVo> productList = sellerService.getShowMyProducts(sellerDetails.getId());
		map.addAttribute("products", productList);
		
		if (productList.isEmpty()) {
			map.addAttribute("message", "No products available.");
		}
		return "seller/MyProduct";
	}

	@GetMapping("/edit-product")
	public String editProductForm(@RequestParam("id") Integer productId, Model model) {
		ProductDetailsUIVo product = sellerService.getProductById(productId);
		model.addAttribute("product", product);
		return "seller/EditProduct";
	}

//	@PostMapping("/edit-product")
//	public String editProduct(@ModelAttribute("product") ProductDetailsUIVo productDetails, HttpServletRequest request,
//			HttpSession session, @RequestParam(value = "prodImg") MultipartFile productImage, ModelMap map) {
//
//		List<CategoryUIVO> categoryList = sellerService.getAllCategories();
//		// System.out.println(categoryList);
//		Optional<CategoryUIVO> newCategory = categoryList.stream()
//				.filter(x -> x.getId() == Integer.parseInt(productDetails.getCategoryId())).findFirst();
//		CategoryUIVO obj = newCategory.get();
//
//		String fileUploadResponse = commonUtil.uploadFile(obj, productImage, "productImage", session);
//
//		if (fileUploadResponse.contains("Success"))
//			productDetails.setProductImage(session.getAttribute("prd_img_path").toString());
//
//		ProductDetailsUIVo product = sellerService.updateProduct(productDetails);
//
//		return "seller/MyAccount";
//	}
	
	@PostMapping("/edit-product")
	public String editProduct(@ModelAttribute("product") ProductDetailsUIVo productDetails, HttpServletRequest request,
			HttpSession session, @RequestParam(value = "prodImg") MultipartFile productImage, ModelMap map) {

		List<CategoryUIVO> categoryList = sellerService.getAllCategories();
		// System.out.println(categoryList);
		
		Optional<CategoryUIVO> newCategory = categoryList.stream()
				.filter(x -> x.getId() == productDetails.getCategoryId()).findFirst();
		CategoryUIVO obj = newCategory.get();

		String fileUploadResponse = commonUtil.uploadFile(obj, productImage, "productImage", session);

		if (fileUploadResponse.contains("Success"))
			productDetails.setProductImage(session.getAttribute("prd_img_path").toString());

		ProductDetailsUIVo product = sellerService.updateProduct(productDetails);

		return "seller/MyProduct";
	}
	
	
	
	
	

//	@GetMapping("/delete-product")
//	public String deleteProduct(@RequestParam("id") Integer productId) {
//	    ProductDetailsUIVo product = sellerService.deleteProductById(productId);
//	    return "seller/MyProduct";
//	}

	@GetMapping("/delete-product")
	public String deleteProduct(@RequestParam("id") Integer productId, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		Integer deletedProduct = sellerService.deleteProductById(productId);
		if (deletedProduct > 0) {
			map.addAttribute("response", "Product " + " deleted successfully. Redirecting to the home page now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/seller/my-Product");
			return "Success";
		} else {
			map.addAttribute("response", "Failed to delete the product. Please try again. Redirecting now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/seller/my-Product");

			return "Error";
		}
	}

	@GetMapping("/add-recipe")
	public String getrecipePage(ModelMap map, HttpSession session) {
		return "seller/AddRecipe";
	}

	@PostMapping("/add-recipe")
	public String saveNewrecipe(@ModelAttribute("recipe") RecipesDetailsUIVO recipeDetails, HttpServletRequest request,
			HttpSession session, @RequestParam(value = "recpImg") MultipartFile recipeImage, ModelMap map,  
			HttpServletResponse response) {
		session = request.getSession(false);
		String fileUploadResponse = recipeUtil.uploadFile(recipeDetails, recipeImage, "recipeImage", session);
		if (fileUploadResponse.contains("Success"))
			recipeDetails.setRecipeImage(session.getAttribute("prd_img_path").toString());
		
		SellerDetailsUIVo seller = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
		recipeDetails.setSellerId(seller.getId());

		RecipesDetailsUIVO recipeResponseList = sellerService.addNewRecipe(recipeDetails);

		if (recipeResponseList != null) {

			map.addAttribute("response", "Recipes " + " Added successfully. Redirecting to the home page now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/seller/my-Recipes");
			return "Success";
		} else {
			map.addAttribute("response1", "Duplicate recipes! This product already exists");
			map.addAttribute("response", "Failed to added the Recipes. Please try again. Redirecting now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/seller/my-Recipes");

			return "Error";
		}
	}
	
	@GetMapping("/my-Recipes")
	public String loadMyRecipe(ModelMap map, HttpSession session) {
		SellerDetailsUIVo sellerDetails = (SellerDetailsUIVo) session.getAttribute("sellerDetails");
		List<RecipesDetailsUIVO> allRecipes = sellerService.getShowMyrecipe(sellerDetails.getId());
		map.addAttribute("recipes", allRecipes);
		if (allRecipes.isEmpty()) {
			map.addAttribute("message", "No products available.");
		}
		return "seller/MyRecipes";
	}
}
