package tech.talci.recipeapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.talci.recipeapp.commands.NotesCommand;
import tech.talci.recipeapp.domain.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {


    @Nullable
    @Synchronized
    @Override
    public NotesCommand convert(Notes source) {
        if(source == null){
            return null;
        }

        NotesCommand command = new NotesCommand();
        command.setId(source.getId());
        command.setRecipeNotes(source.getRecipeNotes());
        return command;
    }
}
