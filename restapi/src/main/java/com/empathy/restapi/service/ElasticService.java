package com.empathy.restapi.service;

import java.io.IOException;

public interface ElasticService {
    String indexRecipes() throws IOException;

    String DeleteRecipeById(String id) throws IOException;
}
