package com.vikram.spring.recipe;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vikram.spring.recipe.domain.UnitOfMeasure;
import com.vikram.spring.recipe.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Spring5RecipeAppApplicationTests {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Test
	public void findByDescription() {
		Optional<UnitOfMeasure> findByDescription = unitOfMeasureRepository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", findByDescription.get().getDescription());
	}
	
	@Test
	public void findByDescriptionCup() {
		Optional<UnitOfMeasure> findByDescription = unitOfMeasureRepository.findByDescription("Cup");
		assertEquals("Cup", findByDescription.get().getDescription());
	}


}
