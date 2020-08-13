package tech.talci.recipeapp.converters;

import com.sun.source.tree.SynchronizedTree;
import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.commands.UnitOfMeasureCommand;
import tech.talci.recipeapp.domain.Ingredient;
import tech.talci.recipeapp.domain.Recipe;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "description";
    public static final String LONG_VALUE = "1";
    public static final String UOM_ID = "2";

    IngredientCommandToIngredient converter;


    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        command.setId(LONG_VALUE);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        command.setUom(uomCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void testConvertWithNullUOM() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(LONG_VALUE);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        command.setUom(uomCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient.getUom());
    }
}