<template>
  <h1 class="page-title">Your recipes</h1>
</template>

<script>
export default {
  name: "PageTitle",
};
</script>

<style>
.page-title {
  font-size: 60px;
  white-space: nowrap;
  max-width: 1280px;
  width: calc(100% - 50px);
}

@media screen and (max-width: 800px) {
  .page-title {
    text-align: center;
  }
}

@media screen and (max-width: 640px) {
  .page-title {
    font-size: 3rem;
  }
}

</style>
