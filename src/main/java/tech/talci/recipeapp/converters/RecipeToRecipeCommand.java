package tech.talci.recipeapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    IngredientToIngredientCommand ingredientConverter;
    CategoryToCategoryCommand categoryConverter;
    NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null){
            return null;
        }

        RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setUrl(source.getUrl());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setNotes(notesConverter.convert(source.getNotes()));
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setPrepTime(source.getPrepTime());
        command.setCookTime(source.getCookTime());
        command.setSource(source.getSource());
        command.setImage(source.getImage());


        //Ingredients and Categories
        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        if(source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
        }
        return command;
    }
}
