<template>
  <div>
    <header>
      <nav>
        <router-link to="/" class="nav-btn">
          <figure><img src="" alt="Logo" /></figure>
        </router-link>

        <router-link to="#" class="nav-btn">Signup</router-link>

        <input id="searchbox" type="text" />

        <router-link to="#" class="nav-btn">Signup</router-link>
        <router-link to="#" class="nav-btn">Login</router-link>
        <router-link to="#" class="nav-btn"
          ><button>Log out</button></router-link
        >
      </nav>
    </header>
  </div>
</template>

<script>
export default {};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

nav {
  display: flex;
  justify-content: space-between;
  list-style: none;
}

.nav-btn {
  text-decoration: none;
  color: black;
  height: 50%;
  padding: 20px;
}
</style>
