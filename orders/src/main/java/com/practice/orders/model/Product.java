package com.practice.orders.model;

import org.springframework.data.annotation.Id;

public class Product {

	@Id
	private String id;
	private String name;
	private String cost;

	@Override
	public String toString() {
		return "Prod -> id :: " + id + " name:: " + name;
	}

	public Product() {
	}

	public Product(String id, String name, String cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public void setName(String name) {
		this.name = name;
	}

}
