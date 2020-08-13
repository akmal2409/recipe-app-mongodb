package tech.talci.recipeapp.services;

import reactor.core.publisher.Flux;
import tech.talci.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
