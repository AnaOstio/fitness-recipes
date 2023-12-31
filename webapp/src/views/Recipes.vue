<template>
  <div>
    <Header>
      <SearchBox @filter="getFilteredRecipes" />
    </Header>

    <div class="wrapped">
      <SideBar @filter="getFilteredRecipes" />
      <main>
        <div class="flex-top">
          <PageTitle :own-recipes="ownRecipes" />
          <SearchBox class="search-box-mobile" @filter="getFilteredRecipes" />
          <AddRecipeBtn v-if="ownRecipes" />
        </div>
        <section>
          <div class="grid grid-recipes">
            <RecipeCard
              v-for="(recipe, index) in recipes"
              :key="index"
              :recipe="recipe"
              :spliceRecipe="() => spliceRecipe(index)"
              :own-recipes="ownRecipes"
            />
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import SideBar from "@/components/SideBar.vue";
import PageTitle from "@/components/PageTitle.vue";
import RecipeCard from "@/components/RecipeCard.vue";
import AddRecipeBtn from "@/components/AddRecipeBtn.vue";
import recipeService from "@/services/recipeService";
import SearchBox from "@/components/SearchBox.vue";

export default {
  name: "YourRecipes",
  components: {
    SearchBox,
    Header,
    SideBar,
    PageTitle,
    RecipeCard,
    AddRecipeBtn,
  },
  data() {
    return {
      recipes: [],
      filter: {
        title: "",
        typeOfMeal: [],
        timePreparation: 0,
        averageRating: 0.0,
        ownRecipes: true,
      },
    };
  },
  props: {
    ownRecipes: {
      type: Boolean,
      required: true,
    },
  },
  created() {
    this.filter.ownRecipes = this.ownRecipes;
    this.getFilteredRecipes(this.filter);
  },
  watch: {
    ownRecipes() {
      this.getFilteredRecipes(this.filter);
    },
  },
  methods: {
    spliceRecipe(index) {
      this.recipes.splice(index, 1);
    },

    getFilteredRecipes(payload) {
      this.filter.ownRecipes = this.ownRecipes;
      payload.title && (this.filter.title = payload.title);
      payload.typeOfMeal && (this.filter.typeOfMeal = payload.typeOfMeal);
      payload.timePreparation &&
        (this.filter.timePreparation = payload.timePreparation);
      payload.averageRating &&
        (this.filter.averageRating = payload.averageRating);

      recipeService
        .filterRecipes(this.filter)
        .then((result) => {
          if (result.status === 200) {
            this.recipes = result.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
.grid-recipes {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  column-gap: 40px;
  row-gap: 50px;
  margin: 40px auto;
  margin-left: 25px;
  max-width: 1280px;
}

.flex-top {
  display: flex;
  max-width: 1280px;
  position: relative;
  margin-left: 25px;
}

.search-box-mobile {
  display: none;
}

/* Common */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

main {
  width: 100%;
}

.btn {
  height: fit-content;
  white-space: nowrap;
  padding: 10px 20px;
  border: 0px;
  background-color: #00ff7f;
  border-radius: 8px;
  font-size: larger;
}

.btn:hover {
  filter: brightness(95%);
  cursor: pointer;
}

.wrapped {
  display: flex;
  margin: 0 auto;
  padding: 40px 0px;
  width: calc(100% - 50px);
  position: absolute;
  top: 90px;
}

@media screen and (max-width: 1350px) {
  .grid-recipes {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media screen and (max-width: 1050px) {
  .grid-recipes {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media screen and (max-width: 800px) {
  .grid-recipes {
    grid-template-columns: repeat(1, 1fr);
  }
}

@media screen and (max-width: 900px) {
  .flex-top {
    flex-direction: column;
    gap: 30px;
    align-items: center;
  }
}

@media screen and (max-width: 768px) {
  .search-box-mobile {
    display: flex;
    flex-direction: row;
  }
}

@media screen and (max-width: 640px) {
  .grid-recipes,
  .flex-top {
    margin-left: 0;
  }

  .wrapped {
    width: 100%;
  }
}
</style>
