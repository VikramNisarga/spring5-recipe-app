package com.vikram.spring.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vikram.spring.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
}
