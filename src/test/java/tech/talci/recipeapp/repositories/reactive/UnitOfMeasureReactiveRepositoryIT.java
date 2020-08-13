package tech.talci.recipeapp.repositories.reactive;

import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.talci.recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() throws Exception{
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Test");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();

        //then
        UnitOfMeasure fetchedUom = unitOfMeasureReactiveRepository.findByDescription("Test").block();

        assertEquals("Test", fetchedUom.getDescription());
        assertNotNull(fetchedUom.getId());

    }
}