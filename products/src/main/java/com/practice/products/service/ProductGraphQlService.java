package com.practice.products.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.products.model.Product;
import com.practice.products.repository.IProductRepo;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi // impose this service for GraphQL
public class ProductGraphQlService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IProductRepo prodRepo;

	@GraphQLQuery(name = "products")
	public List<Product> findAllProducts() {
		return prodRepo.findAll();
	}

	@GraphQLQuery(name = "product")
	public Product findProductById(String id) {
		Optional<Product> p = prodRepo.findById(id);
		if (!p.isPresent())
			throw new RuntimeException("Product doesn't exist with given id " + id);
		return p.get();
	}

	@GraphQLQuery(name = "product")
	public Product findProductByName(String name) {
		Optional<Product> p = prodRepo.findByName(name);
		if (!p.isPresent())
			throw new RuntimeException("Product doesn't exist with given name " + name);
		return p.get();
	}

	@GraphQLMutation(name = "createProduct")
	public Product saveProduct(String id, String name, String cost) {
		return prodRepo.save(new Product(id, name, cost));
	}

	@GraphQLMutation(name = "updateProduct")
	public Product updateProduct(String name, String cost) {
		Optional<Product> p = prodRepo.findByName(name);
		if (!p.isPresent())
			throw new RuntimeException("Product doesn't exist with given name " + name);

		Product prod = p.get();
		prod.setCost(cost);
		return prodRepo.save(prod);
	}

	@GraphQLMutation(name = "deleteProduct")
	public String deleteProduct(String name) {
		Optional<Product> p = prodRepo.findByName(name);
		if (!p.isPresent())
			throw new RuntimeException("Product doesn't exist with given name " + name);

		prodRepo.delete(p.get());
		return "Deleted Product Successfully";
	}

	@GraphQLMutation(name = "deleteProducts")
	public String deleteAll() {
		prodRepo.deleteAll();
		;
		return "All Products Deleted Successfully";
	}

}
