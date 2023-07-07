<template>
  <transition name="modal-animation">
    <div v-show="modalActive" class="modal">
      <transition name="modal-animation-inner">
        <div v-show="modalActive" class="modal-inner">
          <slot />
          <button @click="closeModal">Close</button>
        </div>
      </transition>
    </div>
  </transition>
</template>

<script>
  export default {
    name: "ModalView",
    props: ["modalActive"],
    setup(props, {emit}) {
      const closeModal = () => {
        emit("close");
      };

      return {closeModal};
    }
  }
</script>