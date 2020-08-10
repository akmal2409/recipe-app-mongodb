package tech.talci.recipeapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.talci.recipeapp.commands.CategoryCommand;
import tech.talci.recipeapp.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Nullable
    @Synchronized
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null){
            return null;
        }

        CategoryCommand command = new CategoryCommand();
        command.setDescription(source.getDescription());
        command.setId(source.getId());
        return command;
    }
}