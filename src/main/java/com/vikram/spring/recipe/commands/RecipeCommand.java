package com.vikram.spring.recipe.commands;

import java.util.HashSet;
import java.util.Set;

import com.vikram.spring.recipe.domain.Difficulty;

import lombok.Data;

@Data
public class RecipeCommand {
	
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private NotesCommand notes;
	private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();

}
