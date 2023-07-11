<template>
  <div class="delete-confirm-box">
    <h1>Delete recipe</h1>
    <p>The recipe will be removed permanently</p>
    <div class="delete-confirm-footer">
      <button @click="this.toggleModal" class="btn cancel-btn">Cancel</button>
      <button @click="deleteRecipe" class="btn accept-btn">Accept</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteRecipeConfirm",
  props: ["id", "spliceRecipe", "toggleModal"],
  methods: {
    deleteRecipe: function () {
      this.$store.dispatch("deleteRecipe", {
        id: this.id,
        spliceRecipe: this.spliceRecipe,
        toggleModal: this.toggleModal,
      });
    },
  },
};
</script>

<style scoped>
.delete-confirm-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  width: fit-content;
  height: 200px;
}

.delete-confirm-footer {
  display: flex;
  gap: 10px;
}

.btn {
  height: fit-content;
  white-space: nowrap;
  padding: 10px 20px;
  border: 0px;
  border-radius: 8px;
  font-size: larger;
}

.btn:hover {
  filter: brightness(95%);
}

.cancel-btn {
  background-color: rgb(210, 210, 210);
}

.accept-btn {
  background-color: rgb(255, 106, 101);
}
</style>
