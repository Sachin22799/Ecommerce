package com.ecommerce.seller.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.seller.entity.RecipesDetailsEntity;

@Transactional
public interface RecipesRepository extends JpaRepository<RecipesDetailsEntity, Integer>  {

public	List<RecipesDetailsEntity> findBySellerId(Integer sellerId);

	
	




}
 