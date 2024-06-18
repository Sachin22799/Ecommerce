package com.ecommerce.common.reposetory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.common.entity.CategoryEntity;
import com.ecommerce.common.entity.ProductDetailsEntity;

@Transactional
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Integer> {
	
	public List<ProductDetailsEntity> findByProductNameLike(String productName);

	
	public List<ProductDetailsEntity>  findByIsApproved(Character isApproved);


	public List<ProductDetailsEntity> findByCategoryId(Integer categoryId);

}
