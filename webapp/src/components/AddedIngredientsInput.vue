<template>

  <li class="list-item">
    <p class="ingredient-text">{{ ingredient }}</p>
    <button type="button" class="deleteButtonIngredients">
      <img class="list__icon" src="../assets/minus.png" alt="add" />
      <span class="tooltip">Delete</span>
    </button>
  </li>


</template>

<script>
export default {
  name: "AddedIngredientsInput",
  props: {
    ingredient: {
      type: String,
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
  height: 1.5rem;
  width: 1.5rem;
  justify-content: center;
  align-items: center;
  border-radius: 100%;
  border: none;
  background-color: #F92D52;
  float: right;
}

button:hover .tooltip {
  display: block;
}

img {
  height: 1rem;
  width: 1rem;
  margin-top: 0.1rem;
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


</style>