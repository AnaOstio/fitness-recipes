package com.empathy.restapi.service.impl;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.service.ElasticService;
import com.empathy.restapi.service.RecipeService;
import com.empathy.restapi.util.RecipeDB;
import com.empathy.restapi.util.UserBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    @Override
    public Recipe addRecipeById(String id, Recipe addRecipe) {
        try {
            addRecipe.setRating(new HashMap<>());
            addRecipe.setImageName("https://picsum.photos/200/300");
            Random r = new Random();
            int aux = r.nextInt(13500, 99999);
            addRecipe.setId( aux + "");
            System.out.println("Valor del Id de la receta "+ aux);
            elasticService.indexRecipe(addRecipe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addRecipe;
    }
}
