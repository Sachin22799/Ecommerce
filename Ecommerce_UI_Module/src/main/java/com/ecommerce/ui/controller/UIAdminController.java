package com.ecommerce.ui.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.ui.service.IUIAdminService;
import com.ecommerce.ui.service.IUICommonService;
import com.ecommerce.ui.utils.SecurityQuestion;
import com.ecommerce.ui.vo.AdminDetailsUIVo;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.SellerDetailsUIVo;

@Controller
@RequestMapping("/admin")
public class UIAdminController {

	@Autowired
	IUIAdminService adminService;

	@Autowired
	IUICommonService commonService;

	

	@GetMapping("/")
	public String loadHomePage() {		
		return "index";
	}
	
	@GetMapping("/dashbord")
	public String loadHomePage(ModelMap map) {
		List<CategoryUIVO> categoriesResponseList = adminService.getAllCategories();
		List<SellerDetailsUIVo> sellerResponseList = adminService.getAllSellers();
		List<ProductDetailsUIVo> productResponseList = adminService.getAllProducts();

		map.addAttribute("categoryList", categoriesResponseList);

		map.addAttribute("sellerlist", sellerResponseList);

		map.addAttribute("productlist", productResponseList);
		
		return "Admin/dashboard";

	}

	@GetMapping("/approveSeller")
	public String ApproveSeller(@RequestParam("sellerId") Integer sellerId, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		SellerDetailsUIVo approvedSeller = adminService.approveSeller(sellerId);
		if (null != approvedSeller) {
			if (map.containsKey("sellerFirstName"))
				map.remove("sellerFirstName");
			map.addAttribute("response",
					"Seller " + approvedSeller.getFirstName() + " approved successfully, redirecting to home page now");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/dashbord");
			return "Success";
		} else {
			map.addAttribute("response", "Failed to approve the seller, please try again . . . redirecting now");
			return "Success";
		}

	}

	@GetMapping("/approveCategory")
	public String ApproveCategory(@RequestParam("categoryId") Integer categoryId, ModelMap map,
			HttpServletRequest request, HttpServletResponse response) {
		CategoryUIVO approvedCategory = adminService.approvedCategory(categoryId);
		if (null != approvedCategory) {
			if (map.containsKey("categoryName"))
				map.remove("categoryName");
			map.addAttribute("response", " Category " + approvedCategory.getCategoryName()
					+ " approved successfully, redirecting to home page now");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/dashbord");
			return "Success";
		} else {
			map.addAttribute("response", "Failed to approve the category, please try again . . . redirecting now");
			return "Success";
		}
	}

	@GetMapping("/approveProduct")
	public String ApproveProduct(@RequestParam("productId") Integer productId, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		ProductDetailsUIVo approvedProduct = adminService.approveProduct(productId);
		if (null != approvedProduct) {
			if (map.containsKey("productName"))
				map.remove("productName");
			map.addAttribute("response", "Product " + approvedProduct.getProductName()
					+ " approved successfully, redirecting to home page now");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/dashbord");
			return "Success";
		} else {
			map.addAttribute("response", "Failed to approve the Product, please try again . . . redirecting now");
			return "Success";
		}
	}

	// =============================================================================================================================================================

	@GetMapping("/Registration")
	public String loadSellerRegistrationpage(ModelMap map) {
		List<String> Securityques = Stream.of(SecurityQuestion.values()).map(SecurityQuestion::toString)
				.collect(Collectors.toList());
		map.addAttribute("SecurityQuestion", Securityques);
//			for(String data:Securityques) {
//				System.out.println(data);
//			}
		return "Admin/Admin_Registration";
	}

	@PostMapping("/Registration")
	public String saveData(@ModelAttribute AdminDetailsUIVo adminDetails, Model model) {
		AdminDetailsUIVo admin = adminService.saveAdminData(adminDetails);

		return "Admin/Admin_Login";
	}

	@GetMapping("/logout")
	public String getlogoutPage(ModelMap map) {
		return "Admin/Admin_Login";
	}

	@GetMapping("/login")
	public String getloginPage(ModelMap map) {

		return "Admin/Admin_Login";
	}

	@PostMapping("/login")
	public String checklogin(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		AdminDetailsUIVo adminDetails = adminService.checkLogin(email, password);
		if (null != adminDetails && null != adminDetails.getEmail()) {
			map.put("response1", adminDetails.getFirstName());
			map.put("response2", adminDetails.getLastName());
			map.put("phone", adminDetails.getContactNo());

			session.setAttribute("email", email);
			session.setAttribute("adminDetails", adminDetails);
			response.setHeader("refresh", request.getContextPath() + "/admin/login");

			return "Admin/MyAccount";

		} else {
			map.addAttribute("response", "Invalid email or password");
			response.setHeader("refresh", "5;url= /login");
			response.setHeader("refresh", request.getContextPath() + "/admin/login");

			return "Admin/Error";
		}
	}

