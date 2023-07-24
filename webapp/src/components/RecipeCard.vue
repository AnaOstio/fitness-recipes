<template>
  <div>
    <div class="card-recipe">
      <div
        class="card-hover"
        @click="
          {
            setModalContent(2);
            toggleModal();
          }
        "
      ></div>
      <div class="card-recipe-img">Recipe image</div>
      <div class="card-recipe-body">
        <h2 class="recipe-name">{{ recipeData.title }}</h2>
        <h3 class="recipe-type">Type: {{ recipeData.typeOfMeal }}</h3>
        <div class="recipe-time">
          <i class="fa-solid fa-clock"></i>
          <h3 class="time">{{ recipeData.timeOfPreparation }}</h3>
        </div>
      </div>
      <div class="card-recipe-footer">
        <div class="recipe-rating">
          <h3>{{ recipeData.averageRating }}</h3>
          <i class="fa-solid fa-star"></i>
        </div>
        <UpdateRecipeBtn
          :toggleModal="toggleModal"
          :setModalContent="setModalContent"
          :recipe="recipeData"
          v-if="ownRecipes"
        />
        <DeleteRecipeBtn
          :id="recipeData.id"
          :toggleModal="toggleModal"
          :setModalContent="setModalContent"
          v-if="ownRecipes"
        />
      </div>
    </div>
    <modal-view @close="toggleModal" :modal-active="modalActive">
      <div class="modal-content">
        <DeleteRecipeConfirm
          v-show="modalContent == 0"
          :id="recipeData.id"
          :spliceRecipe="spliceRecipe"
          :toggleModal="toggleModal"
        />
        <AddUpdateComponent
          v-show="modalContent == 1"
          :recipe="recipeData"
          :updateRecipeData="(newRecipe) => updateRecipeData(newRecipe)"
          :toggleModal="toggleModal"
        />
        <RecipeVisualization
          v-show="modalContent == 2"
          :recipe="recipeData"
          :updateRecipeData="(newRecipe) => updateRecipeData(newRecipe)"
        />
      </div>
    </modal-view>
  </div>
</template>

<script>
import DeleteRecipeBtn from "./DeleteRecipeBtn.vue";
import UpdateRecipeBtn from "./UpdateRecipeBtn.vue";
import ModalView from "./Modal.vue";
import DeleteRecipeConfirm from "./DeleteRecipeConfirm.vue";
import AddUpdateComponent from "./Add-UpdateComponent.vue";
import RecipeVisualization from "./RecipeVisualization.vue";
import { ref } from "vue";

export default {
  name: "RecipeCard",
  components: {
    DeleteRecipeBtn,
    UpdateRecipeBtn,
    DeleteRecipeConfirm,
    AddUpdateComponent,
    RecipeVisualization,
    ModalView,
  },
  data() {
    return {
      modalActive: ref(false),
      modalContent: ref(0),
      recipeData: {},
    };
  },
  props: ["recipe", "spliceRecipe", "ownRecipes"],
  watch: {
    recipe(recipe) {
      this.recipeData = { ...recipe };
    },
  },
  created() {
    this.recipeData = { ...this.recipe };
  },
  methods: {
    toggleModal: function () {
      this.modalActive = !this.modalActive;
    },
    setModalContent(modalContent) {
      this.modalContent = modalContent;
    },
    updateRecipeData(recipeUpdated) {
      this.recipeData = recipeUpdated;
    },
  },
};
</script>

<style scoped>
.card-recipe {
  height: 390px;
  width: 100%;
  max-width: 350px;
  background-color: #e4e4da;
  border-radius: 12px;
  padding: 13px;
  margin: auto;
  position: relative;
}

.card-hover {
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 12px;
  z-index: 1;
}

.card-hover:hover {
  background-color: #ffffff33;
  cursor: pointer;
}

.card-recipe-img {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 35%;
  background-color: #eeeeee;
  border-radius: 8px;
}

.card-recipe-body {
  display: flex;
  padding: 15px 0px;
  width: 100%;
  height: 55%;
  flex-direction: column;
  justify-content: space-between;
}

.card-recipe-footer {
  display: flex;
  align-items: center;
  width: 100%;
  height: 10%;
}

.recipe-time {
  display: flex;
  align-items: center;
  gap: 2%;
}

.recipe-time i::before {
  font-size: 16px;
}

.recipe-rating {
  display: flex;
  gap: 2%;
  width: 70%;
  height: fit-content;
}

.recipe-rating i::before {
  font-size: 13px;
}
</style>
