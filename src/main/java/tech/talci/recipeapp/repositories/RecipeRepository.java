package tech.talci.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.talci.recipeapp.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
