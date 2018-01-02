package com.vikram.spring.recipe.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.converters.RecipeCommandToRecipe;
import com.vikram.spring.recipe.converters.RecipeToRecipeCommand;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spring5RecipeServiceIT {

	public static final String NEW_DESCRIPTION = "New Description";
	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	RecipeRepository recipeRepository;

	@Test
	@Transactional
	public void testSaveOfRecipeCommand() throws Exception {

		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

		// when
		testRecipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

		// then
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
		assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}

}
