package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.IngredientCommand;
import com.vikram.spring.recipe.domain.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	

	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}


	@Override
	@Nullable
	@Synchronized
	public IngredientCommand convert(Ingredient source) {
		if(source == null) {
			return null;
		}else {
			IngredientCommand ingredientCommand = new IngredientCommand();
			if(source.getRecipe() != null)
			ingredientCommand.setRecipeId(source.getRecipe().getId());
			ingredientCommand.setId(source.getId());
			ingredientCommand.setAmount(source.getAmount());
			ingredientCommand.setDescription(source.getDescription());
			ingredientCommand.setUomCommand(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUom()));
			return ingredientCommand;
		}
	}
}
