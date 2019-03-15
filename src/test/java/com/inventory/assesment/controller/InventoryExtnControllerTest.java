package com.inventory.assesment.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.inventory.assesment.model.InventoryExtn;
import com.inventory.assesment.service.InventoryExtnService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryExtnControllerTest {

	@Mock
    private InventoryExtnService mockService;
	
	@InjectMocks
	private InventoryExtnController inventoryExtnController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(inventoryExtnController)
				.build();
	}

	@Test
	public void testGetInventoryExtnByItemId() throws Exception {

		// Given
		InventoryExtn invExtn = new InventoryExtn("100", "Deoghar", Double.valueOf("200"), Double.valueOf("100"),
				Double.valueOf("0.4"));

		// When
		when(mockService.getInvtoryExtnByItemId("100")).thenReturn(invExtn);

		mockMvc.perform(get("/getinv/100").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.itemId", Matchers.is("100")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.locationId", Matchers.is("Deoghar")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.supply", Matchers.is(200.0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.demand", Matchers.is(100.0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.eligibilityFactor", Matchers.is(0.4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(5)));

		verify(mockService, times(1)).getInvtoryExtnByItemId("100");
		verifyNoMoreInteractions(mockService);
		
	}
	
	@Test
	public void testCreateInventoryExtn() throws Exception {
		// Given
		InventoryExtn invExtn = new InventoryExtn("100", "Deoghar", Double.valueOf("200"), Double.valueOf("100"),
				Double.valueOf("0.4"));
		// When
		when(mockService.addInventoryExtn(invExtn)).thenReturn(invExtn);

		//Verify
		mockMvc.perform(post("/addinv/")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"itemId\":\"100\",\"locationId\":\"Deoghar\",\"supply\":\"200\",\"demand\":\"100\",\"eligibilityFactor\":\"0.4\"}")
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.itemId", Matchers.is("100")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.locationId", Matchers.is("Deoghar")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.supply", Matchers.is(200.0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.demand", Matchers.is(100.0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.eligibilityFactor", Matchers.is(0.4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(5)));

		verify(mockService, times(1)).addInventoryExtn(invExtn);
		verifyNoMoreInteractions(mockService);
		
	}
	
	@Test
	public void testDeleteInventoryExtnByItemId() throws Exception {
		mockMvc.perform(get("/deleteinv/100")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	

}
