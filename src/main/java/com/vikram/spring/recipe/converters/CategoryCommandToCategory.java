package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.CategoryCommand;
import com.vikram.spring.recipe.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Override
	@Nullable
	public Category convert(CategoryCommand source) {
		if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
	}

}
