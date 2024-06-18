package com.ecommerce.ui.vo;

import java.sql.Timestamp;
import java.time.Instant;

import lombok.Data;

@Data
public class RecipesDetailsUIVO {
	
	private Integer id;

	private String recipeName;
	
	private String recipeImage;
	
	private String introduction;
	
	private String requiredMaterial;
	
	private String recipeInstructions;
	
	private String note;
	
	private Character isApproved = 'Y'; //
	
	private Timestamp createdDate = Timestamp.from(Instant.now());

	private Timestamp updatedDate = Timestamp.from(Instant.now());
		
	private Integer sellerId;
}