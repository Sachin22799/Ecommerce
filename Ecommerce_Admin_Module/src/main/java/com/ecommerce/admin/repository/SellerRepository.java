 package com.ecommerce.admin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.admin.entity.CategoryEntity;
import com.ecommerce.admin.entity.SellerDetailsEntity;


@Transactional
public interface SellerRepository extends JpaRepository<SellerDetailsEntity, Integer>  {

	List<SellerDetailsEntity> findByIsApproved(Character approvedFlag);

	
}
