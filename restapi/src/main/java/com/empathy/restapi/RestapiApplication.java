package com.empathy.restapi;

import com.empathy.restapi.util.RecipeReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestapiApplication.class, args);
		RecipeReader rec = new RecipeReader();
		try {
			rec.readLines();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
