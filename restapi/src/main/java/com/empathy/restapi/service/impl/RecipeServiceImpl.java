package com.empathy.restapi.service.impl;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.RecipeService;
import com.empathy.restapi.util.RecipeDB;
import com.empathy.restapi.util.UserBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Recipe updateRecipeById(String id, Recipe updateRecipe) {
        Optional<Recipe> toUpdate = recipeDB.getRecipeById(id);
        if (toUpdate.isPresent()) {
            toUpdate.get().setTitle(updateRecipe.getTitle());
            toUpdate.get().setIngredients(updateRecipe.getIngredients());
            toUpdate.get().setInstructions(updateRecipe.getInstructions());
            toUpdate.get().setTimeOfPreparation(updateRecipe.getTimeOfPreparation());
            toUpdate.get().setRating(updateRecipe.getRating());
            toUpdate.get().setImageName(updateRecipe.getImageName());
            toUpdate.get().setTypeOfMeal(updateRecipe.getTypeOfMeal());
            toUpdate.get().setMacronutrientsPercentages(updateRecipe.getMacronutrientsPercentages());
        }
        return toUpdate.get();
    }
}
