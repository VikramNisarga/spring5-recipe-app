package com.vikram.spring.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.spring.recipe.domain.Category;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.domain.UnitOfMeasure;
import com.vikram.spring.recipe.repositories.CategoryRepository;
import com.vikram.spring.recipe.repositories.RecipeRepository;
import com.vikram.spring.recipe.repositories.UnitOfMeasureRepository;
import com.vikram.spring.recipe.service.RecipeService;

@Controller
public class IndexController {

	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.findAllRecipes());
		return "index";
	}
}
