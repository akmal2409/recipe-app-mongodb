package tech.talci.recipeapp.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tech.talci.recipeapp.commands.UnitOfMeasureCommand;
import tech.talci.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tech.talci.recipeapp.repositories.UnitOFMeasureRepository;
import tech.talci.recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
        this.converter = converter;
    }

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {

        return unitOfMeasureReactiveRepository
                .findAll()
                .map(converter::convert);
    }
}
