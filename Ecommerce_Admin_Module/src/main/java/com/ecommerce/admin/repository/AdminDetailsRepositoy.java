package com.ecommerce.admin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.admin.entity.AdminDetailsEntity;

@Transactional
public interface AdminDetailsRepositoy extends JpaRepository<AdminDetailsEntity, Integer> {
	 

	public AdminDetailsEntity findByEmailAndPassword(String email, String password);

	public AdminDetailsEntity findByEmailOrContactNo(String email,String contactNo);
	
	
	@Modifying
	@Query("update AdminDetailsEntity u set u.password = :password where u.email=:email or u.contactNo = :contactNo")
	public int resetAdminPassword(@Param("email")String email, @Param("contactNo") String contactNo, @Param("password")String password );
}
