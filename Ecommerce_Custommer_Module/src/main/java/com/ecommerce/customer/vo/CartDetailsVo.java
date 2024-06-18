package com.ecommerce.customer.vo;

import java.sql.Timestamp;
import java.time.Instant;


import lombok.Data;

@Data
public class CartDetailsVo {
	
	private Integer id;

	private String userId;

	private String productId;

	private String cartProductPrice;

	private Timestamp createdDate = Timestamp.from(Instant.now());

	private Timestamp updatedDate = Timestamp.from(Instant.now());
}
