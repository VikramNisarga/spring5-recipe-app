package com.vikram.spring.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.vikram.spring.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	public String getRecipeById(Model model) {
		return null;
	}

}
