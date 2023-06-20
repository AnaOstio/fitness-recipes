package com.empathy.restapi.repository;

import com.empathy.restapi.model.Recipe;

import java.util.List;

public interface ElasticEngine {

    void indexElements(List<Recipe> indexing);
}
