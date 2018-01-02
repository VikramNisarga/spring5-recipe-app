package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;
import com.vikram.spring.recipe.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Override
	@Nullable
	@Synchronized
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if (source == null)
			return null;
		else {
			UnitOfMeasureCommand command = new UnitOfMeasureCommand();
			command.setDescription(source.getDescription());
			command.setId(source.getId());
			return command;
		}
	}

}
