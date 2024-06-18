package com.ecommerce.seller.entity;

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
@Table(name = "product_details")

@Data
public class ProductDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", nullable = false)
	private Integer id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_desc")
	private String productDesc;

	@Column(name = "product_image")
	private String productImage;

	@Column(name = "purchasing_price")
	private String purchasingPrice;

	@Column(name = "selling_price")
	private String sellingPrice;

	@Column(name = "available_qty")
	private String availableQty;

	@Column(name = "min_qty")
	private String minQty;

	@Column(name = "product_info")
	private String productInfo;

	@JoinColumn(name = "category_id", referencedColumnName = "category_id", table = "category", nullable = false)
	private String categoryId;

	@Column(name = "created_ts")
	private Timestamp createdDate = Timestamp.from(Instant.now());

	@Column(name = "updated_ts")
	private Timestamp updatedDate = Timestamp.from(Instant.now());
	
	@Column(name = "is_approved")
	private Character isApproved = 'N';
	
	@JoinColumn(name = "seller_id", referencedColumnName = "seller_id", table = "seller_details", nullable = false)
	private Integer sellerId;

}
