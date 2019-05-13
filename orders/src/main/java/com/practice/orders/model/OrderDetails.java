package com.practice.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderDetails {

	@Id
	private String id;
	private String productId;
	private Product product;
	private Customer customer;

	private String customerId;

	@Override
	public String toString() {
		return "Order -> id :: " + id + " productId:: " + productId + " customerId:: " + customerId;
	}

	public OrderDetails(String id, String productId, String customerId, Product p, Customer c) {
		this.id = id;
		this.customerId = customerId;
		this.productId = productId;
		this.product = p;
		this.customer = c;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
