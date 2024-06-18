package com.ecommerce.ui.service;

import java.util.List;

import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.UserDetailsUIVo;

public interface IUICustomerService {

	public UserDetailsUIVo checkLogin(String email, String password);

	public UserDetailsUIVo forgotPassword(String emailContactNo);

	public LoginInputVo resetPassword(String string, String newPassword);

	public UserDetailsUIVo saveUserData(UserDetailsUIVo uc);




}
