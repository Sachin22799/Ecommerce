  package com.ecommerce.seller.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.seller.entity.CategoryEntity;
import com.ecommerce.seller.entity.ProductDetailsEntity;

 
@Transactional
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Integer> {
	
	public CategoryEntity  findByCategoryId(String categoryId);

	public List<ProductDetailsEntity> findById(ProductDetailsEntity productDetails);
	
	public List<ProductDetailsEntity> findBySellerId(Integer sellerId);

	public ProductDetailsEntity findAllById(ProductDetailsEntity productDetails);
	
	public Optional<ProductDetailsEntity> findById(Integer productId);
	
    public Integer removeById(Integer productId );
 

}
