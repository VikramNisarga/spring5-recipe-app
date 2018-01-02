package com.vikram.spring.recipe.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.vikram.spring.recipe.converters.RecipeCommandToRecipe;
import com.vikram.spring.recipe.converters.RecipeToRecipeCommand;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	private RecipeService recipeService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void testFindAllRecipes() {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipes = new HashSet<>();
		recipes.add(recipe);

		when(recipeService.findAllRecipes()).thenReturn(recipes);

		Set<Recipe> recipes2 = recipeService.findAllRecipes();
		assertEquals(1, recipes2.size());
		verify(recipeRepository, times(1)).findAll();

	}

	@Test
	public void testFindRecipeById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		Recipe recipeReturned = recipeService.findRecipeById(1L);
		assertNotNull(recipeReturned);
		assertEquals(recipe, recipeReturned);
		verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
		verify(recipeRepository, Mockito.never()).findAll();

	}

}
