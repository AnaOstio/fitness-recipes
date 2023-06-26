package com.empathy.restapi.service;

import com.empathy.restapi.model.Recipe;

public interface RecipeService {
    void add(Recipe recipe, String userId);

    Recipe updateRecipeById(String id, Recipe updateRecipe);
}
