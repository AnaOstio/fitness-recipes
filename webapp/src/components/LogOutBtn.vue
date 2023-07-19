<template>
  <button class="btn" @click="click">{{ btnText }}</button>
</template>

<script>
export default {
  name: "LogOutBtn",
  props: ["btnText", "click"],
};
</script>

<style></style>
