<template>
  <div>
    <button @click="toogleModal" id="show-modal" class="btn btn-add-recipe" >Add new recipe</button>
    <modal-view @close="toogleModal" :modal-active="modalActive">
      <div class="modal-content">
        <add-update-component />
      </div>
    </modal-view>
  </div>
</template>

<script>
import ModalView from "./Modal.vue";
import {ref} from "vue";
import AddUpdateComponent from "@/components/Add-UpdateComponent.vue";

export default {
  name: "AddRecipeBtn",
  components: {ModalView, AddUpdateComponent},
  setup() {
    const modalActive = ref(false);
    const toogleModal = () => {
      modalActive.value = !modalActive.value;
    };
    return {modalActive, toogleModal};
  }
};


</script>

<style scoped>
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
  align-items: center;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
  position: fixed;
  top: 0;
  left: 0;
  overflow-y: auto;
  z-index: 3;
  background-color: rgb(255, 255, 255, 0.9) ;
}

</style>
