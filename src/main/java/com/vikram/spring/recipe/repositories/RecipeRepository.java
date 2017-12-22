package com.vikram.spring.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vikram.spring.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
