<template>
  <li class="list-item">
    <p class="ingredient-text">{{ ingredient.value }}</p>
    <button
      @click="$emit('removeIngredient', ingredient.id)"
      type="button"
      class="deleteButtonIngredients"
    >
      <i class="fa-solid fa-minus"></i>
      <span class="tooltip">Delete</span>
    </button>
  </li>
</template>

<script>
export default {
  name: "AddedIngredientsInput",
  props: {
    ingredient: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.list-item {
  display: flex;
  align-items: center;
}

.ingredient-text {
  flex: 1;
}

.deleteButtonIngredients {
  height: 1.75rem;
  width: 1.75rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 100%;
  border: none;
  background-color: #f92d52;
  float: right;
  position: relative;
}

.deleteButtonIngredients i {
  font-size: large;
}

.deleteButtonIngredients:hover {
  filter: brightness(90%);
  cursor: pointer;
}

button:hover .tooltip {
  display: block;
}

img {
  height: 1rem;
  width: 1rem;
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
  right: -125%;
  top: -110%;
}
</style>
