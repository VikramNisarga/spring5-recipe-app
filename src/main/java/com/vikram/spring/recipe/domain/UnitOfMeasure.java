package com.vikram.spring.recipe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author vikram.nisarga
 *
 */
@Entity
public class UnitOfMeasure {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUom() {
		return description;
	}
	public void setUom(String uom) {
		this.description = uom;
	}
	
	
}
