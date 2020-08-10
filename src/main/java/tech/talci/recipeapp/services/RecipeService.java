package tech.talci.recipeapp.services;

import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.domain.Ingredient;
import tech.talci.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(String id);

    void deleteById(String id);
}
