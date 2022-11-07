package com.github.aushacker.otel.spring.controller;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
	private MockMvc mockMvc;

	@Test
	public void returnsBasicMessage() throws Exception {
		mockMvc.perform(get("/hello")).
            //andDo(print()).
            andExpect(status().isOk()).
			andExpect(content().contentType("text/plain;charset=UTF-8")).
            andExpect(content().string(containsString("Hello world")));
	}
}
