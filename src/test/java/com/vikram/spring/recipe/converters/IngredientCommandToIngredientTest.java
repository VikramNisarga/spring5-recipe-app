package com.vikram.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.vikram.spring.recipe.commands.IngredientCommand;
import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;
import com.vikram.spring.recipe.domain.Ingredient;

public class IngredientCommandToIngredientTest {

	private static final Long ID = 1L;
	private static final String DESCRIPTION = "description";
	IngredientCommandToIngredient ingredientCommandToIngredient;
	@Before
	public void setUp() throws Exception {
		ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void testConvertNull() throws Exception {
		Ingredient ingredient = ingredientCommandToIngredient.convert(null);
		assertNull(ingredient);
	}

	@Test
	public void testConvertEmpty() throws Exception {
		Ingredient ingredient = ingredientCommandToIngredient.convert(new IngredientCommand());
		assertNotNull(ingredient);
	}
	@Test
	public void testConvert() {
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setAmount(BigDecimal.ONE);
		ingredientCommand.setDescription(DESCRIPTION);
		ingredientCommand.setId(ID);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(ID);
		uomCommand.setDescription(DESCRIPTION);
		ingredientCommand.setUomCommand(uomCommand);
		
		Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
		assertEquals(ID, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertNotNull(ingredient.getUom());
		assertEquals(ID, ingredient.getId());
	}

}
