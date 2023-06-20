package com.empathy.restapi.repository;

import com.empathy.restapi.configuration.ElasticSearchConfiguration;
import com.empathy.restapi.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticEngineImpl implements  ElasticEngine {

    private static final String INDEX = "recipes";
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ElasticEngineImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void indexElements(List<Recipe> indexing) {
        List<IndexQuery> queries = indexing.stream().map(recipe -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(recipe.getId().toString());
            indexQuery.setObject(recipe);
            return indexQuery;
        }).collect(Collectors.toList());


        elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(INDEX));

    }
}
