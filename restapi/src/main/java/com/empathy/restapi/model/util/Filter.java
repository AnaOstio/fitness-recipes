package com.empathy.restapi.model.util;

public class Filter {
    private Double averageRating;
    private Integer timePreparation;
    private String[] typeOfMeal;
    private String title;
    private boolean ownRecipes;

    public Filter() {
    }

    public Filter(Double averageRating, Integer timePreparation, String[] typeOfMeal, String title, boolean ownRecipes) {
        this.averageRating = averageRating;
        this.timePreparation = timePreparation;
        this.typeOfMeal = typeOfMeal;
        this.title = title;
        this.ownRecipes = ownRecipes;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getTimePreparation() {
        return timePreparation;
    }

    public void setTimePreparation(Integer timePreparation) {
        this.timePreparation = timePreparation;
    }

    public String[] getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String[] typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOwnRecipes() {
        return ownRecipes;
    }

    public void setOwnRecipes(boolean ownRecipes) {
        this.ownRecipes = ownRecipes;
    }
}
