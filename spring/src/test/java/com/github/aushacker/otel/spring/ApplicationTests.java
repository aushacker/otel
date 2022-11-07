package com.github.aushacker.otel.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.aushacker.otel.spring.controller.HelloController;

/**
 * Test context creation.
 */
@SpringBootTest
class ApplicationTests {

	@Autowired
	private HelloController helloController;

	@Test
	void contextLoads() {
		assertNotNull(helloController);
	}

}
