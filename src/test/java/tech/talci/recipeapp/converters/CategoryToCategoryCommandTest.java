package tech.talci.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.recipeapp.commands.CategoryCommand;
import tech.talci.recipeapp.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String LONG_VALUE = "1";
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = converter.convert(category);

        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}