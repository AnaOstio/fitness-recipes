<template>
  <div class="week-card">
    <img
      class="recipeImg"
      src="https://picsum.photos/200/300"
      alt="Recipe Image"
    />
    <p class="recipeTitle">Title</p>
    <div class="buttonType">
      <p>Breakfast</p>
      <button class="deleteButton">
        <img class="list__icon" src="../assets/minus.png" alt="add" />
        <span class="tooltip">Delete</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecipeWeekCardComponent",
};
</script>

<style>
body {
  font-family: "Inter", sans-serif;
}

.week-card {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 10rem;
  width: 7.5rem;
  border: 1px solid black;
  border-radius: 10px;
  margin: 1rem;
  padding: 1rem;
  background-color: rgb(228, 228, 218);
}

.recipeImg {
  height: 5rem;
  width: 8rem;
  border-radius: 10px;
}

.buttonType {
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.recipeTitle {
  font-size: 1.2rem;
  margin: 4px;
}

.tooltip {
  position: absolute;
  display: none;
  border: 1px solid black;
  padding: 3px;
  background-color: rgb(224, 224, 224);
  color: white;
  width: 100px;
  right: 60%;
  top: 100%;
}

.deleteButton {
  position: relative;
  display: flex;
  height: 2rem;
  width: 2rem;
  justify-content: center;
  align-items: center;
  border-radius: 100%;
  border: none;
  background-color: #f92d52;
}

button:hover .tooltip {
  display: block;
}

.deleteButton img {
  height: 1rem;
  width: 1rem;
}

@media screen and (max-width: 800px) {
  .week-card {
    height: 15rem;
    width: 13rem;
  }

  .recipeImg {
    height: 8rem;
    width: 13rem;
  }
}
</style>
