package tech.talci.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.talci.recipeapp.commands.RecipeCommand;
import tech.talci.recipeapp.services.ImageService;
import tech.talci.recipeapp.services.RecipeService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;
    private final String IMAGEUPLOADFORM_URL = "/recipe/imageuploadform";

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String getImageForm(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return IMAGEUPLOADFORM_URL;
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id,
                                  @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(id, file);

        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        RecipeCommand command = recipeService.findCommandById(id);

        if(command.getImage() != null) {
            byte[] imageByteArray = new byte[command.getImage().length];

            int i = 0;

            for (Byte wrappedByte : command.getImage()) {
                imageByteArray[i++] = wrappedByte; //unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(imageByteArray);
            log.debug("Rendered image, streaming it... for recipe ID: " + command.getId());
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
