<template>
  <div class="form-recipe">
    <h1 class="separated" v-if="recipe">Update Recipe</h1>
    <h1 class="separated" v-else>Add new Recipe</h1>
    <form @submit="addRecipeForm">
      <label for="recipeTitle">Title (*)</label>
      <input type="text" id="recipeTitle" class="separated" v-model="title" />
      <div v-if="errors.get('title')">
        <span>{{ errors.get("title") }}</span>
      </div>

      <label for="instructions">Instructions (*)</label>
      <textarea
        id="instructions"
        class="separated"
        v-model="description"
      ></textarea>
      <div v-if="errors.get('description')">
        <span>{{ errors.get("description") }}</span>
      </div>

      <div class="second-part">
        <table class="separated">
          <caption>
            Macronutrients Percentages in Gr. (*)
          </caption>
          <tr>
            <th>Protein</th>
            <td><input id="protein" v-model="protein" /></td>
          </tr>
          <tr>
            <th>Carbohydrates</th>
            <td><input id="carbo" v-model="carbohydrates" /></td>
          </tr>
          <tr>
            <th>Greases</th>
            <td><input id="greases" v-model="greases" /></td>
          </tr>
          <tr>
            <th>Fiber</th>
            <td><input id="fiber" v-model="fiber" /></td>
          </tr>
        </table>

        <div class="state">
          <div class="preparation-time separated">
            <label for="prepTime" class="border"
              >Preparation Time in minutes (*)</label
            >
            <input
              type="number"
              id="prepTime"
              v-model="preparationTime"
              min="0"
            />
          </div>
          <div v-if="errors.get('preparationTime')">
            <span>{{ errors.get("preparationTime") }}</span>
          </div>

          <label for="typeMeal" class="type-of-meal">Type of Meal (*)</label>
          <select id="typeMeal" class="separated" v-model="typeMeal">
            <option value="" selected>Select an option...</option>
            <option value="Breakfast">Breakfast</option>
            <option value="Launch">Lunch</option>
            <option value="Dinner">Dinner</option>
            <option value="Dessert">Dessert</option>
          </select>
        </div>
      </div>

      <label for="ingredients">Ingredients (*)</label>
      <div class="input-ingredients separated">
        <input
          type="text"
          id="ingredients"
          class="border tam"
          v-model="inputTextIngredients"
        />
        <button
          type="button"
          class="addButtonIngredients"
          @click="addIngredientsList"
        >
          <img class="list__icon" src="../assets/add.png" alt="add" />
          <span class="tooltip">Add</span>
        </button>
      </div>

      <p v-if="ingredients.length">Added ingredients</p>
      <div class="added-ingredients separated" v-if="ingredients.length">
        <ul>
          <added-ingredients-input
            v-for="ingredient in ingredients"
            :ingredient="ingredient"
            :key="ingredient.id"
            @removeIngredient="deleteIngredient"
          ></added-ingredients-input>
        </ul>
      </div>

      <div class="add-update-confirm-footer">
        <button v-if="recipe" @click="updateRecipe" type="button" class="btn">
          Update Recipe
        </button>
        <button v-else @click="addRecipeForm" type="button" class="btn">
          Add recipe
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import AddedIngredientsInput from "./AddedIngredientsInput.vue";
import recipeService from "@/services/recipeService";
import { ref } from "vue";
export default {
  name: "AddUpdateComponent",
  components: {
    AddedIngredientsInput,
  },
  props: ["recipe", "updateRecipeData", "toggleModal"],
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
      recipeId: "",
      errors: new Map(),
    };
  },
  watch: {
    recipe(recipe) {
      this.getInfoFromRecipe();
    },
  },
  created() {
    if (this.recipe) {
      this.getInfoFromRecipe();
    }
  },
  methods: {
    getInfoFromRecipe() {
      this.recipeId = this.recipe.id;
      this.title = this.recipe.title;
      this.description = this.recipe.instructions;
      this.preparationTime = this.recipe.timeOfPreparation.split(" ")[0];
      this.typeMeal = this.recipe.typeOfMeal;
      this.protein = this.recipe.macronutrientsPercentages.Protein;
      this.carbohydrates = this.recipe.macronutrientsPercentages.Carbohydrates;
      this.greases = this.recipe.macronutrientsPercentages.Greases;
      this.fiber = this.recipe.macronutrientsPercentages.Fiber;
      for (let i = 0; i < this.recipe.ingredients.length; i++) {
        this.ingredients.push({ value: this.recipe.ingredients[i], id: i });
      }
    },

    updateRecipe: function () {
      let aux = {
        id: this.recipeId,
        title: this.title,
        instructions: this.description,
        timeOfPreparation: this.preparationTime + " minutes",
        typeOfMeal: this.typeMeal,
        macronutrientsPercentages: {
          Protein: this.protein,
          Carbohydrates: this.carbohydrates,
          Greases: this.greases,
          Fiber: this.fiber,
        },
        rating: this.recipe.rating,
        userId: this.recipe.userId,
        ingredients: this.ingredients.map((ingredient) => ingredient.value),
      };

      this.$store.dispatch("updateRecipe", {
        recipe: aux,
        toggleModal: this.toggleModal,
        updateRecipeData: (newRecipe) => this.updateRecipeData(newRecipe),
      });
    },
    addRecipeForm: function (event) {
      event.preventDefault();
      // then here we will send the data to the server
      if (this.validateForm()) {
        console.log("ingredients" + this.ingredients);
        recipeService
          .addRecipe({
            title: this.title,
            imageName: "default.jpg",
            ingredients: this.ingredients.map((ingredient) => ingredient.value),
            instructions: this.description,
            timeOfPreparation: this.preparationTime,
            typeOfMeal: this.typeMeal,
            macronutrientsPercentages: {
              Protein: this.protein,
              Carbohydrates: this.carbohydrates,
              Fiber: this.fiber,
              Greases: this.greases,
              // Get userId with Principal in back
            },
            userId: 1,
          })
          .then((res) => {
            console.log(res);
          });
      }
    },
    validateForm() {
      this.errors = new Map();
      // check title errors
      if (this.title === "") {
        this.errors.set("title", "Title is required");
      } else if (this.title.length < 3) {
        this.errors.set("title", "Title must be at least 3 characters");
      } else if (this.title.length > 50) {
        this.errors.set("title", "Title must be less than 50 characters");
      } else {
        this.errors.delete("title");
      }

      // check description errors
      if (this.description === "") {
        this.errors.set("description", "Description is required");
      } else if (this.description.length < 3) {
        this.errors.set(
          "description",
          "Description must be at least 3 characters"
        );
      } else {
        this.errors.delete("description");
      }

      this.errors.set("table", []);

      // check preparationTime errors
      if (this.preparationTime === "") {
        this.errors.set("preparationTime", "Preparation Time is required");
      } else if (this.preparationTime < 1) {
        this.errors.set(
          "preparationTime",
          "Preparation Time must be at least 1 minute"
        );
      } else {
        this.errors.delete("preparationTime");
      }

      // table errors
      if (this.protein === "") {
        this.errors.get("table").push("Protein is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("Protein must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      // table errors
      if (this.carbohydrates === "") {
        this.errors.get("table").push("carbohydrates is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("carbohydrates must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      // table errors
      if (this.greases === "") {
        this.errors.get("table").push("greases is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("greases must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      if (this.fiber === "") {
        this.errors.get("table").push("fiber is required");
      } else if (this.protein < 1) {
        this.errors.get("table").push("fiber must be at least 1 gr");
      } else {
        this.errors.delete("table");
      }

      return this.errors.size === 0;
    },
  },
  setup() {
    const ingredients = ref([]);
    const inputTextIngredients = ref("");
    const addIngredientsList = () => {
      console.log("entro " + inputTextIngredients.value);
      inputTextIngredients.value !== ""
        ? ingredients.value.push({
            value: inputTextIngredients.value,
            id: ingredients.value.length + 1,
          })
        : "";
      inputTextIngredients.value = "";
    };

    const deleteIngredient = (deleteId) => {
      ingredients.value = ingredients.value.filter(
        (ingredient) => ingredient.id !== deleteId
      );
    };

    return {
      addIngredientsList,
      inputTextIngredients,
      ingredients,
      deleteIngredient,
    };
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}

.form-recipe {
  width: 40vw;
}

.second-part {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
  gap: 5%;
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
  background-color: rgb(217, 227, 235);
  padding: 0px 10px;
}

.preparation-time {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: baseline;
}

input[type="number"] {
  padding-left: 10px;
  width: 30%;
}

li {
  list-style: none;
  margin-bottom: 0.5rem;
}

input,
textarea,
select,
li {
  width: 100%;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid rgb(218, 226, 232);
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

.add-update-confirm-footer {
  display: flex;
  justify-content: center;
}

textarea {
  max-width: 100%;
  min-width: 100%;
  min-height: 10vh;
}

.state {
  display: inline-block;
  width: 50%;
}

label,
caption,
.type-of-meal {
  margin-bottom: 10px;
}

select {
  margin-top: 5px;
}

caption {
  text-align: left;
}

table {
  width: 50%;
}

@media screen and (max-width: 1150px) {
  .form-recipe {
    width: 60vw;
  }

  .second-part {
    flex-direction: column;
  }

  table,
  .state {
    width: 100%;
  }
}

@media screen and (max-width: 640px) {
  .form-recipe {
    width: 72vw;
  }
}
</style>
