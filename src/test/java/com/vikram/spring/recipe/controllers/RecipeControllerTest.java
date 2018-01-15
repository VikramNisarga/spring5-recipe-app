package com.vikram.spring.recipe.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.service.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	RecipeController recipeController;
	@Mock
	Model model;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);

	}

	@Test
	public void testRecipeView() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
				.andExpect(MockMvcResultMatchers.view().name("recipe/show"));

	}

	@Test
	public void testGetRecipeById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		ArgumentCaptor<Long> argumentRecipeId = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<Recipe> argumentCaptorRecipe = ArgumentCaptor.forClass(Recipe.class);
		Mockito.when(recipeService.findRecipeById(1L)).thenReturn(recipe);
		assertEquals("recipe/show", recipeController.getRecipeById(model, 1l));
		Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipe"), argumentCaptorRecipe.capture());
		Mockito.verify(recipeService, Mockito.times(1)).findRecipeById(argumentRecipeId.capture());
		assertEquals(recipe, argumentCaptorRecipe.getValue());
		assertEquals(new Long(1L), argumentRecipeId.getValue());

	}

	@Test
	public void testNewRecipeForm() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
				.andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
