package com.vikram.spring.recipe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikram.spring.recipe.commands.IngredientCommand;
import com.vikram.spring.recipe.converters.IngredientCommandToIngredient;
import com.vikram.spring.recipe.converters.IngredientToIngredientCommand;
import com.vikram.spring.recipe.domain.Ingredient;
import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.repositories.RecipeRepository;
import com.vikram.spring.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {
	private IngredientToIngredientCommand ingredientToIngredientCommand;
	private IngredientCommandToIngredient ingredientCommandToIngredient;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			log.error("recipe not found");
		}

		Optional<IngredientCommand> ingredientOptional = recipeOptional.get().getIngredients().stream()
				.filter(ingredient -> ingredient.getId() == ingredientId)
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		if (!ingredientOptional.isPresent()) {
			log.error("ingredient not found");
		}
		return ingredientOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		if (!recipeOptional.isPresent()) {
			log.error("Recipe Not found");
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId() == command.getId()).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUomCommand().getId())
						.orElseThrow(() -> new RuntimeException("UOM not found")));
			} else {
				recipe.addIngredient(ingredientCommandToIngredient.convert(command));
			}
			Recipe saveRecipe = recipeRepository.save(recipe);
			 //check by description
			Optional<Ingredient> savedIngredientOptional = saveRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = saveRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUomCommand().getId()))
                        .findFirst();
            }
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}

	}
	
	@Override
	public void deleteIngredientById(Long recipeId, Long ingredientId){
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if(recipeOptional.isPresent()) {
			Optional<Ingredient> ingredientOptional = recipeOptional.get().getIngredients().stream().filter(ingredient -> ingredient.getId() == ingredientId).findFirst();
			if(ingredientOptional.isPresent()) {
				Ingredient ingredient = ingredientOptional.get();
				ingredient.setRecipe(null);
				recipeOptional.get().getIngredients().remove(ingredient);
				recipeRepository.save(recipeOptional.get());
			}
		}else {
			log.debug("recipe id not found:" + recipeId);
		}
		
		
	}

}
