package com.vikram.spring.recipe.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vikram.spring.recipe.commands.RecipeCommand;
import com.vikram.spring.recipe.service.ImageService;
import com.vikram.spring.recipe.service.RecipeService;

@Controller
public class ImageController {

	private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }
    
    @GetMapping("recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
    	RecipeCommand recipeCommand = recipeService.findRecipeCommandById(id);
    	 if (recipeCommand.getImage() != null) {
             byte[] byteArray = new byte[recipeCommand.getImage().length];
             int i = 0;

             for (Byte wrappedByte : recipeCommand.getImage()){
                 byteArray[i++] = wrappedByte; //auto unboxing
             }

             response.setContentType("image/jpeg");
             InputStream is = new ByteArrayInputStream(byteArray);
             IOUtils.copy(is, response.getOutputStream());
         }
    }
}
