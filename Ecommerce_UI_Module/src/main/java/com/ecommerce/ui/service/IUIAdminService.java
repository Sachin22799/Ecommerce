package com.ecommerce.ui.service;

import java.util.List;

import com.ecommerce.ui.vo.AdminDetailsUIVo;
import com.ecommerce.ui.vo.CategoryUIVO;
import com.ecommerce.ui.vo.LoginInputVo;
import com.ecommerce.ui.vo.ProductDetailsUIVo;
import com.ecommerce.ui.vo.SellerDetailsUIVo;

public interface IUIAdminService {

	public List<CategoryUIVO> getAllCategories();

	public List<SellerDetailsUIVo> getAllSellers();

	public List<ProductDetailsUIVo> getAllProducts();

	public SellerDetailsUIVo approveSeller(Integer sellerId);

	public CategoryUIVO approvedCategory(Integer categoryId);

	ProductDetailsUIVo approveProduct(Integer productId);

	public AdminDetailsUIVo saveAdminData(AdminDetailsUIVo adminDetails);

	public AdminDetailsUIVo checkLogin(String email, String password);

	public LoginInputVo resetPassword(String string, String newPassword);

	public AdminDetailsUIVo forgotPassword(String emailContactNo);

	 public CategoryUIVO addNewCategory(CategoryUIVO categoryDetail);

	 public Integer deleteCategoryById(CategoryUIVO category);





}
