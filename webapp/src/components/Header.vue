<template>
  <header>
    <nav class="nav-menu">
      <div class="only-mobile">
        <div id="logo">Logo</div>
        <i class="fas fa-bars" @click="showMenu()"></i>
      </div>

      <div class="nav-content" :class="showMobileMenu ? 'open-menu' : 'closed-menu'">
        <div class="nav-items">
          <div id="logo-no-mobile">Logo</div>
          <router-link to="#" class="nav-btn">All recipes</router-link>
          <SearchBox v-if="!showMobileMenu"/>
          <router-link to="#" class="nav-btn">Weekly plan</router-link>
          <router-link to="#" class="nav-btn">Your recipes</router-link>
        </div>

        <LogOutBtn v-if="isLogged == null" :btn-text='loginBtn'/>
        <LogOutBtn v-else :btn-text='logoutBtn'/>
      </div>
    </nav>
  </header>
</template>

<script>
import SearchBox from "./SearchBox.vue";
import LogOutBtn from "./LogOutBtn.vue";

export default {
  name: "Header",
  components: { SearchBox, LogOutBtn },
  data() {
    return {
      showMobileMenu: false,
      loginBtn: "Log in",
      logoutBtn: "Log out",
      isLogged : (sessionStorage.getItem("token"))
    };
  },
  methods: {
    showMenu() {
      this.showMobileMenu = !this.showMobileMenu;
    },
  },
};
</script>

<style scoped>
nav {
  position: relative;
  z-index: 100;
}

.nav-menu {
  background-color: white;
}

.nav-content {
  display: flex;
  align-items: center;
  padding: 10px 30px;
}

.nav-items {
  display: flex;
  align-items: center;
  list-style: none;
  margin: 0;
  padding: 0;
  justify-content: space-between;
  flex-grow: 1;
}

.nav-items a {
  padding: 0 10px;
  text-decoration: none;
  color: black;
}

#logo {
  display: none;
  background-color: #eeeeee;
  border-radius: 4px;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 70px;
}

#logo-no-mobile {
  display: flex;
  background-color: #eeeeee;
  border-radius: 4px;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 70px;
}

i {
  display: none;
  font-size: 1.5rem;
  color: black;
  cursor: pointer;
}

@media screen and (max-width: 768px) {
  .nav-menu {
    padding-top: 10px;
    position: relative;
  }

  .open-menu {
    background-color: #7f7f7f;
    opacity: 1;
  }

  .closed-menu {
    opacity: 0;
    height: 0;
    padding: 0;
  }

  .nav-content {
    flex-direction: column;
    transition: all 0.2s ease-out;
  }

  .only-mobile {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 5px 0;
  }

  #logo {
    display: flex;
  }

  i {
    display: block;
    text-align: right;
    padding: 0 10px 10px 0;
  }

  .nav-items {
    flex-direction: column;
    padding: 5px 0;
  }

  .separated-right {
    margin-right: 0;
    margin-bottom: 10px;
  }

  #logo-no-mobile {
    display: none;
  }
}

@media screen and (max-width: 640px) {
  .separated-right {
    margin-right: 0;
    margin-bottom: 10px;
  }

  #logo-no-mobile {
    display: none;
  }
}

@media screen and (max-width: 400px) {
  .separated-right {
    margin-right: 0;
    margin-bottom: 10px;
  }

  #logo-no-mobile {
    display: none;
  }
}
</style>