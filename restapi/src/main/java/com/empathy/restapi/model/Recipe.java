package com.empathy.restapi.model;

import java.util.List;
import java.util.Map;

public class Recipe {
    private String title;
    private String imageName;
    private List<String> ingredients;
    private String instructions;
    private String typeOfMeal;
    private double rating;
    private String timeOfPReparation;

    private Map<String, Double> macronutrientsPercentages;

    public Recipe() {}

    public Recipe(String title, String imageName, List<String> ingredients,
                  String instructions, String typeOfMeal, double rating, String timeOfPReparation,
                  Map<String, Double> macronutrientsPercentages) {
        this.title = title;
        this.imageName = imageName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.typeOfMeal = typeOfMeal;
        this.rating = rating;
        this.timeOfPReparation = timeOfPReparation;
        this.macronutrientsPercentages = macronutrientsPercentages;
    }

    public String getTitle() {
        return title;
    }

    public String getImageName() {
        return imageName;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public double getRating() {
        return rating;
    }

    public String getTimeOfPReparation() {
        return timeOfPReparation;
    }

    public Map<String, Double> getMacronutrientsPercentages() {
        return macronutrientsPercentages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTimeOfPReparation(String timeOfPReparation) {
        this.timeOfPReparation = timeOfPReparation;
    }

    public void setMacronutrientsPercentages(Map<String, Double> macronutrientsPercentages) {
        this.macronutrientsPercentages = macronutrientsPercentages;
    }

}
