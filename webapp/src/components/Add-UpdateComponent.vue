<template>
  <div class="form-recipe">
    <h1 class="separated" v-if="recipe">Update Recipe</h1>
    <h1 class="separated" v-else>Add new Recipe</h1>
    <form>
      <label for="recipeTitle">Title*</label>
      <input type="text" id="recipeTitle" class="separated" v-model="title" />
      <div class="errorMsg" v-if="errors.get('title')">
        <span>{{ errors.get("title") }}</span>
      </div>

      <label for="instructions">Instructions*</label>
      <textarea
        id="instructions"
        class="separated"
        v-model="description"
      ></textarea>
      <div class="errorMsg" v-if="errors.get('description')">
        <span>{{ errors.get("description") }}</span>
      </div>

      <div class="second-part">
        <MacroPercentageTable
          :macros="macronutrientsPercentages"
          @setMacroContent="setMacroContent"
        />
        <ul class="errorMsg" v-if="errors.get('table')">
          <li v-for="e in errors.get('table')">{{ e }}</li>
        </ul>


        <div class="state">
          <div class="preparation-time separated">
            <label for="prepTime" class="border"
              >Preparation Time in minutes*</label
            >
            <input
              type="number"
              id="prepTime"
              v-model="preparationTime"
              min="0"
            />
          </div>
          <div class="errorMsg" v-if="errors.get('preparationTime')">
            <span>{{ errors.get("preparationTime") }}</span>
          </div>

          <label for="typeMeal" class="type-of-meal">Type of Meal*</label>
          <select id="typeMeal" class="separated" v-model="typeMeal">
            <option value="" selected>Select an option...</option>
            <option value="Breakfast">Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Mid-morning meal">Mid-morning meal</option>
            <option value="Dinner">Dinner</option>
            <option value="Dessert">Dessert</option>
          </select>
          <div class="errorMsg" v-if="errors.get('typeOfMeal')">
            <span>{{ errors.get("typeOfMeal") }}</span>
          </div>
        </div>
      </div>

      <label for="ingredients">Ingredients*</label>
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
          <i class="fa-solid fa-plus"></i>
          <span class="tooltip">Add</span>
        </button>
      </div>
      <div class="errorMsg" v-if="errors.get('ingredients')">
        <span>{{ errors.get("ingredients") }}</span>
      </div>

      <p v-if="ingredients.length" class="separated">Added ingredients</p>
      <div class="added-ingredients separated" v-if="ingredients.length">
        <ul>
          <added-ingredients-input
            v-for="(ingredient, index) in ingredients"
            :ingredient="ingredient"
            :key="index"
            @removeIngredient="deleteIngredient"
            class="ingredient-item"
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
import MacroPercentageTable from "./MacroPercentageTable.vue";
import recipeService from "@/services/recipeService";
import { ref } from "vue";

