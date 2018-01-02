package com.vikram.spring.recipe.converters;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.vikram.spring.recipe.commands.IngredientCommand;
import com.vikram.spring.recipe.domain.Ingredient;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.domain.UnitOfMeasure;

public class IngredientToIngredientCommandTest {

	IngredientToIngredientCommand ingredientToIngredientCommand;
	private static final String DESCRIPTION = "description";
	private static final Long ID = 1L;
	UnitOfMeasure unitOfMeasure;

	@Before
	public void setUp() throws Exception {
		ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	public void testConvertNull() throws Exception {
		IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(null);
		assertNull(ingredientCommand);
	}

	@Test
	public void testConvertEmpty() throws Exception {
		IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(new Ingredient());
		assertNotNull(ingredientCommand);
	}

	@Test
	public void testConvertNullUOM() throws Exception {
		Ingredient ingredient = new Ingredient();
		ingredient.setAmount(BigDecimal.ONE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setId(ID);
		ingredient.setUom(null);
		Recipe recipe = new Recipe();
		recipe.setId(ID);
		recipe.setDescription(DESCRIPTION);
		ingredient.setRecipe(recipe);

		IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
		assertEquals(ID, ingredientCommand.getId());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
		assertNull(ingredientCommand.getUomCommand());
		assertEquals(BigDecimal.ONE, ingredientCommand.getAmount());

	}

	@Test
	public void testConvert() {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setDescription(DESCRIPTION);
		unitOfMeasure.setId(ID);
		Ingredient ingredient = new Ingredient();
		ingredient.setAmount(BigDecimal.ONE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setId(ID);
		ingredient.setUom(unitOfMeasure);
		Recipe recipe = new Recipe();
		recipe.setId(ID);
		recipe.setDescription(DESCRIPTION);
		ingredient.setRecipe(recipe);

		IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
		assertEquals(ID, ingredientCommand.getId());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
		assertNotNull(ingredientCommand.getUomCommand());
		assertEquals(ID, ingredientCommand.getUomCommand().getId());
		assertEquals(BigDecimal.ONE, ingredientCommand.getAmount());
	}

}
