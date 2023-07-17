<template>
  <div class="recipe-wrapper">
    <h1 class="separated">{{ recipe.title }}</h1>
    <div class="recipe-img separated">Recipe image</div>
    <dl>
      <div class="flex separated">
        <dt>Type: {{ recipe.typeOfMeal }}</dt>
        <dt>
          <i class="fa-solid fa-clock"></i>
          {{ recipe.timeOfPreparation }}
        </dt>
      </div>

      <dt>Instructions</dt>
      <dd class="separated">{{ recipe.instructions }}</dd>

      <dt>Ingredients</dt>
      <dd class="separated">
        <ul class="ingredients-list">
          <li v-for="ingredient in recipe.ingredients">{{ ingredient }}</li>
        </ul>
      </dd>

      <dt>Macronutrients Percentages in Gr.*</dt>
      <MacroPercentageTable
        :macros="recipe.macronutrientsPercentages"
        :readonly="true"
      />
    </dl>

    <form class="rating">
      <label for="recipeTitle">Rate this recipe!</label>
      <div class="stars">
        <i
          v-for="index in 5"
          :key="index"
          @click="rate(index)"
          :class="{ rated: rates[index - 1], unrated: !rates[index - 1] }"
          class="fa-solid fa-star"
        ></i>
      </div>

      <button v-if="recipe" @click="updateRecipe" type="button" class="btn">
        Save Rate
      </button>
    </form>
  </div>
</template>

<script>
import { ref } from "vue";
import MacroPercentageTable from "./MacroPercentageTable.vue";

export default {
  name: "RecipeVisualization",
  props: ["recipe", "updateRecipeData"],
  components: {
    MacroPercentageTable,
  },
  data() {
    return {
      rating: 0,
      rates: ref([false, false, false, false, false]),
    };
  },
  created() {
    this.rate(this.recipe.rating["1"]);
  },
  methods: {
    rate(index) {
      this.rates = this.rates.map((rate, i) => (rate = i < index));
    },
    updateRecipe: function () {
      let aux = { ...this.recipe };

      aux.rating["1"] = this.rating;

      this.$store.dispatch("updateRecipe", {
        recipe: aux,
        updateRecipeData: (newRecipe) => this.updateRecipeData(newRecipe),
      });
    },
  },
};
</script>

<style scoped>
.recipe-img {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 200px;
  background-color: #eeeeee;
  border-radius: 8px;
}

.rating {
  width: 100%;
  padding: 15px 15px 0px 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 18px;
}

.rating label {
  font-size: 20px;
}

.stars {
  display: flex;
  gap: 10px;
}

.rated {
  color: rgb(255, 204, 0);
}

.unrated {
  color: rgb(224, 224, 224);
}

.stars i {
  font-size: 25px;
  cursor: pointer;
}

.separated {
  margin-bottom: 15px;
}

.flex {
  display: flex;
  justify-content: space-between;
}

dt {
  font-weight: bold;
  margin-bottom: 10px;
}

.ingredients-list {
  list-style-type: disc;
  list-style-position: inside;
}

.ingredients-list li:not(:last-child) {
  margin-bottom: 5px;
}

.recipe-wrapper {
  width: 40vw;
}

table {
  width: 100%;
}

caption {
  font-weight: bold;
  text-align: left;
  margin-bottom: 10px;
}

@media screen and (max-width: 1150px) {
  .recipe-wrapper {
    width: 60vw;
  }
}

@media screen and (max-width: 640px) {
  .recipe-wrapper {
    width: 72vw;
  }
}
</style>
