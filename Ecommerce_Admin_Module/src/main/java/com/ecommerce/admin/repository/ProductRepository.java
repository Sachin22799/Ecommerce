 package com.ecommerce.admin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.admin.entity.CategoryEntity;
import com.ecommerce.admin.entity.ProductDetailsEntity;


@Transactional
public interface ProductRepository extends JpaRepository<ProductDetailsEntity, Integer>  {

	List<ProductDetailsEntity> findByIsApproved(Character approvedFlag);

	
}
