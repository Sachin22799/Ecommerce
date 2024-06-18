package com.ecommerce.common.reposetory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.common.entity.ProductDetailsEntity;
import com.ecommerce.common.entity.RecipesDetailsEntity;

@Transactional
public interface RecipeDetailsRepository extends JpaRepository<RecipesDetailsEntity, Integer>  {

	List<RecipesDetailsEntity> findByIsApproved(Character flag);


	
}
