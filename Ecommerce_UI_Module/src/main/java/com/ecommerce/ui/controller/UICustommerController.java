package com.ecommerce.ui.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.ui.service.IUICommonService;
import com.ecommerce.ui.service.IUICustomerService;
import com.ecommerce.ui.service.impl.UICustomerServiceImpl;
import com.ecommerce.ui.utils.SecurityQuestion;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.UserDetailsUIVo;

@Controller
@RequestMapping("/user")
public class UICustommerController {

	@Autowired
	IUICustomerService commonCustomerService;
	
	@GetMapping("/")
	public String loadHomePage() {
		return "index";
	}

	@GetMapping("/Registration")
	public String loadUserRegistrationpage(ModelMap map) {
		List<String> Securityques = Stream.of(SecurityQuestion.values()).map(SecurityQuestion::toString)
				.collect(Collectors.toList());
		map.addAttribute("SecurityQuestion", Securityques);
//		for(String data:Securityques) {
//			System.out.println(data);
//		}
		return "user/User_Registration";
	}

	@PostMapping("/Registration")
	public String saveData(@ModelAttribute UserDetailsUIVo uc) {
		System.out.println("User Data:" + uc);
		uc.setUserType("N");
		UserDetailsUIVo user = commonCustomerService.saveUserData(uc);
		System.out.println("user:" + user);
		return "user/User_Login";
	}

	
	@GetMapping("/logout")
	public String logoutUser(ModelMap map, HttpSession session,HttpServletRequest request, HttpServletResponse response) {
	
		Enumeration<String> sessionList = session.getAttributeNames();
		
		while(sessionList.hasMoreElements()) {
			session.removeAttribute(sessionList.nextElement());
		}
		session.invalidate();
		

		 return "index";

	}
	
	
	
	@GetMapping("/login")
	public String getloginPage(ModelMap map) {
		return "user/User_Login";
	}

	@PostMapping("/login")
	public String checklogin(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		UserDetailsUIVo userDetails = commonCustomerService.checkLogin(email, password);
		if (null != userDetails && null != userDetails.getEmail()) {
			map.put("response", userDetails.getFirstName());
			session.setAttribute("email", email);
			response.setHeader("refresh", request.getContextPath() + "/home/");

			return "index";
			// return "user/Success";

		} else {
			map.addAttribute("response", "Invalid email or password");
			response.setHeader("refresh", "5;url= /login");
			response.setHeader("refresh", request.getContextPath() + "/user/login");

			return "user/Error";
		}
	}

	@GetMapping("/forgot-pass")
	public String getForgotPasswordPage(Model map) {
		map.addAttribute("initialFlag", true);
		map.addAttribute("setPasswordFields", false);
		return "user/forgot-password";
	}

	@PostMapping(value = "/forgot-pass", params = "forgotpassword")
	public String getForgotPasswordDetails(@RequestParam(name = "email") String email, ModelMap map,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		UserDetailsUIVo userDetails = commonCustomerService.forgotPassword(email);
		if (userDetails != null) {
			if (map.containsKey("initialFlag"))
				map.remove("initialFlag");
			map.addAttribute("initialFlag", false);
			if (map.containsKey("setPasswordFields"))
				map.remove("setPasswordFields");
			map.addAttribute("setPasswordFields", false);

			List<String> securityQuestion = new ArrayList<String>();
			List<String> securityAnswer = new ArrayList<String>();
			securityQuestion.add(userDetails.getSecurityQuestion1());
			securityQuestion.add(userDetails.getSecurityQuestion2());
			securityQuestion.add(userDetails.getSecurityQuestion3());
			map.addAttribute("sec_ques_list", securityQuestion);
			securityAnswer.add(userDetails.getSecurityAnswer1());
			securityAnswer.add(userDetails.getSecurityAnswer2());
			securityAnswer.add(userDetails.getSecurityAnswer3());
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
		return "user/forgot-password";
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
			return "user/forgot-password";
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
			return "user/forgot-password";

		}
	}

	@PostMapping(value = "/forgot-pass", params = "resetpwd")
	public String resetPassword(@RequestParam(value = "newPassword") String newPassword,
			@RequestParam(value = "cfmpassword") String confirmPassword, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse resp) {
		session = request.getSession(false);

		if (newPassword.equals(confirmPassword)) {
			LoginInputVo response = commonCustomerService.resetPassword(session.getAttribute("email").toString(),
					newPassword);
			System.out.println(response);
			if (null != response) {
				map.addAttribute("response", " password reset successful  " + session.getAttribute("email").toString());
				resp.setHeader("refresh", request.getContextPath() + "/user/login");
				return "user/PwdUpdateSucc";

			} else {

				if (map.containsKey("errorMsg"))
					map.remove("errorMsg");
				String error = "Faield to update password " + session.getAttribute("email").toString()
						+ " please try again";
				System.out.println("failure case.....");
				map.addAttribute("errorMsg", error);
				resp.setHeader("refresh", request.getContextPath() + "/user/forgot-pass");

				return "user/forgot-password";
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
			return "user/forgot-password";
		}
	}
}
