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
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "imageName")
    private String imageName;

    @Field(type = FieldType.Text, name = "instructions")
    private String instructions;

    @Field(type = FieldType.Text, name = "typeOfMeal")
    private String typeOfMeal;

    @Field(type = FieldType.Object, name = "rating")
    private Map<String, Double> rating;

    @Field(type = FieldType.Double, name = "averageRating")
    private Double averageRating;

    @Field(type = FieldType.Integer, name = "timeOfPreparation")
    private int timeOfPreparation;

    @Field(type = FieldType.Object, name = "macronutrientsPercentages")
    private Map<String, Double> macronutrientsPercentages;

    @Field(name = "ingredients")
    private List<String> ingredients;

    @Field(type = FieldType.Text, name = "userId")
    private String userId;

    public Recipe() {
    }

    public Recipe(String id, String title, List<String> ingredients, String instructions, String imageName,
            String typeOfMeal, Map<String, Double> rating, Map<String, Double> macronutrientsPercentages,
            int timeOfPreparation, String userId) {
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.typeOfMeal = typeOfMeal;
        this.rating = rating;
        this.macronutrientsPercentages = macronutrientsPercentages;
        this.timeOfPreparation = timeOfPreparation;
        this.userId = userId;
        this.averageRating = 0.0;
    }

    public Recipe(String[] recipe) {
        this.averageRating = 0.0;
        this.id = recipe[0];
        this.title = recipe[1];
        this.ingredients = Arrays.stream(recipe[2].replaceAll("^\"|\"$", "")
                .replaceAll("^\\[|\\]$", "")
                .replaceAll("^\'|\'$", "")
                .split("\', \'")).toList();
        this.instructions = recipe[3].replaceAll("^\"|\"$", "");
        this.imageName = recipe[4];
        this.typeOfMeal = recipe[5];
        this.timeOfPreparation = Integer.parseInt(recipe[8]);
        this.userId = recipe[9];

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.macronutrientsPercentages = objectMapper.readValue(recipe[7].replaceAll("\'", "\""),
                    new TypeReference<Map<String, Double>>() {
                    });
            this.rating = objectMapper.readValue(recipe[6].replaceAll("\'", "\""),
                    new TypeReference<Map<String, Double>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // I need to put the average rating
        calculateAverageRating();
    }

    private void calculateAverageRating() {
        double aux = 0.0;
        for (Map.Entry<String, Double> entry : this.rating.entrySet()) {
            aux += entry.getValue();
        }
        this.averageRating = aux / this.rating.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Map<String, Double> getRating() {
        return rating;
    }

    public int getTimeOfPreparation() {
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
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public void setRating(Map<String, Double> rating) {
        this.rating = rating;
    }

    public void setTimeOfPreparation(int timeOfPreparation) {
        this.timeOfPreparation = timeOfPreparation;
    }

    public void setMacronutrientsPercentages(Map<String, Double> macronutrientsPercentages) {
        this.macronutrientsPercentages = macronutrientsPercentages;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
