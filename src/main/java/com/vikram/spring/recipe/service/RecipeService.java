package com.vikram.spring.recipe.service;

import java.util.Set;

import com.vikram.spring.recipe.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> findAllRecipes();

}
