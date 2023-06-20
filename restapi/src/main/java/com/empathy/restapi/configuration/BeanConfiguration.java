package com.empathy.restapi.configuration;

import com.empathy.restapi.util.RecipeReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RecipeReader recipeReader() {
        return new RecipeReader();
    }
}
