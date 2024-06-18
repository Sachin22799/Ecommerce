package com.ecommerce.ui.vo;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;

import lombok.Data;


@Data
public class SellerDetailsUIVo {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String contactNo;
	
	private String Address;
	
	private Integer pincod;
	
	private String securityQuestion1;
	
	private String securityAnswer1;
	
	private String securityQuestion2;
	
	private String securityAnswer2;
	
	private String securityQuestion3;
	
	private String securityAnswer3;
		
	private Timestamp createdDate =Timestamp.from(Instant.now());
	
	private Timestamp updatedDate =Timestamp.from(Instant.now());
    
	private Character isApproved = 'N'; 

}
