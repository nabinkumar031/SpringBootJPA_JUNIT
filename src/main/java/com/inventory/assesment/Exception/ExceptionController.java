package com.inventory.assesment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = InventoryNotfoundException.class)
	 public ResponseEntity<Object> exception(InventoryNotfoundException exception) {
	      return new ResponseEntity<>("Inventory not found", HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(value = DuplicateItemIdException.class)
	 public ResponseEntity<Object> exception(DuplicateItemIdException exception) {
	      return new ResponseEntity<>("Duplicate ItemId found", HttpStatus.NOT_FOUND);
	   }
	 
}
