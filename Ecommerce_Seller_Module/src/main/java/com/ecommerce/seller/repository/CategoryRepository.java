package com.ecommerce.seller.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.seller.entity.CategoryEntity;



@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>  {

	
	public List<CategoryEntity>  findByIsApproved(Character isApproved);
	
	//public CategoryEntity  findById(Integer categoryId);





}
 