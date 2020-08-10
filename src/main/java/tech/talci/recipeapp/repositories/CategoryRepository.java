package tech.talci.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.talci.recipeapp.domain.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByDescription(String description);
}
