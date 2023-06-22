package com.empathy.restapi.controller;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.ElasticService;
import com.empathy.restapi.service.QueryService;
import com.empathy.restapi.service.impl.ElasticServiceImpl;
import com.empathy.restapi.service.impl.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    private ElasticService indexService;
    private QueryService queryService;

    @Autowired
    public RecipeController(ElasticServiceImpl indexService, QueryServiceImpl queryService) {
        this.indexService = indexService;
        this.queryService = queryService;
    }

    @GetMapping("/bulk-recipes")
    public ResponseEntity<String> bulkRecipes() throws IOException {
        String indexing = indexService.indexRecipes();
        if (indexing == "No errors") {
            return new ResponseEntity<>("No errors indexing the elements", HttpStatus.OK);
        }

        return new ResponseEntity<>(indexing, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) throws IOException {
        return new ResponseEntity<Recipe>(queryService.getRecipeById(id), HttpStatus.OK);
    }

    @GetMapping("/recipes/title/{title}")
    public ResponseEntity<List<Recipe>> getRecipesByTitle(@PathVariable String title) throws IOException {
        List<Recipe> recipes = queryService.getRecipeByTitle(title);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}
