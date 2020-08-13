package tech.talci.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.talci.recipeapp.converters.RecipeCommandToRecipe;
import tech.talci.recipeapp.converters.RecipeToRecipeCommand;
import tech.talci.recipeapp.domain.Recipe;
import tech.talci.recipeapp.exceptions.NotFoundException;
import tech.talci.recipeapp.repositories.RecipeRepository;
import tech.talci.recipeapp.repositories.reactive.RecipeReactiveRepository;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeCommandToRecipe, recipeToRecipeCommand,
                recipeReactiveRepository);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId("1");
        Recipe recipe2 = new Recipe();
        recipe2.setId("2");

        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe, recipe2));

        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(recipes.size(), 2);
        verify(recipeReactiveRepository, times(1)).findAll();
        verify(recipeReactiveRepository, never()).findById(anyString());
    }

    @Test
    public void testDeleteById() throws Exception{
        //given
        String idToDelete = "1";

        //when
        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeReactiveRepository, times(1)).deleteById(anyString());
    }

    @Test(expected = NotFoundException.class)
    public void testRecipeByIdNotFound() {

        //when
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.empty());

        //then
        Recipe recipeReturned = recipeService.findById("1").block();
        //should go boom
    }
}