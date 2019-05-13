package com.practice.orders.service;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.practice.orders.model.Customer;
import com.practice.orders.model.Product;
import com.practice.orders.service.query.CustomerQuery;
import com.practice.orders.service.query.ProductQuery;
import com.practice.orders.service.query.ProductsQuery;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.exceptions.GraphQLException;

@Component
public class GraphQLClient {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

	public Product getProduct(String id) throws Exception {
		GraphQLRequestEntity requestEntity = null;
		try {
			requestEntity = GraphQLRequestEntity.Builder().url("http://localhost:9090/graphql/")
					.arguments(new Arguments("product", new Argument<String>("id", id))).request(ProductQuery.class)
					.build();
			logger.info("Request---------> {}", requestEntity);
			GraphQLResponseEntity<ProductQuery> responseEntity = graphQLTemplate.query(requestEntity,
					ProductQuery.class);
			ProductQuery ps = responseEntity.getResponse();
			logger.info("Response---------> {}", ps.getProduct());
			return ps.getProduct();
		} catch (IllegalStateException | MalformedURLException e) {
			logger.error("IllegalStateException / MalformedURLException:: ", e);
			throw e;
		} catch (Exception e) {
			logger.error("Exception:: ", e);
			throw e;
		}

	}

	public void getProducts() {
		GraphQLRequestEntity requestEntity = null;
		try {
			requestEntity = GraphQLRequestEntity.Builder().url("http://localhost:9090/graphql/")
					.request(ProductsQuery.class).build();
			logger.info("Request---------> {}", requestEntity);
			GraphQLResponseEntity<ProductsQuery> responseEntity = graphQLTemplate.query(requestEntity,
					ProductsQuery.class);
			ProductsQuery ps = responseEntity.getResponse();
			logger.info("Response---------> {}", ps.getProducts());
		} catch (IllegalStateException | MalformedURLException e) {
			logger.error("IllegalStateException / MalformedURLException:: ", e);
		} catch (Exception e) {
			logger.error("Exception:: ", e);
		}
	}

	public Customer getCustomer(String id) throws Exception {
		GraphQLRequestEntity requestEntity = null;
		try {
			requestEntity = GraphQLRequestEntity.Builder().url("http://localhost:8080/graphql/")
					.arguments(new Arguments("customer", new Argument<String>("id", id))).request(CustomerQuery.class)
					.build();
			logger.info("Request---------> {}", requestEntity);
			GraphQLResponseEntity<CustomerQuery> responseEntity = graphQLTemplate.query(requestEntity,
					CustomerQuery.class);
			CustomerQuery oq = responseEntity.getResponse();
			logger.info("Response---------> {}", oq.getCustomer());
			return oq.getCustomer();
		} catch (IllegalStateException | MalformedURLException e) {
			logger.error("IllegalStateException / MalformedURLException:: ", e);
			throw e;
		} catch (GraphQLException e) {
			logger.error("GraphQLException:: ", e.getMessage());
			throw e;
		}
	}

}
