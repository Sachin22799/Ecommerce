package com.ecommerce.customer.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer.entity.ProductDetailsEntity;



@Transactional
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Integer> {



	

	



}
