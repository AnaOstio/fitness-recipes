package com.empathy.restapi.controller;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.util.RecipeReader;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class RecipeController {

    private ElasticsearchOperations elasticsearchOperations;

    public RecipeController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostMapping("/recipe")
    public String saveRecipe(@RequestBody Recipe re) {
        RecipeReader recipeReader = new RecipeReader();
        try {
            recipeReader.readLines();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Recipe r = elasticsearchOperations.save(recipeReader.getRecipes().get(0));

        return r.getTitle();
    }

    @GetMapping("/recipes")
    public String getRecipes(){
        return  saveRecipe(new Recipe());
    }
}
