package com.empathy.restapi.util;

import com.empathy.restapi.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RecipeDB {

    List<Recipe> recipes = new ArrayList<>();
    @Autowired
    private UserBD userBD;

    public void readLines() throws FileNotFoundException {
         String filePath =
         "//Users//anafo//Documents//Proyectos//fitness-recipes//scripts//foodFilled2.csv";
        // String filePath = "//Users//carlosda//Documents//GitHub//fitness-recipes//scripts//foodFilled2.csv";

        Scanner sc = new Scanner(new File(filePath));
        sc.useDelimiter("---\n");

        String[] recipeFields;

        while (sc.hasNext()) {
            recipeFields = sc.next().split("#");

            if (recipeFields.length == 10){
                Recipe recipe = new Recipe(recipeFields);
                recipes.add(recipe);
                userBD.addRecipeUser(recipe.getId(), recipe.getUserId());
            }
        }

        sc.close();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public Optional<Recipe> getRecipeById(String id) {
        return recipes.stream().filter((recipe) -> recipe.getId().equals(id)).findFirst();
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }
    public void indexingRecipes(Recipe recipe){

    }
}
