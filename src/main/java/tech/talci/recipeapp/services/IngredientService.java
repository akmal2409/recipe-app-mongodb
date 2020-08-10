package tech.talci.recipeapp.services;

import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.domain.Ingredient;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(String recipeId, String ingredientId);
}
