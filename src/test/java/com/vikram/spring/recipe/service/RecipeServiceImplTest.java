package com.vikram.spring.recipe.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
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
	private RecipeServiceImpl recipeServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
	}
	@Test
	public void testFindAllRecipes() {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipes = new HashSet<>();
		recipes.add(recipe);
		
		when(recipeServiceImpl.findAllRecipes()).thenReturn(recipes);
		
		Set<Recipe> recipes2 = recipeServiceImpl.findAllRecipes();
		assertEquals(1, recipes2.size());
		verify(recipeRepository, times(1)).findAll();
		
	}
	
}
