<template>
  <div class="sidebar"></div>
</template>

<script>
export default {
  name: "Sidebar",
};
</script>

<style>
.sidebar {
  width: 20%;
  min-width: 200px;
  background-color: aquamarine;
}

@media screen and (max-width: 640px) {
  .sidebar {
    display: none;
  }
}
</style>
