package com.vikram.spring.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.spring.recipe.domain.Recipe;
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
	
	@RequestMapping("/recipe/show/{id}")
	public String getRecipeById(Model model, @PathVariable Long id) {
		Recipe recipe = recipeService.findRecipeById(id);
		model.addAttribute("recipe", recipe);
		return "recipe/show";
		
		
		
	}

}
