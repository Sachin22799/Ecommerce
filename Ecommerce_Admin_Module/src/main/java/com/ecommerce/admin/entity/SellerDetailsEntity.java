package com.ecommerce.admin.entity;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "seller_details", uniqueConstraints = { @UniqueConstraint(columnNames = "email_id"),
		@UniqueConstraint(columnNames = "contact_no") })
@Data
public class SellerDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id", nullable = false)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "security_ques_1")
	private String securityQuestion1;

	@Column(name = "security_ans_1")
	private String securityAnswer1;

	@Column(name = "security_ques_2")
	private String securityQuestion2;

	@Column(name = "security_ans_2")
	private String securityAnswer2;

	@Column(name = "security_ques_3")
	private String securityQuestion3;

	@Column(name = "security_ans_3")
	private String securityAnswer3;

//    @Column(name = "user_type")
//    private String userType;
//    
	@Column(name = "created_ts")
	private Timestamp createdDate = Timestamp.from(Instant.now());

	@Column(name = "updated_ts")
	private Timestamp updatedDate = Timestamp.from(Instant.now());

//    @JoinColumn(name = "product_id", referencedColumnName = "product_id", table = "product_details", nullable = false)
//    private Integer productId;

	@Column(name = "is_approved")
	private Character isApproved = 'N';

	// Constructors, getters, setters and other methods

}
