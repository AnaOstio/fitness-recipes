<template>
  <div
    @click="
      () => {
        toggleModal();
        setModalContent(0);
      }
    "
    class="card-crud-btn"
  >
    <i class="fa-solid fa-trash"></i>
  </div>
</template>

<script>
export default {
  name: "DeleteRecipeBtn",
  props: ["toggleModal", "setModalContent"],
};
</script>

<style></style>
