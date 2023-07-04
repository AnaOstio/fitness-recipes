<template>
  <div>
    <h1>Week Plan</h1>
    <div class="week">
        <recipe-list-week-component day="Monday"></recipe-list-week-component>
        <recipe-list-week-component day="Tuesday"></recipe-list-week-component>
        <recipe-list-week-component day="Wednesday"></recipe-list-week-component>
        <recipe-list-week-component day="Thursday"></recipe-list-week-component>
        <recipe-list-week-component day="Friday"></recipe-list-week-component>
        <recipe-list-week-component day="Saturday"></recipe-list-week-component>
        <recipe-list-week-component day="Sunday"></recipe-list-week-component>
    </div>
  </div>


</template>

<script>
import RecipeListWeekComponent from "@/components/RecipeListWeekComponent.vue";
export default {
  name: "WeeklyPlan",
  components: {
    RecipeListWeekComponent,
    },
}

</script>


<style>
  .week {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-between;
    text-align: center;
  }
  @media screen and (max-width: 800px) {
    .week {
      overflow-x: scroll;
      background-color: aqua;
      width: 700%;
    }
  }

</style>