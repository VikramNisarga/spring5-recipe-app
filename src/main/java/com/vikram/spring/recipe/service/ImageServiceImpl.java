package com.vikram.spring.recipe.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vikram.spring.recipe.domain.Recipe;
import com.vikram.spring.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
	
	RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		log.info("Saving image: " + recipeId);
		Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
		try {
			int i =0;
			Byte[] byteObjects = new Byte[file.getBytes().length];
			for(byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}
			optionalRecipe.get().setImage(byteObjects);
			recipeRepository.save(optionalRecipe.get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
