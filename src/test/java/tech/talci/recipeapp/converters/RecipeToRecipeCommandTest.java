package tech.talci.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.domain.*;

import java.net.URL;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(), new NotesToNotesCommand());
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        Category category = new Category();
        category.setId(CATEGORY_ID);
        recipe.getCategories().add(category);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        recipe.getIngredients().add(ingredient);
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setSource(SOURCE);
        recipe.setServings(SERVINGS);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(1, command.getCategories().size());
        assertEquals(1, command.getIngredients().size());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SOURCE, command.getSource());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(DIFFICULTY, command.getDifficulty());

    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }
}