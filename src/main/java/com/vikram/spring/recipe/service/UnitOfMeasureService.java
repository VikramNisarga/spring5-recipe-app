package com.vikram.spring.recipe.service;

import java.util.Set;

import com.vikram.spring.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
