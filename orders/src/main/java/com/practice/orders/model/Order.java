package com.practice.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document
@Builder
@Data
public class Order {

	@Id
	private String id;
	private String productId;
	private String customerId;

}
