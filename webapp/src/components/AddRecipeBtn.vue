<template>
  <div>
    <button @click="toogleModal" id="show-modal" class="btn btn-add-recipe" >Add new recipe</button>
    <modal-view @close="toogleModal" :modal-active="modalActive">
      <div class="modal-content">
        <h1>Modal View</h1>
        <p>This is a modal view!</p>
      </div>
    </modal-view>
  </div>
</template>

<script>
import ModalView from "./Modal.vue";
import {ref} from "vue";

export default {
  name: "AddRecipeBtn",
  components: {ModalView},
  setup() {
    const modalActive = ref(false);
    const toogleModal = () => {
      modalActive.value = !modalActive.value;
    };
    return {modalActive, toogleModal};
  }
};


</script>

<style>
.btn-add-recipe {
  position: absolute;
  bottom: 0;
  right: 0;
  max-width: 350px;
}

@media screen and (max-width: 800px) {
  .btn-add-recipe {
    width: 100%;
    position: initial;
  }
}

.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  position: fixed;
  top: 0;
  left: 0;
  background-color: aliceblue;
}

.modal-inner {
  position: relative;
  max-width: 640px;
  width: 80%;
}
</style>
