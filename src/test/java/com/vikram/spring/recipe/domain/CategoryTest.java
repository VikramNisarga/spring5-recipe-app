package com.vikram.spring.recipe.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;

	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void testGetId() {
		Long id = 4L;
		category.setId(id);
		assertEquals(id, category.getId());
	}

	@Test
	public void testGetDescription() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRecipes() {
		//fail("Not yet implemented");
	}

}
