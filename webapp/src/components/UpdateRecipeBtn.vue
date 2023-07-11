<template>
  <div
      @click="
      () => {
        toggleModal();
        setModalContent(1);
      }
    "
      class="card-crud-btn"
    >
      <i class="fa-solid fa-pen"></i>
   </div>
</template>

<script>
export default {
  name: "UpdateRecipeBtn",
  props: ["toggleModal", "setModalContent"],
};
</script>