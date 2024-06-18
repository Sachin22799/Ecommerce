package com.ecommerce.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.customer.entity.CartDetailsEntity;
import com.ecommerce.customer.entity.ProductDetailsEntity;
import com.ecommerce.customer.entity.UserDetailsEntity;
import com.ecommerce.customer.repository.CartDetailsRepositoy;
import com.ecommerce.customer.repository.ProductDetailsRepository;
import com.ecommerce.customer.repository.UserDetailsRepositoy;
import com.ecommerce.customer.service.ICustomerService;
import com.ecommerce.customer.vo.LoginInputVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	@Autowired
	UserDetailsRepositoy userDetailsRepo;

	@Autowired
	ProductDetailsRepository productRepo;

	@Autowired
	CartDetailsRepositoy cartDetailsRepo;
	
	@Autowired
	ICustomerService customerservice;
	
	

	@GetMapping(value = "/user-login", produces = "application/json")
	public ResponseEntity<Optional<UserDetailsEntity>> sampleMethod() {
		// List<UserDetailsEntity> userList = userDetailsRepo.findAll();
		// System.out.println(userList);
		Optional<UserDetailsEntity> userDetails = userDetailsRepo.findById(2);
		return new ResponseEntity<Optional<UserDetailsEntity>>(userDetails, HttpStatus.OK);
		// return new ResponseEntity<List<UserDetailsEntity>>(userList, HttpStatus.OK);

	}

	@PostMapping(value = "/check-user-login", produces = "application/json", consumes = { "*/*" })
	public ResponseEntity<UserDetailsEntity> checkUserLogin(@RequestBody LoginInputVo loginVo) {
		UserDetailsEntity userDetails = userDetailsRepo.findByEmailAndPassword(loginVo.getEmail(),
				loginVo.getPassword());
		// List<UserDetailsEntity> userList = userDetailsRepo.findAll();
		// return new ResponseEntity<List<UserDetailsEntity>>(userList, HttpStatus.OK);

		if (userDetails != null && !StringUtils.isEmpty(userDetails.getEmail()))
			return new ResponseEntity<UserDetailsEntity>(userDetails, HttpStatus.OK);
		else
			return new ResponseEntity<UserDetailsEntity>(userDetails, HttpStatus.OK);

	}

	@GetMapping(value = "/forgot-pass", produces = "application/json")
	public ResponseEntity<UserDetailsEntity> getForgotPasswordData(
			@RequestParam(value = "email", required = false) String email) {
		log.info(email);
		String regex = "\\d+";
		String contactNo = "";
		if (email.matches(regex)) {
			contactNo = email;
			email = "";
		}

		UserDetailsEntity userDetails = userDetailsRepo.findByEmailOrContactNo(email, contactNo);
		return new ResponseEntity<UserDetailsEntity>(userDetails, HttpStatus.OK);

	}

	@PostMapping(value = "/reset-pass", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> resetUserPassword(@RequestBody LoginInputVo inputeVO) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-Type", "application/json");

		String regex = "\\d+";
		String contactNo = "";
		if (inputeVO.getEmail().matches(regex)) {
			contactNo = inputeVO.getEmail();
			inputeVO.setEmail("");
		}

		int count = userDetailsRepo.resetUserPassword(inputeVO.getEmail(), contactNo, inputeVO.getPassword());
		LoginInputVo responseVo = new LoginInputVo();
		responseVo.setResponse(count + "row is uptaded for user :" + inputeVO.getEmail());
		return new ResponseEntity<>(responseVo, HttpStatus.OK);

	}

	@PostMapping(value = "/saveUser", produces = "application/json")
	public ResponseEntity<?> saveUser(@RequestBody UserDetailsEntity ud) {

		UserDetailsEntity userdetails = userDetailsRepo.save(ud);
		System.out.println(userdetails);

		return new ResponseEntity<UserDetailsEntity>(userdetails, HttpStatus.OK);
	}

	@PostMapping(value = "/save-cart-details", produces = "application/json")
	public ResponseEntity<?> saveCartData(@RequestBody CartDetailsEntity cartDetails) {

		CartDetailsEntity savedCartDetails = cartDetailsRepo.save(cartDetails);
		// System.out.println(saveCartDetails);

		return new ResponseEntity<CartDetailsEntity>(savedCartDetails, HttpStatus.OK);
	}

	@GetMapping(value = "/get-cart-details", produces = "application/json")
	public ResponseEntity<?> getCartData(@RequestParam("userId") String userId) {

		CartDetailsEntity savedCartDetails = cartDetailsRepo.findByUserId(userId);
		// System.out.println(saveCartDetails);

		return new ResponseEntity<CartDetailsEntity>(savedCartDetails, HttpStatus.OK);
	}

//	@GetMapping("/buy-product")
//	public ResponseEntity<?> buyProduct(@RequestParam(name = "productId", required = false) Integer productId) {
//		return new ResponseEntity<Optional<CartDetailsEntity>>(customerservice.findByProductId(productId),
//				HttpStatus.OK);
//
//}
}