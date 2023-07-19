package com.empathy.restapi.service;

import com.empathy.restapi.model.Recipe;
import java.io.IOException;
import java.util.List;

public interface QueryService {
    Recipe getRecipeById(String id) throws IOException;

    List<Recipe> getRecipeByTitle(String title) throws IOException;

    List<Recipe> getRecipesByUserId(String userId) throws IOException;

    List<Recipe> findRecipesByIngredients(String[] ingredients) throws IOException;
}
