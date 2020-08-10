package tech.talci.recipeapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.talci.recipeapp.commands.NotesCommand;
import tech.talci.recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {


    @Nullable
    @Synchronized
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null){
            return null;
        }

        Notes notes = new Notes();
        notes.setRecipeNotes(source.getRecipeNotes());
        notes.setId(source.getId());

        return notes;
    }
}
