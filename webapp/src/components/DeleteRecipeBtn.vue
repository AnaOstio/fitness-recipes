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

<style scoped>
.card-crud-btn i::before {
  font-size: 20px;
}

.card-crud-btn i:hover {
  color: #7f7f7f;
  cursor: pointer;
}

.card-crud-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 15%;
  height: 100%;
  z-index: 2;
}
</style>
