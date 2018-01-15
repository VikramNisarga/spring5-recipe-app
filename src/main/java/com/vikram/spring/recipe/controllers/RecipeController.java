package com.vikram.spring.recipe.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.exceptions.NotFoundException;
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

	@GetMapping(path = "/recipe/{id}/show")
	public String getRecipeById(Model model, @PathVariable Long id) {
		Recipe recipe = recipeService.findRecipeById(id);
		model.addAttribute("recipe", recipe);
		return "recipe/show";
	}

	@GetMapping(path = "/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@RequestMapping(path="/recipe/{id}/update")
	public String updateRecipe(@PathVariable Long id, Model model) {
		Recipe recipe = recipeService.findRecipeById(id);
		model.addAttribute("recipe", recipe);
		return "recipe/recipeform";
		
	}
	@GetMapping(path="/recipe")
	public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> log.debug(error.toString()));
			return "recipe/recipeform";
		}
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
		
	}
	
	@GetMapping(path="/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable Long id) {
		recipeService
		.deleteRecipeById(id);
		return "redirect:/";
		
		
	}
	
	

}
