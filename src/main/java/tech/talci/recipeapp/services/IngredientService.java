package tech.talci.recipeapp.services;

import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.domain.Ingredient;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long ingredientId);
}
