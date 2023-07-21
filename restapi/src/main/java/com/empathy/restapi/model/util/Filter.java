package com.empathy.restapi.model.util;

public class Filter {
    private Double averageRating;
    private Integer timePreparation;
    private String[] typeOfMeal;

    public Filter() {
    }

    public Filter(Double averageRating, Integer timePreparation, String[] typeOfMeal) {
        this.averageRating = averageRating;
        this.timePreparation = timePreparation;
        this.typeOfMeal = typeOfMeal;
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
}
