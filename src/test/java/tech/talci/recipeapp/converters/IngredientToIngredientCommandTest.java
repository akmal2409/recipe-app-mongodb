package tech.talci.recipeapp.converters;

import com.sun.xml.bind.v2.model.core.ID;
import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.domain.Ingredient;
import tech.talci.recipeapp.domain.Recipe;
import tech.talci.recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "description";
    public static final Long UOM_ID = Long.valueOf(2L);
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final Long RECIPE_ID = Long.valueOf(3L);


    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);

        //Recipe recipe = new Recipe();
        //recipe.setId(RECIPE_ID);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUom(uom);
        //ingredient.setRecipe(recipe);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(UOM_ID, command.getUom().getId());
        //assertEquals(RECIPE_ID, command.getRecipeId());
    }

    @Test
    public void testNullAsUOMID() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertNull(ingredient.getUom());


    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }
}