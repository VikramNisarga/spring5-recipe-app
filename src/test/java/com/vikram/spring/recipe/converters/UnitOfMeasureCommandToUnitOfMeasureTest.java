package com.vikram.spring.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;
import com.vikram.spring.recipe.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
	
	UnitOfMeasureCommand unitOfMeasureCommand; 
	private static final Long ID = 1L;
	public static final String DESCRIPTION = "Description"; 
	private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
	
	@Before
	public void setUp() throws Exception {
		unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testConvert() {
		unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setDescription(DESCRIPTION);
		unitOfMeasureCommand.setId(ID);
		
		UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);
		assertEquals(ID, unitOfMeasure.getId());
		assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
		
	}
	
	@Test
	public void testNull() throws Exception {
		assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));
	}
	
	@Test
	public void testEmpty() throws Exception {
		UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand());
		assertNotNull(unitOfMeasure);
	}

}
