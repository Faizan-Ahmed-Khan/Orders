package com.practice.orders.service;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.practice.orders.model.Products;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

@Component
public class GraphQLClient {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void sendRequest() {
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

		GraphQLRequestEntity requestEntity = null;
		try {
			requestEntity = GraphQLRequestEntity.Builder().url("http://localhost:9090/graphql/").request(Products.class)
					.build();
			logger.info("Request---------> " + requestEntity);
			GraphQLResponseEntity<Products> responseEntity = graphQLTemplate.query(requestEntity, Products.class);
			logger.info("Response---------> " + responseEntity);
		} catch (IllegalStateException | MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
