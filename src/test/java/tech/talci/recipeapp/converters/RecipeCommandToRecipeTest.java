package tech.talci.recipeapp.converters;

import org.assertj.core.internal.Diff;
import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.CategoryCommand;
import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.commands.NotesCommand;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.domain.Difficulty;
import tech.talci.recipeapp.domain.Recipe;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final String ID_VALUE = "1";
    private static final String DESCRIPTION = "Description";
    private static final Integer PREP_TIME = Integer.valueOf(15);
    private static final Integer COOK_TIME = Integer.valueOf(12);
    private static final String SOURCE = "dojki";
    private static final String DIRECTIONS = "directions";
    private static final Integer SERVINGS = Integer.valueOf(12);
    private static final String INGREDIENT_ID = "2";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final String NOTES_ID = "3";
    private static final String CATEGORY_ID = "4";

    RecipeCommandToRecipe converter;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                                                new CategoryCommandToCategory(), new NotesCommandToNotes());
    }

    @Test
    public void convert() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);
        Set<CategoryCommand> categoryCommandSet = new HashSet<>();
        categoryCommandSet.add(categoryCommand);
        recipeCommand.setCategories(categoryCommandSet.stream().collect(Collectors.toList()));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        Set<IngredientCommand> ingredientCommandsSet = new HashSet<>();
        ingredientCommandsSet.add(ingredientCommand);
        recipeCommand.setIngredients(ingredientCommandsSet.stream().collect(Collectors.toList()));
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setSource(SOURCE);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertEquals(ID_VALUE,recipe.getId());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(1, recipe.getIngredients().size());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }
}