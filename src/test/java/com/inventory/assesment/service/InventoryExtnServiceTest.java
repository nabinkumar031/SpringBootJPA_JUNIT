package com.inventory.assesment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inventory.assesment.Exception.InventoryNotfoundException;
import com.inventory.assesment.model.InventoryExtn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryExtnServiceTest {
	
	@Mock
	private InventoryExtnRepository repository;
	
	@InjectMocks InventoryExtnService inventoryExtnService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void true_testInventoryExtnExist() {
		when(repository.existsById("100")).thenReturn(true);
		assertEquals(Boolean.TRUE,inventoryExtnService.inventoryExtnExist("100"));
	}
	
	@Test
	public void false_testInventoryExtnExist() {
		when(repository.existsById("100")).thenReturn(false);
		assertEquals(Boolean.FALSE,inventoryExtnService.inventoryExtnExist("100"));
	}
	
	@Test
	public void testUpdateInventoryExtn() {
		// Given
		InventoryExtn invExtn = new InventoryExtn("100", "Deoghar", Double.valueOf("200"), 
				Double.valueOf("100"),Double.valueOf("0.4"));
		when(repository.existsById("100")).thenReturn(true);
		when(repository.save(invExtn)).thenReturn(invExtn);
		assertEquals(invExtn,inventoryExtnService.updateInventoryExtn(invExtn));
	}
	
	@Test(expected = InventoryNotfoundException.class)
	public void exception_testUpdateInventoryExtn() {
		// Given
		InventoryExtn invExtn = new InventoryExtn("100", "Deoghar", Double.valueOf("200"), 
				Double.valueOf("100"),Double.valueOf("0.4"));
		when(repository.existsById("100")).thenReturn(false);
		inventoryExtnService.updateInventoryExtn(invExtn);
	}
	

	
	@Test(expected = InventoryNotfoundException.class)
	public void exception_testDeleteInvtoryExtnByItemId() {
		when(repository.existsById("100")).thenReturn(false);
		inventoryExtnService.deleteInvtoryExtnByItemId("100");
	}
	
	

}
