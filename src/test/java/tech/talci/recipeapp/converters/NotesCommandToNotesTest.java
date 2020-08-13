package tech.talci.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.NotesCommand;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.domain.Notes;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private final static String ID_VALUE = "1";
    private final static String recipeNotes = "Notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void convert() {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(recipeNotes);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(recipeNotes, notes.getRecipeNotes());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new NotesCommand()));
    }


}