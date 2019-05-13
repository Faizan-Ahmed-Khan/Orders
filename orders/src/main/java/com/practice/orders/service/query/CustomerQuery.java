package com.practice.orders.service.query;

import com.practice.orders.model.Customer;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;

public class CustomerQuery {
	@GraphQLArguments(@GraphQLArgument(name = "id"))

	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
