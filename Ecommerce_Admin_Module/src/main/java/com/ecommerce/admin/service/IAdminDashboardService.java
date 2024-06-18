package com.ecommerce.admin.service;

import java.util.List;

import com.ecommerce.admin.entity.CategoryEntity;
import com.ecommerce.admin.entity.ProductDetailsEntity;
import com.ecommerce.admin.entity.SellerDetailsEntity;

public interface IAdminDashboardService {

	public List<CategoryEntity> findByIsApproved(Character approvedFlag);

	public List<ProductDetailsEntity> findByIsApproved1(Character approvedFlag);

	public List<SellerDetailsEntity> findByIsApproved2(Character capprovedFlag);

	public SellerDetailsEntity approveSeller(Integer sellerId);

	public CategoryEntity approveCategory(Integer categoryId);

	public ProductDetailsEntity approveproduct(Integer productId);

	public CategoryEntity addNewCategory(CategoryEntity categoryDetails);

	public Integer deleteCategory(CategoryEntity category);
}
