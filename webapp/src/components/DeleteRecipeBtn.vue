<template>
  <div @click="deleteRecipe" class="card-crud-btn">
    <i class="fa-solid fa-trash"></i>
  </div>
</template>

<script>
import recipeService from "@/services/recipeService";

export default {
  name: "DeleteRecipeBtn",
  props: {
    id: String,
  },
  methods: {
    deleteRecipe() {
      recipeService
        .deleteRecipe(this.id)
        .then((result) => {
          if (result.status == 200) {
            // TODO OK message
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
