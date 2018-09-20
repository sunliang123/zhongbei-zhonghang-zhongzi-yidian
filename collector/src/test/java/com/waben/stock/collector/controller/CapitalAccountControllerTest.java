package com.waben.stock.collector.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 资金账户 Test
 * 
 * @author luomengan
 *
 */
public class CapitalAccountControllerTest extends BaseControllerTest {

	@Test
	public void fetchById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/capitalAccount/{id}", 1).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.result.id").value("1"));
	}

	@Test
	public void addition() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/capitalAccount/")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}

	@Test
	public void modification() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/capitalAccount/")
				.param("id", "10")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result.id").value(10));
	}
	
	@Test
	public void delete() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/capitalAccount/{id}", 10)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result").value(10));
	}
	
	@Test
	public void capitalAccounts() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/capitalAccount/page")
				.param("page", "0")
				.param("limit", "10")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result.size").isNumber());
	}
	
}