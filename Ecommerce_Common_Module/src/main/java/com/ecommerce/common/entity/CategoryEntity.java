package com.ecommerce.common.entity;

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
@Table(name = "category")

@Data
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false)
	private Integer id;

	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "category_desc")
	private String categoryDesc;

	@Column(name = "is_approved")
	private Character isApproved = 'N'; 

	@Column(name = "created_ts")
	private Timestamp createdDate = Timestamp.from(Instant.now());

	@Column(name = "updated_ts")
	private Timestamp updatedDate = Timestamp.from(Instant.now());
	
//	@JoinColumn(name = "seller_id", referencedColumnName = "seller_id", table = "seller_details", nullable = false)
//	private Integer sellerId;

	@JoinColumn(name = "admin_id", referencedColumnName = "admin_id", table = "admin_details", nullable = false)
	private Integer adminId;


}
