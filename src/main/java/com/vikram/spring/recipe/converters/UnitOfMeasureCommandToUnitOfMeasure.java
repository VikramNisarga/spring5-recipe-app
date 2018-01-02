package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;
import com.vikram.spring.recipe.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if (source == null)
			return null;
		else {
			UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
			unitOfMeasure.setDescription(source.getDescription());
			unitOfMeasure.setId(source.getId());
			return unitOfMeasure;
		}
	}

}
