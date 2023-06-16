package com.empathy.restapi.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Recipe {
    private Long id;
    private String title;
    private String[] ingredients;
    private String imageName;
    private String instructions;
    private String typeOfMeal;
    private double rating;
    private String timeOfPreparation;

    private Map<String, Double> macronutrientsPercentages;

    public Recipe() {}

    public Recipe(Long id, String title, String[] ingredients, String instructions, String imageName, String typeOfMeal, double rating, Map<String, Double> macronutrientsPercentages, String timeOfPreparation){
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.typeOfMeal = typeOfMeal;
        this.rating = rating;
        this.macronutrientsPercentages = macronutrientsPercentages;
        this.timeOfPreparation = timeOfPreparation;
    }

    public Recipe(String[] recipe){
        this.id = Long.parseLong(recipe[0]);
        this.title = recipe[1];
        this.ingredients = recipe[2].replaceAll("\\[(.*?)\\]", "").split(", ");
        this.instructions = recipe[3].replaceAll("^\"|\"$", "");
        this.imageName = recipe[4];
        this.typeOfMeal = recipe[5];
        this.rating = Double.parseDouble(recipe[6]);
        this.timeOfPreparation = recipe[8];

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            this.macronutrientsPercentages = objectMapper.readValue(recipe[7].replaceAll("\'", "\""), new TypeReference<Map<String, Double>>() {});
            }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageName() {
        return imageName;
    }

    public String[] getIngredients() {
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

    public String getTimeOfPreparation() {
        return timeOfPreparation;
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

    public void setIngredients(String[] ingredients) {
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

    public void setTimeOfPreparation(String timeOfPreparation) {
        this.timeOfPreparation = timeOfPreparation;
    }

    public void setMacronutrientsPercentages(Map<String, Double> macronutrientsPercentages) {
        this.macronutrientsPercentages = macronutrientsPercentages;
    }

}
