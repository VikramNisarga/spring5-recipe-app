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
import org.mockito.MockitoAnnotations;

import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	@Mock
	RecipeRepository recipeRepository;
	private RecipeService recipeService;
 
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
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
		
	}
	
}
