package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void helloWorldEndpointReturnsGreeting() {
		String response = webTestClient.get()
				.uri("/api/hello/world")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();

		Assertions.assertEquals("Hello World", response);
	}

	@Test
	void helloNameEndpointAcceptsFormData() {
		webTestClient.post()
				.uri("/api/hello/name")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData("param", "Geoff"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.isEqualTo("Hello Geoff");
	}

}
