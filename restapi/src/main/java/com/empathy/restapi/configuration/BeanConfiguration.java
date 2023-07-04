package com.empathy.restapi.configuration;

import com.empathy.restapi.util.RecipeDB;
import com.empathy.restapi.util.UserBD;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RecipeDB recipeReader() {
        return new RecipeDB();
    }

    @Bean
    public UserBD userBD() {
        return new UserBD();
    }

}
