package com.vikram.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.vikram.spring.recipe.commands.NotesCommand;
import com.vikram.spring.recipe.domain.Notes;

public class NotesCommandToNotesTest {

	private static final String NOTES = "Notes";
	private static final Long ID = 1L;
	private NotesCommandToNotes notesCommandToNotes;

	@Before
	public void setUp() throws Exception {
		notesCommandToNotes = new NotesCommandToNotes();
	}

	@Test
	public void testConvertNull() throws Exception {
		Notes notes = notesCommandToNotes.convert(null);
		assertNull(notes);
	}

	@Test
	public void testConvertEmpty() throws Exception {
		Notes notes = notesCommandToNotes.convert(new NotesCommand());
		assertNotNull(notes);
	}

	@Test
	public void testConvert() {
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID);
		notesCommand.setNotes(NOTES);
		Notes notes = notesCommandToNotes.convert(notesCommand);
		assertEquals(ID, notes.getId());
		assertEquals(NOTES, notes.getNotes());
	}

}
