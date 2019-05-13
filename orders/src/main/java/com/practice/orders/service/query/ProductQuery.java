package com.practice.orders.service.query;

import com.practice.orders.model.Product;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

public class ProductQuery {
	@GraphQLArguments({ @GraphQLArgument(name = "id", optional = true),
			@GraphQLArgument(name = "name", optional = true) })

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
