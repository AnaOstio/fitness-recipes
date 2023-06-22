package com.empathy.restapi.util;

import com.empathy.restapi.model.Recipe;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeReader {

    List<Recipe> recipes = new ArrayList<>();

    public void readLines() throws FileNotFoundException {
        // String filePath =
        // "//Users//anafo//Documents//Proyectos//fitness-recipes//scripts//foodFilled2.csv";
        String filePath = "//Users//carlosda//Documents//GitHub//fitness-recipes//scripts//foodFilled2.csv";

        Scanner sc = new Scanner(new File(filePath));
        sc.useDelimiter("---\n");

        String[] recipeFields;

        while (sc.hasNext()) {
            recipeFields = sc.next().split("#");

            if (recipeFields.length == 9)
                recipes.add(new Recipe(recipeFields));
        }

        sc.close();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
