package com.ecommerce.customer.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.customer.entity.UserDetailsEntity;

@Transactional
public interface UserDetailsRepositoy extends JpaRepository<UserDetailsEntity, Integer> {
	 

	public UserDetailsEntity findByEmailAndPassword(String email, String password);

	public UserDetailsEntity findByEmailOrContactNo(String email,String contactNo);
	
	
	@Modifying
	@Query("update UserDetailsEntity u set u.password = :password where u.email=:email or u.contactNo = :contactNo")
	public int resetUserPassword(@Param("email")String email, @Param("contactNo") String contactNo, @Param("password")String password );

}
