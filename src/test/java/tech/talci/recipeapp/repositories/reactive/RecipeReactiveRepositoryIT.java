package tech.talci.recipeapp.repositories.reactive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.talci.recipeapp.domain.Recipe;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testRecipeSave() throws Exception{
        //given
        Recipe recipe = new Recipe();
        recipe.setDescription("Test");

        recipeReactiveRepository.save(recipe).block();

        //then
        Long count = recipeReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }
}