export default {
  name: "AddUpdateComponent",
  components: {
    AddedIngredientsInput,
    MacroPercentageTable,
  },
  props: ["recipe", "updateRecipeData", "toggleModal"],
  data: function () {
    return {
      title: "",
      description: "",
      preparationTime: "",
      typeMeal: "",
      macronutrientsPercentages: {
        protein: "",
        carbohydrates: "",
        greases: "",
        fiber: "",
      },
      recipeId: "",
      errors: new Map(),
    };
  },
  watch: {
    recipe() {
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
      this.preparationTime = this.recipe.timeOfPreparation;
      this.typeMeal = this.recipe.typeOfMeal;
      this.macronutrientsPercentages.protein =
        this.recipe.macronutrientsPercentages.protein;
      this.macronutrientsPercentages.carbohydrates =
        this.recipe.macronutrientsPercentages.carbohydrates;
      this.macronutrientsPercentages.greases =
        this.recipe.macronutrientsPercentages.greases;
      this.macronutrientsPercentages.fiber =
        this.recipe.macronutrientsPercentages.fiber;
      for (let i = 0; i < this.recipe.ingredients.length; i++) {
        this.ingredients.push({ value: this.recipe.ingredients[i], id: i });
      }
    },

    updateRecipe: function () {
      let aux = {
        id: this.recipeId,
        title: this.title,
        instructions: this.description,
        timeOfPreparation: this.preparationTime,
        typeOfMeal: this.typeMeal,
        macronutrientsPercentages: this.macronutrientsPercentages,
        rating: this.recipe.rating,
        imageName: this.recipe.imageName,
        averageRating: this.recipe.averageRating,
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
        recipeService
          .addRecipe({
            title: this.title,
            imageName: "default.jpg",
            ingredients: this.ingredients.map((ingredient) => ingredient.value),
            instructions: this.description,
            timeOfPreparation: this.preparationTime,
            typeOfMeal: this.typeMeal,
            userId: sessionStorage.getItem("userId"),
            averageRating: 0.0,
            macronutrientsPercentages: this.macronutrientsPercentages,
          })
          .then((res) => {
            if (res.status === 200) {
              this.$emit("toggleModalAdd");
            }
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
        this.errors.set("description", "Instructions is required");
      } else if (this.description.length < 3) {
        this.errors.set(
          "description",
          "Instructions must be at least 3 characters"
        );
      } else {
        this.errors.delete("description");
      }

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

      this.errors.set("table", []);

      // table errors
      if (this.macronutrientsPercentages.protein === "") {
        this.errors.get("table").push("Protein is required\n");
      } else if (this.macronutrientsPercentages.protein < 1) {
        this.errors.get("table").push("Protein must be at least 1 gr\n");
      } else {
        this.errors.delete("table");
      }

      // table errors
      if (this.macronutrientsPercentages.carbohydrates === "") {
        this.errors.get("table").push("carbohydrates is required\n");
      } else if (this.macronutrientsPercentages.carbohydrates < 1) {
        this.errors.get("table").push("carbohydrates must be at least 1 gr\n");
      } else {
        this.errors.delete("table");
      }

      // table errors
      if (this.macronutrientsPercentages.greases === "") {
        this.errors.get("table").push("greases is required\n");
      } else if (this.macronutrientsPercentages.greases < 1) {
        this.errors.get("table").push("greases must be at least 1 gr\n");
      } else {
        this.errors.delete("table");
      }

      if (this.macronutrientsPercentages.fiber === "") {
        this.errors.get("table").push("fiber is required\n");
      } else if (this.macronutrientsPercentages.fiber < 1) {
        this.errors.get("table").push("fiber must be at least 1 gr\n");
      } else {
        this.errors.delete("table");
      }

      if (this.typeMeal === "") {
        this.errors.set("typeOfMeal", "Type of meal is required");
      } else {
        this.errors.delete("typeOfMeal");
      }

      if (!this.ingredients.length) {
        this.errors.set("ingredients", "At least one ingredient is required");
      } else {
        this.errors.delete("ingredients");
      }

      return this.errors.size === 0;
    },

    setMacroContent(newMacro) {
      this.macronutrientsPercentages = newMacro;
    },
  },
  setup() {
    const ingredients = ref([]);
    const inputTextIngredients = ref("");
    const addIngredientsList = () => {
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
      deleteIngredient,
      inputTextIngredients,
      ingredients,
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
  margin-bottom: 15px;
}

.border {
  margin-right: 10px;
}

.preparation-time {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
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
.ingredient-item {
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
  height: 2rem;
  width: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 100%;
  border: none;
  float: right;
  position: relative;
}

.addButtonIngredients:hover {
  filter: brightness(95%);
  cursor: pointer;
}

.addButtonIngredients i {
  font-size: large;
}

button:hover .tooltip {
  display: block;
}

img {
  height: 1.5rem;
  width: 1.5rem;
}

body {
  margin: 100px;
}

.tooltip {
  position: absolute;
  display: none;
  border: 1px solid rgba(0, 0, 0, 0);
  border-radius: 5px;
  padding: 3px;
  background-color: rgb(224, 224, 224);
  color: rgb(47, 15, 15);
  width: 100px;
  right: -100%;
  top: -100%;
}

.input-ingredients {
  display: flex;
  align-items: center;
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
.type-of-meal {
  margin-bottom: 10px;
}

label[for="prepTime"] {
  margin: 0px;
}

select {
  margin-top: 10px;
}

.errorMsg {
  color: rgb(255, 70, 70);
  margin-top: -12px;
  margin-bottom: 15px;
}

@media screen and (max-width: 1150px) {
  .form-recipe {
    width: 60vw;
  }

  .second-part {
    flex-direction: column;
  }

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
