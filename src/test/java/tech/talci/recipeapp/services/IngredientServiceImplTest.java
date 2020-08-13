package tech.talci.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.converters.IngredientCommandToIngredient;
import tech.talci.recipeapp.converters.IngredientToIngredientCommand;
import tech.talci.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import tech.talci.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tech.talci.recipeapp.domain.Ingredient;
import tech.talci.recipeapp.domain.Recipe;
import tech.talci.recipeapp.domain.UnitOfMeasure;
import tech.talci.recipeapp.exceptions.NotFoundException;
import tech.talci.recipeapp.repositories.RecipeRepository;
import tech.talci.recipeapp.repositories.UnitOFMeasureRepository;
import tech.talci.recipeapp.repositories.reactive.RecipeReactiveRepository;
import tech.talci.recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;

import javax.crypto.spec.OAEPParameterSpec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;


    IngredientService ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureReactiveRepository unitOFMeasureRepository;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;


    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                                        recipeReactiveRepository, unitOFMeasureRepository, recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("1");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        ingredient1.setRecipe(recipe);
        recipe.addIngredient(ingredient2);
        ingredient2.setRecipe(recipe);
        recipe.addIngredient(ingredient3);
        ingredient3.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        //then
        IngredientCommand command = ingredientService.findByRecipeIdAndIngredientId("1", "3").block();

        assertEquals("3", command.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
    }

    @Test
    public void testSaveIngredientCommand() throws Exception{
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId("3");
        command.setRecipeId("2");

        Recipe recipe = new Recipe();
        recipe.setId("2");

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("3");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).block();

        //then
        assertEquals("3", savedCommand.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void testDeleteById() {
        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(recipe));

        //when
        ingredientService.deleteById("1", "3");

        //then
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

}