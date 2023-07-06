<template>
  <div class="form-recipe">
    <h1 class="separated">Add new Recipe</h1>
    <form @submit="addRecipeForm">
      <label for="recipeTitle">Title (*)</label>
      <input type="text" id="recipeTitle" class="separated" v-model="title"/>
      <div v-if="errors.get('title')">
        <span>{{ errors.get('title') }}</span>
      </div>

      <label for="ingredients">Ingredients (*)</label>
      <div class="input-ingredients separated">
        <input type="text" id="ingredients" class="border tam" />
        <button type="button" class="addButtonIngredients">
          <img class="list__icon" src="../assets/add.png" alt="add" />
          <span class="tooltip">Add</span>
        </button>
      </div>

      <p>Added ingredients</p>
      <div class="added-ingredients separated">
        <ul>
          <added-ingredients-input ingredient="Ingredient 1"></added-ingredients-input>
          <li>Ingredient 2</li>
          <li>Ingredient 3</li>
          <li>Ingredient 4</li>
        </ul>
      </div>

      <label for="instructions">Instructions (*)</label>
      <textarea id="instructions" class="separated" v-model="description"></textarea>
      <div v-if="errors.get('description')">
        <span>{{ errors.get('description') }}</span>
      </div>

      <div class="preparation-time separated">
        <label for="prepTime" class="border">Preparation Time in minutes (*)</label>
        <input type="number" id="prepTime" v-model="preparationTime"/>
      </div>
      <div v-if="errors.get('preparationTime')">
        <span>{{ errors.get('preparationTime') }}</span>
      </div>

      <label for="typeMeal">Type of Meal (*)</label>
      <select id="typeMeal" class="separated">
        <option value="breakfast">Breakfast</option>
        <option value="lunch">Lunch</option>
        <option value="dinner">Dinner</option>
        <option value="dessert">Dessert</option>
      </select>

      <table class="separated">
        <caption>Macronutrients Percentages in Gr. (*)</caption>
        <tr>
          <th>Protein</th>
          <td><input id="protein" v-model="protein"></td>
        </tr>
        <tr>
          <th>Carbohydrates</th>
          <td><input id="carbo" v-model="carbohydrates"></td>
        </tr>
        <tr>
          <th>Greases</th>
          <td><input id="greases" v-model="greases"></td>
        </tr>
        <tr>
          <th>Fiber</th>
          <td><input id="fiber" v-model="fiber"></td>
        </tr>
      </table>



      <button class="addRecipes" type="submit">Add Recipe</button>

    </form>
  </div>
</template>

<script>
import AddedIngredientsInput from "./AddedIngredientsInput.vue";
export default {
  name: "AddUpdateComponent",
  components: {
    AddedIngredientsInput,
  },
  data: function () {
    return {
      title: "",
      description: "",
      preparationTime: "",
      typeMeal: "",
      protein: "",
      carbohydrates: "",
      greases: "",
      fiber: "",
      ingredients: [],
      errors: new Map(),
    };
  },
  methods: {
    addRecipeForm: function (event) {
        this.errors = new Map();
        // check title errors
        if(this.title === ""){
            this.errors.set("title", "Title is required");
        } else if (this.title.length < 3) {
            this.errors.set("title", "Title must be at least 3 characters");
        } else if (this.title.length > 50) {
            this.errors.set("title", "Title must be less than 50 characters");
        } else {
            this.errors.delete("title");
        }

        // check description errors
        if(this.description === ""){
            this.errors.set("description", "Description is required");
        } else if (this.description.length < 3) {
            this.errors.set("description", "Description must be at least 3 characters");
        }  else {
            this.errors.delete("description");
        }

        this.errors.set("table", []);

        // check preparationTime errors
        if(this.preparationTime === ""){
            this.errors.set("preparationTime", "Preparation Time is required");
        } else if (this.preparationTime < 1) {
            this.errors.set("preparationTime", "Preparation Time must be at least 1 minute");
        } else {
            this.errors.delete("preparationTime");
        }

        // table errors
        if(this.protein === ""){
            this.errors.get("table").push("Protein is required");
        } else if (this.protein < 1) {
          this.errors.get("table").push("Protein must be at least 1 gr");
        } else {
            this.errors.delete("table");
        }

      // table errors
      if(this.carbohydrates === ""){
        this.errors.get("table").push("carbohydrates is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("carbohydrates must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      // table errors
      if(this.greases === ""){
        this.errors.get("table").push("greases is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("greases must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      if(this.fiber === ""){
        this.errors.get("table").push("fiber is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("fiber must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      event.preventDefault();
      // then here we will send the data to the server
      if(this.errors.size === 0){
        console.log("ta limpio")
      }
    },
  },
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}

.form-recipe {
  background-color: whitesmoke;
  padding: 5%;
}

.tam {
  width: 80%;
}

.separated {
  margin-bottom: 1rem;
}

.border {
  margin-right: 10px;
}

th {
  background-color: rgb(218, 226, 232);

}

.preparation-time {
  width: 100%;
}

input[type="number"] {
  padding-left: 10px;
  width: 30%;
}

li {
  list-style: none;
  margin-bottom: 0.5rem;
}

input, textarea, select, li {
  width: 100%;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid rgb(218, 226, 232);
}

.addRecipes{
  margin-bottom: 10px;
  max-width: 50%;
  width: 100%;
  align-self: center;
  background-color: rgb(0, 255, 127);
  color: rgb(73, 41, 86);
  height: 30px;
  border-radius: 13px;
}

.input-ingredients {
  width: 100%;
}

.addButtonIngredients {
  background-color: rgb(0, 255, 127);
  border-radius: 50%;
  border: none;
  align-items: center;
  justify-content: center;
  height: 30px;
  width: 30px;
}

button:hover .tooltip {
  display: block;
}

img {
  height: 1.5rem;
  width: 1.5rem;
  margin-top: 0.2rem;
}

body {
  margin: 100px;
}

.tooltip {
  position: absolute;
  display: none;
  border: 1px solid black;
  padding: 3px;
  background-color: gray;
  color: white;
  width: 100px;
  right: 60%;
  top: 100%;
}

.input-ingredients {
  display: flex;
}

.input-ingredients input {
  flex: 1;
  margin-right: 10px;
}

</style>