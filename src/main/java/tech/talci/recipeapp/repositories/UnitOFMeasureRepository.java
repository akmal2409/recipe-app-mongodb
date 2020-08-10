package tech.talci.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.talci.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

@Repository
public interface UnitOFMeasureRepository extends CrudRepository<UnitOfMeasure, String> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
