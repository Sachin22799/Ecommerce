 package com.ecommerce.admin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.admin.entity.CategoryEntity;
import com.ecommerce.admin.entity.ProductDetailsEntity;


@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>  {

	

	public List<CategoryEntity>  findByIsApproved(Character isApproved);


	public ProductDetailsEntity saveAndFlush(ProductDetailsEntity setEntityData);


	public Integer removeById(Integer categoryId);
}
	