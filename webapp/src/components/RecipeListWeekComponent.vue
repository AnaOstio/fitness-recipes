<template>
  <div class="day">
    <h2>Monday</h2>
    <div class="listRecipes">
      <recipe-week-card-component></recipe-week-card-component>
    </div>
  </div>
</template>

<script>
import RecipeWeekCardComponent from "@/components/RecipeWeekCardComponent.vue";
export  default {
  name: "RecipeListWeekComponent",
  components: {
    RecipeWeekCardComponent,
  },
}

</script>

<style>
  .listRecipes{
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .day {
    width: calc(100% / 7);
    height: 100vh;
    border-left: 1px solid black;
  }

  .day:nth-child(7) {
    border-right: 1px solid black;
  }

  @media screen and (max-width: 800px) {
    .day {
      width: 100%;
    }
  }

</style>