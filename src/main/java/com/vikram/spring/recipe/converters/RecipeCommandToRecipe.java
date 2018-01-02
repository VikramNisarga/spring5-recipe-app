package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	IngredientCommandToIngredient ingredientCommandToIngredient;
	CategoryCommandToCategory categoryCommandToCategory;
	NotesCommandToNotes notesCommandToNotes;

	public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
			CategoryCommandToCategory categoryCommandToCategory, NotesCommandToNotes notesCommandToNotes) {
		super();
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.notesCommandToNotes = notesCommandToNotes;
	}

	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}

		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
		}

		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients()
					.forEach(ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
		}

		return recipe;

	}

}
