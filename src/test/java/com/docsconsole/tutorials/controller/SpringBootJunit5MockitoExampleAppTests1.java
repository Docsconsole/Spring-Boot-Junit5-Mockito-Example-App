package com.docsconsole.tutorials.controller;

import com.docsconsole.tutorials.controllers.ProductsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootJunit5MockitoExampleAppTests1 {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	ProductsController productsController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWeb_WhenSuccessIsResult() throws Exception {
		assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/curd-app/api/testWeb", String.class)).contains("Result: Success");
	}

}


