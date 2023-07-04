<template>
  <button class="btn btn-add-recipe">Add new recipe</button>
</template>

<script>
export default {
  name: "AddRecipeBtn",
};
</script>

<style>
.btn-add-recipe {
  position: absolute;
  bottom: 0;
  right: 0;
  max-width: 350px;
}

@media screen and (max-width: 800px) {
  .btn-add-recipe {
    width: 100%;
    position: initial;
  }
}
</style>
