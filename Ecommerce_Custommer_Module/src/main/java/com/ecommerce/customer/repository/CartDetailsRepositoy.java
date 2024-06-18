package com.ecommerce.customer.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.customer.entity.CartDetailsEntity;
import com.ecommerce.customer.entity.UserDetailsEntity;

@Transactional
public interface CartDetailsRepositoy extends JpaRepository<CartDetailsEntity, Integer> {

  public CartDetailsEntity findByUserId(String userId);
  
	//Optional<CartDetailsEntity> removeByCart(Integer productId);


}
