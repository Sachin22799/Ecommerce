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

import lombok.Data;

@Entity
@Table(name = "recipes_details")

@Data
public class RecipesDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id", nullable = false)
	private Integer id;

	@Column(name = "recipe_name")
	private String recipeName;
	
	@Column(name = "recipe_image")
	private String recipeImage;
	
	@Column(name = "introduction")
	private String introduction;
	
	@Column(name = "required_material")
	private String requiredMaterial;
	
	@Column(name = "recipe_Instructions")
	private String recipeInstructions;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "is_approved")
	private Character isApproved = 'Y'; //
	
	@Column(name = "created_ts")
	private Timestamp createdDate = Timestamp.from(Instant.now());

	@Column(name = "updated_ts")
	private Timestamp updatedDate = Timestamp.from(Instant.now());
		
	@JoinColumn(name = "seller_id", referencedColumnName = "seller_id", table = "seller_details", nullable = false)
	private Integer sellerId;
}
