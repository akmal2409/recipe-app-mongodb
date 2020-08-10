package tech.talci.recipeapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null){
            return null;
        }

        IngredientCommand command = new IngredientCommand();
        command.setUom(uomConverter.convert(source.getUom()));
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setAmount(source.getAmount());
        if(source.getRecipe() != null){
            command.setRecipeId(source.getRecipe().getId());
        }
        return command;
    }
}
