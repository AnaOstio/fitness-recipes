package com.empathy.restapi.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.empathy.restapi.model.Recipe;

public interface RecipeService {
    void add(Recipe recipe, String userId);

    Recipe updateRecipeById(String id, Recipe updateRecipe) throws IOException;

    Recipe addRecipeById(String id, Recipe addRecipe);
}
