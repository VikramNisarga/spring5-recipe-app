package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	IngredientToIngredientCommand ingredientToIngredientCommand;
	CategoryToCategoryCommand categoryToCategoryCommand;
	NotesToNotesCommand notesToNotesCommand;

	public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand,
			CategoryToCategoryCommand categoryToCategoryCommand, NotesToNotesCommand notesToNotesCommand) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.notesToNotesCommand = notesToNotesCommand;
	}

	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}

		final RecipeCommand command = new RecipeCommand();
		command.setId(source.getId());
		command.setCookTime(source.getCookTime());
		command.setPrepTime(source.getPrepTime());
		command.setDescription(source.getDescription());
		command.setDifficulty(source.getDifficulty());
		command.setDirections(source.getDirections());
		command.setServings(source.getServings());
		command.setSource(source.getSource());
		command.setUrl(source.getUrl());
		command.setNotes(notesToNotesCommand.convert(source.getNotes()));

		if(source.getCategories() != null)
		source.getCategories().forEach(category -> command.getCategories().add(categoryToCategoryCommand.convert(category)));
		
		if(source.getIngredients() != null)
			source.getIngredients().forEach(ingredient -> command.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
		
		return command;	
		
	}

}
