package tech.talci.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.talci.recipeapp.commands.IngredientCommand;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.converters.RecipeCommandToRecipe;
import tech.talci.recipeapp.converters.RecipeToRecipeCommand;
import tech.talci.recipeapp.domain.Ingredient;
import tech.talci.recipeapp.domain.Recipe;
import tech.talci.recipeapp.exceptions.NotFoundException;
import tech.talci.recipeapp.repositories.RecipeRepository;
import tech.talci.recipeapp.repositories.reactive.RecipeReactiveRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeReactiveRepository recipeReactiveRepository;

    public RecipeServiceImpl(RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand, RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("I'm in the service");

        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id){

        Recipe recipe = recipeReactiveRepository.findById(id).block();

        if(recipe == null){
            throw new NotFoundException("Recipe Not Found. ID: " + id);
        }


        return Mono.just(recipe);
    }

    @Override
    @Transactional
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command); // Detached from Hibernate context

        Recipe savedRecipe = recipeReactiveRepository.save(detachedRecipe).block(); // We save it, Data JPA creates entity or meges
        log.debug("Saved recipeID: " + savedRecipe.getId());
        RecipeCommand savedCommand = recipeToRecipeCommand.convert(savedRecipe);

        return Mono.just(savedCommand);
    }

    @Override
    @Transactional
    public Mono<RecipeCommand> findCommandById(String id) {
        return Mono.just(recipeToRecipeCommand.convert(findById(id).block()));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        recipeReactiveRepository.deleteById(id).block();

        return Mono.empty();
    }

}
