package com.ecommerce.customer.entity;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "cart_details")
@Data
public class CartDetailsEntity {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer id;
     
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "product_id")
    private String productId;
    
    @Column(name = "cart_product_price")
    private String cartProductPrice;
     
    @Column(name = "created_ts")
    private Timestamp createdDate = Timestamp.from(Instant.now());
    
    @Column(name = "updated_ts")
    private Timestamp updatedDate = Timestamp.from(Instant.now());

	
	
}
