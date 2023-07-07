package com.empathy.restapi.service;

import com.empathy.restapi.model.Recipe;

import java.io.IOException;

public interface ElasticService {
    String indexRecipes() throws IOException;

    String indexRecipe(Recipe r) throws IOException;
}
