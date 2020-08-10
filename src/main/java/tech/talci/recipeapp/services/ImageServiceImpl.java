package tech.talci.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.talci.recipeapp.domain.Recipe;
import tech.talci.recipeapp.repositories.RecipeRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(String recipeId, MultipartFile file) {
        log.debug("Received a file");

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b: file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
            log.debug("Saved image for recipe: " + recipeId);
        } catch (IOException e){
            log.error("Error", e);
            e.printStackTrace();
        }

    }
}
