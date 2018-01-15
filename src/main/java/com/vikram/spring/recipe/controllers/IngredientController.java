package com.vikram.spring.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.spring.recipe.commands.IngredientCommand;
import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;
import com.vikram.spring.recipe.service.IngredientService;
import com.vikram.spring.recipe.service.RecipeService;
import com.vikram.spring.recipe.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientController {

	RecipeService recipeService;
	IngredientService ingredientService;
	UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping(path = "/recipe/{id}/ingredients")
	public String getIngredients(@PathVariable Long id, Model model) {
		log.debug("reading ingredients for Recipe: " + id);
		model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
		return "recipe/ingredient/lists";

	}

	@GetMapping(path = "/recipe/{recipeId}/ingredient/{id}/show")
	public String getIngredientById(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, id);
		model.addAttribute("ingredient", ingredientCommand);
		return "recipe/ingredient/show";

	}
	
	@GetMapping(path = "/recipe/{recipeId}/ingredient/new")
	public String createIngredient(@PathVariable Long recipeId, Model model) {
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(recipeId);
		IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUomCommand(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
		
		
	}

	@GetMapping(path = "/recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
		return "recipe/ingredient/ingredientform";

	}

	@GetMapping(path = "/recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand command, Model model) {
		log.info("Ingredient UOM: "+ command.getUomCommand());
		IngredientCommand saveIngredientCommand = ingredientService.saveIngredientCommand(command);
		log.info("saved Recipe id: " + saveIngredientCommand.getRecipeId());
		log.info("saved Ingredient id: " + saveIngredientCommand.getId());
		return "redirect:/recipe/" + saveIngredientCommand.getRecipeId() + "/ingredient/"
				+ saveIngredientCommand.getId() + "/show";
	}
	
	@GetMapping(path = "/recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long id) {
		ingredientService.deleteIngredientById(recipeId, id);
		

        return "redirect:/recipe/" +recipeId +"/ingredients";
		
		
	}
}
