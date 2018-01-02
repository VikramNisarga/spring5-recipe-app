package com.vikram.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vikram.spring.recipe.commands.NotesCommand;
import com.vikram.spring.recipe.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Override
	@Synchronized
	public NotesCommand convert(Notes source) {
		if (source == null)
			return null;
		else {
			NotesCommand command = new NotesCommand();
			command.setId(source.getId());
			command.setNotes(source.getNotes());
			return command;
		}
	}

}
