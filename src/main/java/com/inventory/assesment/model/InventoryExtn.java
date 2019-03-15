package com.inventory.assesment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryExtn {
	
	@Id
	private String itemId;
	private String locationId;
	private Double supply;
	private Double demand;
	private Double eligibilityFactor;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient // atp will not be persisted in database
	private Double atp;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private Double promisable;
	
	public InventoryExtn(String itemId, String locationId, Double supply, Double demand, Double eligibilityFactor){
		this.itemId = itemId;
		this.locationId = locationId;
		this.supply = supply;
		this.demand = demand;
		this.eligibilityFactor = eligibilityFactor;
	}
	

}
