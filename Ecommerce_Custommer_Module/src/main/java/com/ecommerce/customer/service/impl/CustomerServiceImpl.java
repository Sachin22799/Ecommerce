package com.ecommerce.customer.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.customer.entity.CartDetailsEntity;
import com.ecommerce.customer.entity.ProductDetailsEntity;
import com.ecommerce.customer.repository.CartDetailsRepositoy;
import com.ecommerce.customer.repository.ProductDetailsRepository;
import com.ecommerce.customer.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	
	@Autowired
	ProductDetailsRepository productRepo;
	
	
	@Autowired
	CartDetailsRepositoy cartRepo;

//	@Override
//	public Optional<CartDetailsEntity> findByProductId(Integer productId) {
//		return cartRepo.removeByCart(productId);
//	}	
	
}