	@GetMapping("/forgot-pass")
	public String getForgotPasswordPage(Model map) {
		map.addAttribute("initialFlag", true);
		map.addAttribute("setPasswordFields", false);
		return "Admin/forgot-password";
	}

	@PostMapping(value = "/forgot-pass", params = "forgotpassword")
	public String getForgotPasswordDetails(@RequestParam(name = "email") String email, ModelMap map,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		AdminDetailsUIVo adminDetails = adminService.forgotPassword(email);
		if (adminDetails != null) {
			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", false);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", false);

			List<String> securityQuestion = new ArrayList<String>();
			List<String> securityAnswer = new ArrayList<String>();
			securityQuestion.add(adminDetails.getSecurityQuestion1());
			securityQuestion.add(adminDetails.getSecurityQuestion2());
			securityQuestion.add(adminDetails.getSecurityQuestion3());
			map.addAttribute("sec_ques_list", securityQuestion);
			securityAnswer.add(adminDetails.getSecurityAnswer1());
			securityAnswer.add(adminDetails.getSecurityAnswer2());
			securityAnswer.add(adminDetails.getSecurityAnswer3());
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
		return "Admin/forgot-password";
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
			return "Admin/forgot-password";
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
			return "Admin/forgot-password";

		}
	}

	@PostMapping(value = "/forgot-pass", params = "resetpwd")
	public String resetPassword(@RequestParam(value = "newPassword") String newPassword,
			@RequestParam(value = "cfmpassword") String confirmPassword, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse resp) {
		session = request.getSession(false);

		if (newPassword.equals(confirmPassword)) {
			LoginInputVo response = adminService.resetPassword(session.getAttribute("email").toString(), newPassword);
			if (null != response) {
				map.addAttribute("response", " password reset successful  " + session.getAttribute("email").toString());
				resp.setHeader("refresh", request.getContextPath() + "/admin/login");
				return "Admin/PwdUpdateSucc";

			} else {

				if (map.containsKey("errorMsg"))
					map.remove("errorMsg");
				String error = "Faield to update password " + session.getAttribute("email").toString()
						+ " please try again";
				System.out.println("failure case.....");
				map.addAttribute("errorMsg", error);
				resp.setHeader("refresh", request.getContextPath() + "/admin/forgot-pass");

				return "Admin/forgot-password";
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
			return "Admin/forgot-password";
		}
	}

	@GetMapping("/add-category")
	public String getloadCategory(@ModelAttribute CategoryUIVO category, HttpSession session) {
		List<CategoryUIVO> categoriesResponseList = commonService.getAllCategories();
		session.setAttribute("categoryList", categoriesResponseList);
		category.setIsApproved('N');
		return "Admin/AddCategory";
	}

	@PostMapping("/add-category")
	public String saveCategory(@ModelAttribute CategoryUIVO categoryDetail, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		session = request.getSession(false);

		AdminDetailsUIVo admin = (AdminDetailsUIVo) session.getAttribute("adminDetails");
		categoryDetail.setAdminId(admin.getId());

		CategoryUIVO category = adminService.addNewCategory(categoryDetail);

		if (category != null) {
			map.addAttribute("response", " category added successfully!!! ");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/add-category");
			return "Success";
		} else {
			map.addAttribute("response", "Failed to added the category, please try again . . . redirecting now");
			return "Success";
		}	
	 }

//	@GetMapping("/delete-category")
//	public String deleteProduct(@RequestParam("id") Integer categoryIdId, ModelMap map, HttpServletRequest request,
//			HttpServletResponse response) {
//		Integer deletedCategory = adminService.deleteCategoryById(categoryIdId);
//		if (deletedCategory > 0) {
//			map.addAttribute("response", "category " + " deleted successfully. Redirecting to the home page now.");
//			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/add-category");
//			return "Success";
//		} else {
//			map.addAttribute("response", "Failed to delete the category. Please try again. Redirecting now.");
//			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/add-category");
//
//			return "Error";
//		}
//	}
	
	@GetMapping("/delete-category")
	public String deleteCategory(@RequestParam ("categoryId") Integer categoryId , ModelMap map,HttpServletRequest request,
		HttpServletResponse response	) {
		CategoryUIVO category = new CategoryUIVO();
		category.setId(categoryId);
		Integer deleteCategory = adminService.deleteCategoryById(category);
		if (deleteCategory > 0) {
			map.addAttribute("response", "category " + " deleted successfully. Redirecting to the home page now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/add-category");
			return "Success";
		} else {
			map.addAttribute("response", "Failed to delete the product. Please try again. Redirecting now.");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/admin/add-category");

			return "Error";
		}
	
	}
}
