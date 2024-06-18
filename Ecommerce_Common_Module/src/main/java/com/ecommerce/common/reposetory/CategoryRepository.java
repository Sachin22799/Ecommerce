package com.ecommerce.common.reposetory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.common.entity.CategoryEntity;

@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>  {

	
	public List<CategoryEntity>  findByIsApproved(Character isApproved);
}
