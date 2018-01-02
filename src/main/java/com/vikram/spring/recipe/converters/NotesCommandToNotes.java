package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.NotesCommand;
import com.vikram.spring.recipe.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Override
	@Synchronized
	public Notes convert(NotesCommand source) {

		if (source == null)
			return null;
		else {
			Notes notes = new Notes();
			notes.setId(source.getId());
			notes.setNotes(source.getNotes());
			return notes;
		}
	}

}
