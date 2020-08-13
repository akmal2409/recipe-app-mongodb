package tech.talci.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import tech.talci.recipeapp.commands.UnitOfMeasureCommand;
import tech.talci.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tech.talci.recipeapp.domain.UnitOfMeasure;
import tech.talci.recipeapp.repositories.UnitOFMeasureRepository;
import tech.talci.recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    @Mock
    UnitOfMeasureReactiveRepository repository;

    UnitOfMeasureService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() {
        //given
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");

        when(repository.findAll()).thenReturn(Flux.just(uom1, uom2));

        //when
        List<UnitOfMeasureCommand> commands = service.listAllUoms().collectList().block();

        //then
        assertEquals(2, commands.size());
        verify(repository, times(1)).findAll();
    }
}