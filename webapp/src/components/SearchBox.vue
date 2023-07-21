<template>
  <form class="search-form">
    <input class="search-input" type="text" v-model="titleFilter" />
    <button @click="" class="search-btn">
      <i class="fa-solid fa-arrow-right-long"></i>
    </button>
  </form>
</template>

<script>
export default {
  name: "SearchBox",
  data() {
    return {
      titleFilter: "",
    };
  },
};
</script>

<style>
.search-form {
  max-width: 350px;
  width: 100%;
  display: flex;
  flex-direction: row;
}

.search-input {
  width: 80%;
  height: 35px;
  border: 0px;
  border-top-left-radius: 16px;
  border-bottom-left-radius: 16px;
  background-color: #eeeeee;
  padding: 15px;
}

.search-btn {
  width: 20%;
  height: 35px;
  border: 0px;
  border-top-right-radius: 16px;
  border-bottom-right-radius: 16px;
  background-color: #eeeeee;
}

.search-btn i {
  font-size: 25px;
  color: rgb(100, 100, 100);
}

.search-btn i:hover {
  filter: brightness(80%);
}

.search-btn:hover {
  cursor: pointer;
}
</style>
