package tech.talci.recipeapp.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.talci.recipeapp.domain.Category;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIT {

    @Autowired
    CategoryRepository categoryRepository;


    @Test
    public void findByDescription() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");

        assertEquals("American", categoryOptional.get().getDescription());
    }
}