package tech.talci.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.NotesCommand;
import tech.talci.recipeapp.domain.Notes;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String recipeNotes = "Notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(recipeNotes);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(recipeNotes, command.getRecipeNotes());
    }
}