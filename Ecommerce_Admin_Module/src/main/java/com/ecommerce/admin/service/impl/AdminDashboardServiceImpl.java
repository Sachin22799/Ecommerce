package com.ecommerce.admin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.entity.CategoryEntity;
import com.ecommerce.admin.entity.ProductDetailsEntity;
import com.ecommerce.admin.entity.SellerDetailsEntity;
import com.ecommerce.admin.repository.CategoryRepository;
import com.ecommerce.admin.repository.ProductRepository;
import com.ecommerce.admin.repository.SellerRepository;
import com.ecommerce.admin.service.IAdminDashboardService;

@Service
public class AdminDashboardServiceImpl  implements IAdminDashboardService{
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	SellerRepository sellerRepo;

	@Override
	public List<CategoryEntity> findByIsApproved(Character approvedFlag) {
		return categoryRepo.findByIsApproved(approvedFlag);
	}

	@Override
	public List<ProductDetailsEntity> findByIsApproved1(Character approvedFlag) {
		return productRepo.findByIsApproved(approvedFlag);
	}

	@Override
	public List<SellerDetailsEntity> findByIsApproved2(Character approvedFlag) {
		return sellerRepo.findByIsApproved(approvedFlag);
	}
	@Override
	public SellerDetailsEntity approveSeller(Integer sellerId) {
		Optional<SellerDetailsEntity> existingRecord = sellerRepo.findById(sellerId);
		SellerDetailsEntity updatedRecord = null;
		if(existingRecord.isPresent()) {
			SellerDetailsEntity setEntityData = existingRecord.get();
			setEntityData.setIsApproved('Y');
			updatedRecord = sellerRepo.saveAndFlush(setEntityData);
		}
		return updatedRecord;
	}

	@Override
	public CategoryEntity approveCategory(Integer categoryId) {
		Optional<CategoryEntity> existingRecord = categoryRepo.findById(categoryId);
		CategoryEntity updatedRecord = null;
		if(existingRecord.isPresent()) {
			CategoryEntity setEntityData = existingRecord.get();
			setEntityData.setIsApproved('Y');
			updatedRecord = categoryRepo.saveAndFlush(setEntityData);
		}
		return updatedRecord;
		
	}

	@Override
	public ProductDetailsEntity approveproduct(Integer productId) {
		
		Optional<ProductDetailsEntity> existingRecord = productRepo.findById(productId);
		ProductDetailsEntity updatedRecord = null;
		if(existingRecord.isPresent()) {
			ProductDetailsEntity setEntityData = existingRecord.get();
			setEntityData.setIsApproved('Y');
			updatedRecord = categoryRepo.saveAndFlush(setEntityData);
		}
		return updatedRecord;	
	}

	@Override
	public CategoryEntity addNewCategory(CategoryEntity categoryDetails) {
		return categoryRepo.save(categoryDetails);
	}


	@Override
	public Integer deleteCategory(CategoryEntity category) {
		return categoryRepo.removeById(category.getId());
	}

	
}
