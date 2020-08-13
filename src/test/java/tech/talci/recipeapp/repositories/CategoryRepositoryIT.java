package tech.talci.recipeapp.repositories;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.talci.recipeapp.bootstrap.RecipeBootstrap;
import tech.talci.recipeapp.domain.Category;

import javax.print.DocFlavor;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryRepositoryIT {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UnitOFMeasureRepository unitOFMeasureRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {

        recipeRepository.deleteAll();
        categoryRepository.deleteAll();
        unitOFMeasureRepository.deleteAll();

        RecipeBootstrap  recipeBootstrap = new RecipeBootstrap(recipeRepository, categoryRepository, unitOFMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");

        assertEquals("American", categoryOptional.get().getDescription());
    }
}