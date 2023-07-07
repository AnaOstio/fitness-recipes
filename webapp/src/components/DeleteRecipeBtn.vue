<template>
  <div @click="toogleModal" class="card-crud-btn">
    <i class="fa-solid fa-trash"></i>
    <modal-view @close="toogleModal" :modal-active="modalActive">
      <div class="modal-content">
        <DeleteRecipeConfirm :id="id" />
      </div>
    </modal-view>
  </div>
</template>

<script>
import ModalView from "./Modal.vue";
import DeleteRecipeConfirm from "./DeleteRecipeConfirm.vue";
import recipeService from "@/services/recipeService";
import { ref } from "vue";

export default {
  name: "DeleteRecipeBtn",
  components: {
    ModalView,
    DeleteRecipeConfirm,
  },
  props: {
    id: String,
  },
  setup() {
    const modalActive = ref(false);
    const toogleModal = () => {
      modalActive.value = !modalActive.value;
    };
    return { modalActive, toogleModal };
  },
  methods: {
    deleteRecipe() {
      recipeService
        .deleteRecipe(this.id)
        .then((result) => {
          if (result.status == 200) {
            // TODO OK message
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
