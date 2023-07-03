<template>
  <div class="day">
    <h2>{{ day }}</h2>
    <div class="listRecipes">
      <recipe-week-card-component></recipe-week-card-component>
      <week-plan-add-button></week-plan-add-button>
    </div>
  </div>
</template>

<script>
import RecipeWeekCardComponent from "@/components/RecipeWeekCardComponent.vue";
import WeekPlanAddButton from "@/components/WeekPlanAddButton.vue";
export  default {
  name: "RecipeListWeekComponent",
  components: {
    RecipeWeekCardComponent,
    WeekPlanAddButton,
  },
  props: {
    day: {
      type: String,
      required: true,
    },
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