package com.empathy.restapi.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Document(indexName = "recipes")
public class Recipe {

    @Id
    private Long id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "imageName")
    private String imageName;

    @Field(type = FieldType.Text, name = "instructions")
    private String instructions;

    @Field(type = FieldType.Text, name = "typeOfMeal")
    private String typeOfMeal;

    @Field(type = FieldType.Double, name = "rating")
    private double rating;

    @Field(type = FieldType.Text, name = "timeOfPreparation")
    private String timeOfPreparation;

    @Field(type = FieldType.Object, name = "macronutrientsPercentages")
    private Map<String, Double> macronutrientsPercentages;

    @Field(name = "ingredientList")
    private List<String> ingredientList;

    public Recipe() {}

    public Recipe(Long id, String title, List<String> ingredientList, String instructions, String imageName, String typeOfMeal, double rating, Map<String, Double> macronutrientsPercentages, String timeOfPreparation){
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.ingredientList = ingredientList;
        this.instructions = instructions;
        this.typeOfMeal = typeOfMeal;
        this.rating = rating;
        this.macronutrientsPercentages = macronutrientsPercentages;
        this.timeOfPreparation = timeOfPreparation;
    }

    public Recipe(String[] recipe){
        this.id = Long.parseLong(recipe[0]);
        this.title = recipe[1];
        this.ingredientList = Arrays.stream(recipe[2].replaceAll("^\"|\"$", "")
                .replaceAll("^\\[|\\]$", "")
                .replaceAll("^\'|\'$", "")
                .split("\', \'")).toList();
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

    public List<String> getIngredients() {
        return ingredientList;
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

    public void setIngredients(List<String> ingredients) {
        this.ingredientList = ingredients;
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
