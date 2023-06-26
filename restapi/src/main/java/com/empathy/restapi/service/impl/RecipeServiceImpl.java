package com.empathy.restapi.service.impl;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.RecipeService;
import com.empathy.restapi.util.RecipeDB;
import com.empathy.restapi.util.UserBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDB recipeDB;
    @Autowired
    private UserBD userBD;

    @Override
    public void add(Recipe recipe, String userId) {
        recipeDB.addRecipe(recipe);
        userBD.addRecipeUser(recipe.getId(), userId);
    }
}
