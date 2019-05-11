package com.practice.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@Document
@GraphQLProperty(name = "products")
public class Product {
//	@GraphQLArguments({ @GraphQLArgument(name = "id"), @GraphQLArgument(name = "name"),
//			@GraphQLArgument(name = "cost") })

	@Id
	private String id;
	private String name;
	private String cost;

	@Override
	public String toString() {
		return "Product -> id :: " + id + " name:: " + name;
	}

	public Product() {
		super();
	}

	public Product(String id, String name, String cost) {
		super();
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
