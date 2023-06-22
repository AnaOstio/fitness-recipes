package com.empathy.restapi.service;

import com.empathy.restapi.model.Recipe;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface QueryService {
    ResponseEntity<Recipe> getRecipeById(Long id) throws IOException;

    List<Recipe> getRecipeByTitle(String title) throws IOException;
}
