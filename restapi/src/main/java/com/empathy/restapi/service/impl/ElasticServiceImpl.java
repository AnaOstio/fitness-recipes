package com.empathy.restapi.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.ElasticService;
import com.empathy.restapi.util.RecipeDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticServiceImpl implements ElasticService {

    private static final String INDEX = "recipes";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private RecipeDB recipeDB;

    @Override
    public String indexRecipes() throws IOException {
        // Read elements from CSV file
        try {
            recipeDB.readLines();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Indexing recipes
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
        for (Recipe recipe : recipeDB.getRecipes()) {
            bulkRequest.operations(op -> op
                    .index(idx -> idx
                            .index(INDEX)
                            .id(recipe.getId().toString())
                            .document(recipe)));
        }

        BulkResponse result = elasticsearchClient.bulk(bulkRequest.build());

        // Test if the indexing has errors
        if (result.errors()) {
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    return item.error().reason();
                }
            }
        }
        return "No errors";
    }

    public String indexRecipe(Recipe r) throws IOException {
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
        bulkRequest.operations(op -> op
                .index(idx -> idx
                        .index(INDEX)
                        .id(r.getId().toString())
                        .document(r)));
        BulkResponse result = elasticsearchClient.bulk(bulkRequest.build());

        if (result.errors()) {
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    return item.error().reason();
                }
            }
        }
        return "No errors";
    }

    @Override
    public String DeleteRecipeById(String id) throws IOException {
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
        bulkRequest.operations(op -> op
                .delete(idx -> idx
                        .index(INDEX)
                        .id(id)));

        BulkResponse result = elasticsearchClient.bulk(bulkRequest.build());

        if (result.errors()) {
            if (result.items().get(0).error() != null) {
                return result.items().get(0).error().reason();
            }
        }
        return "Recipe deleted successfully";
    }

}
