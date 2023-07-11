<template>
  <div class="card-crud-btn">
    <button type="button" @click="toogleModal" id="show-modal"><i class="fa-solid fa-pen"></i></button>
    <modal-view @close="toogleModal" :modal-active="modalActive">
      <div class="modal-content">
        <add-update-component :recipe="recipe"/>
      </div>
    </modal-view>
   </div>
</template>

<script>
import ModalView from "@/components/Modal.vue";
import AddUpdateComponent from "@/components/Add-UpdateComponent.vue";
import {ref} from "vue";

export default {
  name: "UpdateRecipeBtn",
  components: {ModalView, AddUpdateComponent},
  props: {
    recipe: Object,
  },
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
  z-index: 9999;
  background-color: rgb(255, 255, 255, 0.9) ;
}
</style>
