<template>
  <div class="card-recipe">
    <div class="card-recipe-img">Recipe image</div>
    <div class="card-recipe-body">
      <h2 class="recipe-name">Recipes Name</h2>
      <h3 class="recipe-type">Type: breakfast</h3>
      <div class="recipe-time">
        <div class="time-icon">T</div>
        <h3 class="time">30 min</h3>
      </div>
    </div>
    <div class="card-recipe-footer">
      <h3 class="recipe-rating">Stars</h3>
      <div class="card-btn-update">U</div>
      <div class="card-btn-delete">D</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecipeCard",
};
</script>

<style>
.card-recipe {
  height: 390px;
  width: 100%;
  max-width: 350px;
  background-color: #e4e4da;
  border-radius: 12px;
  padding: 13px;
  margin: auto;
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
  width: 100%;
  height: 10%;
}

.recipe-time {
  display: flex;
}

.time-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eeeeee;
  width: 10%;
  height: 100%;
  margin-right: 2%;
}

.recipe-rating {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eeeeee;
  height: 100%;
  width: 60%;
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
</style>
