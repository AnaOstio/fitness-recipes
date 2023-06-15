package com.empathy.restapi.util;

import com.empathy.restapi.model.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecipeReader {

    public void readLines() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(
                "//Users//anafo//Documents//Proyectos//fitness-recipes//scripts//foodFilled.csv"));
        sc.useDelimiter("\n\n\n");
        //setting comma as delimiter pattern
        while (sc.hasNext()) {
            System.out.print(sc.next());
        }
        sc.close();
    }

}
