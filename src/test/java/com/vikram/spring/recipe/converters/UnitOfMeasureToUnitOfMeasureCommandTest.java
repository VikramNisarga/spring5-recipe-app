package com.vikram.spring.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;
import com.vikram.spring.recipe.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	private static final Long ID = 1L;
	public static final String DESCRIPTION = "Description"; 
	public UnitOfMeasure unitOfMeasure; 
	
	@Before
	public void setUp() throws Exception {
		unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testConvert() {
		unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setDescription(DESCRIPTION);
		unitOfMeasure.setId(ID);
		
		UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);
		assertEquals(ID, unitOfMeasureCommand.getId());
		assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
		
	}
	
	@Test
	public void testNull() throws Exception {
		assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
	}
	
	@Test
	public void testEmpty() throws Exception {
		UnitOfMeasureCommand unitOfMeasureCommand= unitOfMeasureToUnitOfMeasureCommand.convert(new UnitOfMeasure());
		assertNotNull(unitOfMeasureCommand);
	}
	

}
