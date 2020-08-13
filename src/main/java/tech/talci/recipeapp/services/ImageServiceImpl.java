package tech.talci.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import tech.talci.recipeapp.domain.Recipe;
import tech.talci.recipeapp.repositories.RecipeRepository;
import tech.talci.recipeapp.repositories.reactive.RecipeReactiveRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    @Transactional
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {
        log.debug("Received a file");

        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    try {

                        Byte[] byteObjects = new Byte[file.getBytes().length];

                        int i = 0;

                        for(byte b: file.getBytes()){
                            byteObjects[i++] = b;
                        }

                        recipe.setImage(byteObjects);
                        log.debug("Saved image for recipe: " + recipeId);

                        return recipe;
                    } catch (IOException e){
                        log.error("Error", e);
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
                });

        recipeReactiveRepository.save(recipeMono.block()).block();



        return Mono.empty();
    }
}
