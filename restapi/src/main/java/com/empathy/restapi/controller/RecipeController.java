package com.empathy.restapi.controller;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.model.util.Filter;
import com.empathy.restapi.security.util.TokenUtil;
import com.empathy.restapi.service.ElasticService;
import com.empathy.restapi.service.QueryService;
import com.empathy.restapi.service.RecipeService;
import com.empathy.restapi.service.UserService;
import com.empathy.restapi.service.impl.ElasticServiceImpl;
import com.empathy.restapi.service.impl.QueryServiceImpl;
import com.empathy.restapi.service.impl.RecipeServiceImpl;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RecipeController {

    private ElasticService elasticService;
    private QueryService queryService;
    private RecipeService recipeService;
    private UserService userService;

    private TokenUtil tokenUtil;

    @Autowired
    public RecipeController(ElasticServiceImpl elasticService, QueryServiceImpl queryService,
            RecipeServiceImpl recipeService, TokenUtil tokenUtil, UserService userService) {
        this.elasticService = elasticService;
        this.queryService = queryService;
        this.recipeService = recipeService;
        this.tokenUtil = tokenUtil;
        this.userService = userService;
    }

    @GetMapping("/bulk-recipes")
    public ResponseEntity<String> bulkRecipes() throws IOException {
        String indexing = elasticService.indexRecipes();
        if (indexing == "No errors") {
            return new ResponseEntity<>("No errors indexing the elements", HttpStatus.OK);
        }

        return new ResponseEntity<>(indexing, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Map<String, Object>> getRecipeById(@PathVariable String id) throws IOException {
        Recipe recipe = queryService.getRecipeById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("recipe", recipe);
        response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/recipes/title/{title}")
    public ResponseEntity<List<Recipe>> getRecipesByTitle(@PathVariable String title) throws IOException {
        List<Recipe> recipes = queryService.getRecipeByTitle(title);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/recipes/user/{userId}")
    public ResponseEntity<HashMap<String, Object>> getRecipesByUserId(@PathVariable String userId,
                                                                      HttpServletRequest request) throws IOException {

        String token = tokenUtil.getToken(request);
        System.out.println("Username: " + tokenUtil.getUsernameFromToken(token));

        List<Recipe> recipes = queryService.getRecipesByUserId(userId);

        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("data", recipes);
        response.put("status", HttpStatus.OK.value());



        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/recipes/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> updateRecipeById(@PathVariable String id)
            throws IOException {

        // TODO: handle errors for the response
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("data", elasticService.DeleteRecipeById(id));
        response.put("status", HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/recipes/add/{id}")
    public ResponseEntity<Recipe> addRecipeById(@PathVariable String id, @RequestBody Recipe addRecipe)
            throws IOException {
        Recipe add = recipeService.addRecipeById(id, addRecipe);
        return new ResponseEntity<>(add, HttpStatus.OK);
    }

    // Cuando haga un actualizar, acordarse de volver a actualizar el averageRating
    @PutMapping("/recipes/update/{id}")
    public ResponseEntity<HashMap<String, Object>> updateRecipe(@PathVariable String id,
            @RequestBody Recipe updateRecipe) throws IOException {
        Recipe updated = recipeService.updateRecipeById(id, updateRecipe);
        if (updated != null) {
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("data", updated);
            response.put("status", HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/recipes/filters")
    public ResponseEntity<HashMap<String, Object>> getFiltersResultUser(@RequestBody Filter filter,
                                                                        HttpServletRequest request) throws IOException {
        HashMap<String, Object> response = new HashMap<String, Object>();

        // User in session
        String token = tokenUtil.getToken(request);
        String username = tokenUtil.getUsernameFromToken(token);
        String id = userService.findByUsername(username).getId();

        List<Recipe> recipes = queryService.findByFilters(filter.getTypeOfMeal(),
                filter.getAverageRating(), filter.getTimePreparation(), filter.getTitle(), filter.isOwnRecipes(), id);

        response.put("data", recipes);
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
