package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.IngredientCommand;
import com.vikram.spring.recipe.domain.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		super();
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

	@Override
	public Ingredient convert(IngredientCommand source) {

		if (source == null)
			return null;
		else {
			Ingredient ingredient = new Ingredient();
			ingredient.setAmount(source.getAmount());
			ingredient.setDescription(source.getDescription());
			ingredient.setUom(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUomCommand()));
			ingredient.setId(source.getId());
			return ingredient;
		}
	}

}
