<template>
  <transition name="modal-animation">
    <div v-if="modalActive" class="modal">
      <transition name="modal-animation-inner">
        <div v-show="modalActive" class="modal-inner">
          <slot />
          <div @click="closeModal" class="modal-close-btn">
            <i class="fa-sharp fa-solid fa-circle-xmark"></i>
          </div>
        </div>
      </transition>
    </div>
  </transition>
</template>

<script>
export default {
  name: "ModalView",
  props: ["modalActive"],
  setup(props, { emit }) {
    const closeModal = () => {
      emit("close");
    };

    return { closeModal };
  },
};
</script>

<style>
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
  position: fixed;
  top: 0;
  left: 0;
  overflow-y: auto;
  background-color: rgba(168, 168, 168, 0.596);
  z-index: 4;
}

.modal-inner {
  background-color: rgb(255, 255, 255);
  width: fit-content;
  padding: 40px;
  border-radius: 10px;
  position: relative;
  max-height: 85%;
  overflow: hidden;
  overflow-y: auto;
}

.modal-close-btn {
  position: absolute;
  top: 12px;
  right: 12px;
}

.modal-close-btn i::before {
  color: rgb(129, 129, 129);
}

.modal-close-btn i::before {
  font-size: 20px;
}

.modal-close-btn i:hover {
  filter: brightness(70%);
  cursor: pointer;
}
</style>
