package tech.talci.recipeapp.repositories;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.talci.recipeapp.bootstrap.RecipeBootstrap;
import tech.talci.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOFMeasureRepositoryIT {

    @Autowired
    UnitOFMeasureRepository unitOFMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {

        recipeRepository.deleteAll();
        unitOFMeasureRepository.deleteAll();
        categoryRepository.deleteAll();

        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(recipeRepository, categoryRepository, unitOFMeasureRepository);

        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() {

        Optional<UnitOfMeasure> optional = unitOFMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", optional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {

        Optional<UnitOfMeasure> optional = unitOFMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", optional.get().getDescription());
    }
}