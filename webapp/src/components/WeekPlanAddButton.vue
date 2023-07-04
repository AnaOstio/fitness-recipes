<template><button class="addButton">
  <img class="list__icon" src="../assets/add.png" alt="add" />
  <span class="tooltip">Add</span>
</button>
</template>

<script>
export default {
  name: "WeekPlanAddButton",
};
</script>

<style>
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

  button:hover .tooltip {
    display: block;
  }

  img {
    height: 1.5rem;
    width: 1.5rem;
  }

  .addButton {
    position: relative;
    display: flex;
    height: 3rem;
    width: 3rem;
    justify-content: center;
    align-items: center;
    border-radius: 30%;
    border: none;
    background-color: rgb(0, 255, 127);
  }

</style>