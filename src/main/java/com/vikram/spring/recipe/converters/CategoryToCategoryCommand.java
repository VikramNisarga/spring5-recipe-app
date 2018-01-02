package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.CategoryCommand;
import com.vikram.spring.recipe.domain.Category;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Override
	@Nullable
	@Synchronized
	public CategoryCommand convert(Category source) {

		if (source == null) {
			return null;
		} else {
			CategoryCommand categoryCommand = new CategoryCommand();
			categoryCommand.setDescription(source.getDescription());
			categoryCommand.setId(source.getId());
			return categoryCommand;
		}
	}

}
