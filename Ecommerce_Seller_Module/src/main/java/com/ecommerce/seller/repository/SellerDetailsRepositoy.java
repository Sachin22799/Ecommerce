package com.ecommerce.seller.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.seller.entity.SellerDetailsEntity;

@Transactional
public interface SellerDetailsRepositoy extends JpaRepository<SellerDetailsEntity, Integer> {
	 

	public SellerDetailsEntity findByEmailAndPassword(String email, String password);

	public SellerDetailsEntity findByEmailOrContactNo(String email,String contactNo);
	
	
	@Modifying
	@Query("update SellerDetailsEntity u set u.password = :password where u.email=:email or u.contactNo = :contactNo")
	public int resetSellerPassword(@Param("email")String email, @Param("contactNo") String contactNo, @Param("password")String password );
}
