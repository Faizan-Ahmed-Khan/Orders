package com.practice.orders.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

@Component
public class GraphQLClient {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

	@SuppressWarnings("rawtypes")
	public <T> Object sendRequest(T inputClass, String url, Map<String, String> hm, String... arg) throws Exception {
		GraphQLRequestEntity requestEntity = null;
		if (hm.size() == 0) {
			requestEntity = GraphQLRequestEntity.Builder().url(url).request(inputClass.getClass()).build();
			logger.info("Request---------> {}", requestEntity);
		} else {
			List<Argument> arguments = new ArrayList<>();
			Iterator<String> itr = hm.keySet().iterator();
			while (itr.hasNext()) {
				String key = itr.next();
				arguments.add(new Argument<String>(key, hm.get(key)));
			}
			requestEntity = GraphQLRequestEntity.Builder().url(url).arguments(new Arguments(arg[0], arguments))
					.request(inputClass.getClass()).build();
			logger.info("Request---------> {}", requestEntity);
		}
		GraphQLResponseEntity<? extends Object> responseEntity = graphQLTemplate.query(requestEntity,
				inputClass.getClass());
		return responseEntity.getResponse();

	}

}
