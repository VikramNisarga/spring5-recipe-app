package com.vikram.spring.recipe.service;

import com.vikram.spring.recipe.commands.IngredientCommand;

public interface IngredientService {
	
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	void deleteIngredientById(Long recipeId, Long ingredientId);
}
