<template>
  <header>
    <nav>
      <router-link to="/" class="nav-btn">
        <figure><img src="" alt="Logo" /></figure>
      </router-link>
      <!-- <router-link to="/all">All recipes</router-link>
      <router-link to="/weekplan">Week plan</router-link> -->

      <input id="searchbox" type="text" />
      <router-link to="/signup" class="nav-btn">Signup</router-link>
      <router-link to="/login" class="nav-btn">Login</router-link>
    </nav>
  </header>
</template>

<script>
export default {
  name: "Header",
  data: function () {
    return {};
  },

  methods: {},
};
</script>

<style>
nav {
  display: flex;
  justify-content: space-between;
  list-style: none;
  margin: 0;
  padding: 0;
}
nav figure {
  margin: 0px;
}

.nav-btn {
  text-decoration: none;
  color: black;
  margin: 0;
  height: 50%;
  padding: 20px;
}
</style>
