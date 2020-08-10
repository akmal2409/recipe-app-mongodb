package tech.talci.recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CategoryTest {

    Category category;
    Recipe recipe;

    @Before
    public void setUp(){
        category = new Category();
        recipe = new Recipe();
    }
    @Test
    public void getId() throws Exception{
        Long idValue = 4l;
        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception{
        String description = "Test description of a recipe";
        category.setDescription(description);

        assertEquals(description, category.getDescription());
    }

    @Test
    public void getRecipes() throws Exception{
    }
}