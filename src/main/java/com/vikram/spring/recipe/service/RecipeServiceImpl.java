package com.vikram.spring.recipe.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.converters.RecipeCommandToRecipe;
import com.vikram.spring.recipe.converters.RecipeToRecipeCommand;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.exceptions.NotFoundException;
import com.vikram.spring.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> findAllRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}

	@Override
	public Recipe findRecipeById(Long id) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if (recipe.isPresent()) {
			return recipe.get();
		}
		throw new NotFoundException("Recipe Not found for id :" + id);

	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe recipeDetached = recipeCommandToRecipe.convert(recipeCommand);
		Recipe recipeSaved = recipeRepository.save(recipeDetached);
		log.debug("Recipe saved:" + recipeSaved);

		return recipeToRecipeCommand.convert(recipeSaved);
	}

	@Override
	@Transactional
	public RecipeCommand findRecipeCommandById(Long id) {
		return recipeToRecipeCommand.convert(findRecipeById(id));
	}

	@Override
	public void deleteRecipeById(Long id) {
		recipeRepository.deleteById(id);
		
	}

}
