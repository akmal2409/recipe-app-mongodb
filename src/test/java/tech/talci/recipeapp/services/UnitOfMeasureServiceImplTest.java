package tech.talci.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.talci.recipeapp.commands.UnitOfMeasureCommand;
import tech.talci.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tech.talci.recipeapp.domain.UnitOfMeasure;
import tech.talci.recipeapp.repositories.UnitOFMeasureRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOFMeasureRepository repository;

    UnitOfMeasureService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.service = new UnitOfMeasureServiceImpl(repository, new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void listAllUoms() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(2L);
        Set<UnitOfMeasure> uomSet = new HashSet<>();
        uomSet.add(uom);

        //when
        when(repository.findAll()).thenReturn(uomSet);
        Set<UnitOfMeasureCommand> commandSet = service.listAllUoms();

        //then
        assertEquals(1, commandSet.size());
        verify(repository, times(1)).findAll();
    }
}