package com.empathy.restapi.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.QueryService;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.InternalOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class QueryServiceImpl implements QueryService {

    private static final String INDEX = "recipes";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public Recipe getRecipeById(String id) throws IOException {
        GetResponse<Recipe> response = elasticsearchClient.get(g -> g
                .index(INDEX)
                .id(id),
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

    @Override
    public List<Recipe> findByFilters(String[] types, Double review,
                                                 Integer timePreparation, String title, boolean user, String id)
            throws IOException {

        List<Recipe> recipes = new ArrayList<>();
        System.out.println(title);
        if(types.length != 0 && types != null){
            for (String type : types) {
                List<Query> queries = new ArrayList<>();
                queries.add(MatchQuery.of(m -> m.field("typeOfMeal").query(type))._toQuery());

                if(review != null && review > 0)
                    queries.add(queryAverage(review));

                if(timePreparation != null && timePreparation > 0)
                    queries.add(queryTime(timePreparation));

                if(title != null && !title.trim().isEmpty())
                    queries.add(queryTitle(title.trim()));

                if(user)
                    queries.add(queryUser(id));

                Query query =
                        BoolQuery.of(q -> q.must(queries))._toQuery();

                SearchResponse response =
                        elasticsearchClient.search(i -> i
                                        .index(INDEX)
                                        .query(query)
                                        .size(100),
                                Recipe.class);

                List<Hit<Recipe>> hits = response.hits().hits();
                for (Hit<Recipe> hit : hits) {
                    recipes.add(hit.source());
                }
            }
        } else {
            List<Query> queries = new ArrayList<>();
            if(review != null && review > 0)
                queries.add(queryAverage(review));

            if(timePreparation != null && timePreparation > 0)
                queries.add(queryTime(timePreparation));

            if(title != null && !title.trim().isEmpty())
                queries.add(queryTitle(title.trim()));

            if(user)
                queries.add(queryUser(id));

            Query query =
                    BoolQuery.of(q -> q.must(queries))._toQuery();

            SearchResponse response =
                    elasticsearchClient.search(i -> i
                                    .index(INDEX)
                                    .query(query)
                                    .size(100),
                            Recipe.class);

            List<Hit<Recipe>> hits = response.hits().hits();
            for (Hit<Recipe> hit : hits) {
                recipes.add(hit.source());
            }
        }

        return recipes;
    }

    public Query queryTime(Integer value){
        if(value != null && value > 0){
            Query qTime = RangeQuery.of(r -> r
                    .field("timeOfPreparation")
                    .lte(JsonData.of(value))
            )._toQuery();

            return qTime;
        }
        return null;
    }

    public Query queryAverage(Double value){
        if(value != null && value > 0){
            Query query = RangeQuery.of(r -> r
                    .field("averageRating")
                    .gte(JsonData.of(value))
            )._toQuery();

            return query;
        }
        return null;
    }

    public Query queryUser(String user) {
        Query q = MatchQuery.of(m -> m.field("userId").query(user))._toQuery();
        return q;
    }

    public Query queryTitle(String title){
        Query q = MatchQuery.of(m -> m.field("title").query(title))._toQuery();
        return q;
    }
}
