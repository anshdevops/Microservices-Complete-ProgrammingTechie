package com.programmingtechie.productservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.repos.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class ProductServiceApplicationTests {


	@Autowired
	private MockMvc mockMvc;



	@Autowired
	private ObjectMapper mapper;
	@Autowired
	ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:6.0.4");



	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry properties){
		properties.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}



	@Test
	void shouldCreateProduct() throws Exception {

		 ProductRequest request=getProductRequest();
		 String requestInString=mapper.writeValueAsString(request);
		 mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				 .contentType(MediaType.APPLICATION_JSON)
						 .content(requestInString))
				 .andExpect(status().isCreated());

		assert (productRepository.findAll().size()==1);




	}

	private ProductRequest getProductRequest() {

		return ProductRequest.builder()
				.price(BigDecimal.valueOf(1200))
				.name("Iphone 13")
				.description("Costly")
				.build();
	}

}
