package com.empathy.restapi.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.ElasticService;
import com.empathy.restapi.util.RecipeReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ElasticServiceImpl implements ElasticService {

    private static final String INDEX = "recipes";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private RecipeReader recipeReader;

    @Override
    public String indexRecipes() throws IOException {
        // Read elements from CSV file
        try {
            recipeReader.readLines();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Indexing recipes
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
        for(Recipe recipe: recipeReader.getRecipes()){
            bulkRequest.operations(op -> op
                    .index(idx -> idx
                            .index(INDEX)
                            .id(recipe.getId().toString())
                            .document(recipe)
                    )
            );
        }

        BulkResponse result = elasticsearchClient.bulk(bulkRequest.build());

        // Test if the indexing has errors
        if (result.errors()) {
            for (BulkResponseItem item: result.items()) {
                if (item.error() != null) {
                    return item.error().reason();
                }
            }
        }
        return "No errors";
    }

}
