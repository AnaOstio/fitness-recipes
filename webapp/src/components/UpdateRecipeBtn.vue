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

<style>
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
