package com.empathy.restapi.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.QueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    private static final String INDEX = "recipes";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public Recipe getRecipeById(Long id) throws IOException {
        GetResponse<Recipe> response = elasticsearchClient.get(g -> g
                .index(INDEX)
                .id(id.toString()),
                Recipe.class);

        return response.source();
    }

    @Override
    public List<Recipe> getRecipeByTitle(String title) throws IOException {
        SearchResponse<Recipe> response = elasticsearchClient.search(s -> s
                .index(INDEX)
                .query(q -> q
                        .match(t -> t
                                .field("title")
                                .query(title))),
                Recipe.class);

        List<Hit<Recipe>> hits = response.hits().hits();
        List<Recipe> recipes = new ArrayList<>();
        for (Hit<Recipe> hit : hits) {
            recipes.add(hit.source());
        }

        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByUserId(String userId) throws IOException {
        SearchResponse<Recipe> response = elasticsearchClient.search(s -> s
                .index(INDEX)
                .query(q -> q
                        .match(t -> t
                                .field("userId")
                                .query(userId))),
                Recipe.class);

        List<Hit<Recipe>> hits = response.hits().hits();
        List<Recipe> recipes = new ArrayList<>();
        for (Hit<Recipe> hit : hits) {
            recipes.add(hit.source());
        }

        return recipes;
    }
}
