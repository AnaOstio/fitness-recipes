<template>
  <header>
    <nav>
      <router-link to="/" class="nav-btn">
        <!-- <figure><img src="" alt="Logo" /></figure> -->
        <div id="logo">Logo</div>
      </router-link>

      <router-link to="#" class="nav-btn">All recipes</router-link>

      <SearchBox />

      <router-link to="#" class="nav-btn">Weekly plan</router-link>
      <router-link to="#" class="nav-btn">Your recipes</router-link>

      <LogOutBtn />
    </nav>
  </header>
</template>

<script>
import SearchBox from "./SearchBox.vue";
import LogOutBtn from "./LogOutBtn.vue";

export default {
  name: "Header",
  components: { SearchBox, LogOutBtn },
};
</script>

<style>
nav {
  display: flex;
  justify-content: space-around;
  align-items: center;
  list-style: none;
  padding: 10px;
}

.nav-btn {
  text-decoration: none;
  color: black;
}

#logo {
  display: flex;
  background-color: #eeeeee;
  border-radius: 4px;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 70px;
}
</style>
