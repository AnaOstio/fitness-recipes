package com.empathy.restapi.controller;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.util.RecipeReader;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class RecipeController {

    private ElasticsearchOperations elasticsearchOperations;
    private static final String INDEX = "recipes";

    public RecipeController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostMapping("/recipe")
    public List<String> saveRecipe() {
        RecipeReader recipeReader = new RecipeReader();
        try {
            recipeReader.readLines();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<IndexQuery> queries = recipeReader.getRecipes().stream().map(recipe -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(recipe.getId().toString());
            indexQuery.setObject(recipe);
            return indexQuery;
        }).collect(Collectors.toList());

        return elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(INDEX))
                .stream()
                .map(indexQuery -> indexQuery.toString())
                .collect(Collectors.toList());
    }

    @GetMapping("/recipes")
    public List<String> getRecipes(){
        return  saveRecipe();
    }
}
