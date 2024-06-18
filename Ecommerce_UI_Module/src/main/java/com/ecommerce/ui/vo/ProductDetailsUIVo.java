package com.ecommerce.ui.vo;

import java.sql.Timestamp;
import java.time.Instant;


import lombok.Data;

@Data
public class ProductDetailsUIVo {

	private Integer id;

	private String productName;

	private String productDesc;

	private String productImage;

	private String purchasingPrice;

	private String sellingPrice;

	private String availableQty;

	private String minQty;
	
	private String productInfo;

	private Integer categoryId;

	private Timestamp createdDate = Timestamp.from(Instant.now());

	private Timestamp updatedDate = Timestamp.from(Instant.now());

	private Integer sellerId;
	
	private Character isApproved = 'N'; 

}
