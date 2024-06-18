package com.ecommerce.ui.vo;

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

@Data
public class CategoryUIVO {

	
	private Integer id;

	private String categoryName;

	private String categoryDesc;

	private Character isApproved = 'N'; 

	private Timestamp createdDate = Timestamp.from(Instant.now());

	private Timestamp updatedDate = Timestamp.from(Instant.now());
	
	private Integer adminId;

	



}
