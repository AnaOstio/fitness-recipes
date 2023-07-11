<template>
  <div>
    <button @click="toggleModal" id="show-modal" class="btn btn-add-recipe">
      Add new recipe
    </button>
    <!-- <modal-view @close="toggleModal" :modal-active="modalActive">
      <div class="modal-content">
        <add-update-component />
      </div>
    </modal-view> -->
  </div>
</template>

<script>
import ModalView from "./Modal.vue";
import { ref } from "vue";
import AddUpdateComponent from "@/components/Add-UpdateComponent.vue";

export default {
  name: "AddRecipeBtn",
  components: { ModalView, AddUpdateComponent },
  setup() {
    const modalActive = ref(false);
    const toggleModal = () => {
      modalActive.value = !modalActive.value;
    };
    return { modalActive, toggleModal };
  },
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
</style>
