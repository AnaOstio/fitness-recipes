package com.empathy.restapi;

import com.empathy.restapi.util.RecipeReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestapiApplication.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.empathy.restapi");
		context.refresh();

		RecipeReader rec =  context.getBean(RecipeReader.class);
		try {
			rec.readLines();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
