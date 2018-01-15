package com.vikram.spring.recipe.commands;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.vikram.spring.recipe.domain.Difficulty;

import lombok.Data;

@Data
public class RecipeCommand {
	
	private Long id;
	@Size(min=3,max=255)
	private String description;
	
	@Max(value=999)
	@Min(value=1)
	private Integer prepTime;
	@Max(value=999)
	@Min(value=1)
	private Integer cookTime;
	private Integer servings;
	private String source;
	@URL
	private String url;
	@NotBlank
	private String directions;
	private Difficulty difficulty;
	private NotesCommand notes;
	private Byte[] image;
	private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
	
}
