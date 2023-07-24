package com.empathy.restapi.service.impl;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.ElasticService;
import com.empathy.restapi.service.QueryService;
import com.empathy.restapi.service.RecipeService;
import com.empathy.restapi.util.RecipeDB;
import com.empathy.restapi.util.UserBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDB recipeDB;
    @Autowired
    private UserBD userBD;

    @Autowired
    private ElasticService elasticService;

    @Autowired
    private QueryService queryService;

    @Override
    public void add(Recipe recipe, String userId) {
        recipeDB.addRecipe(recipe);
        userBD.addRecipeUser(recipe.getId(), userId);
    }

    @Override
    public Recipe updateRecipeById(String id, Recipe updateRecipe) throws IOException {
        Recipe toUpdate = queryService.getRecipeById(id);

        if (toUpdate != null) {
            toUpdate.setId(id);
            toUpdate.setTitle(updateRecipe.getTitle());
            toUpdate.setIngredients(updateRecipe.getIngredients());
            toUpdate.setInstructions(updateRecipe.getInstructions());
            toUpdate.setTimeOfPreparation(updateRecipe.getTimeOfPreparation());
            toUpdate.setRating(updateRecipe.getRating());
            toUpdate.setImageName(updateRecipe.getImageName());
            toUpdate.setTypeOfMeal(updateRecipe.getTypeOfMeal());
            toUpdate.setMacronutrientsPercentages(updateRecipe.getMacronutrientsPercentages());
            toUpdate.setAverageRating(updateRecipe.getAverageRating());
            toUpdate.setUserId(updateRecipe.getUserId());
            toUpdate.calculateAverageRating();
            return elasticService.updateRecipe(toUpdate);
        }

        return null;

    }

    @Override
    public Recipe addRecipeById(String id, Recipe addRecipe) {
        try {
            addRecipe.setRating(new HashMap<>());
            addRecipe.setImageName("https://picsum.photos/200/300");
            Random r = new Random();
            int aux = r.nextInt(13500, 99999);
            addRecipe.setId(aux + "");
            System.out.println("Valor del Id de la receta " + aux);
            elasticService.indexRecipe(addRecipe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addRecipe;
    }
}
