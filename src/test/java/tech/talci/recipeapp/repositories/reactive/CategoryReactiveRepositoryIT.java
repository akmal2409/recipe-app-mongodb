package tech.talci.recipeapp.repositories.reactive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.talci.recipeapp.domain.Category;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;


    @Before
    public void setUp() throws Exception {

        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() {
        //given
        Category category = new Category();
        category.setDescription("Test");

        categoryReactiveRepository.save(category).then().block();

        //then
        Category returnedCategory = categoryReactiveRepository.findByDescription("Test").block();

        assertEquals("Test", category.getDescription());
        assertNotNull(returnedCategory.getId());
    }
}