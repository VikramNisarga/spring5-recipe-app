package com.vikram.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.vikram.spring.recipe.commands.NotesCommand;
import com.vikram.spring.recipe.domain.Notes;
import com.vikram.spring.recipe.domain.Recipe;

public class NotesToNotesCommandTest {

	private static final String NOTES = "Notes";
	private static final Long ID = 1L;
	NotesToNotesCommand notesToNotesCommand;

	@Before
	public void setUp() throws Exception {
		notesToNotesCommand = new NotesToNotesCommand();
	}

	@Test
	public void testConvertNull() throws Exception {
		NotesCommand notesCommand = notesToNotesCommand.convert(null);
		assertNull(notesCommand);
	}

	@Test
	public void testConvertEmpty() throws Exception {
		NotesCommand notesCommand = notesToNotesCommand.convert(new Notes());
		assertNotNull(notesCommand);
	}

	@Test
	public void testConvert() {
		Notes notes = new Notes();
		notes.setId(ID);
		notes.setNotes(NOTES);
		notes.setRecipe(new Recipe());
		NotesCommand notesCommand = notesToNotesCommand.convert(notes);
		assertEquals(ID, notesCommand.getId());
		assertEquals(NOTES, notesCommand.getNotes());
	}

}
