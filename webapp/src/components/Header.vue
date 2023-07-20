<template>
  <header>
    <nav class="nav-menu">
      <div class="only-mobile">
        <div id="logo">Logo</div>
        <i class="fas fa-bars btn-menu" @click="showMenu()"></i>
      </div>

      <div
        class="nav-content"
        :class="showMobileMenu ? 'open-menu' : 'closed-menu'"
      >
        <div class="nav-items">
          <div id="logo-no-mobile">Logo</div>
          <router-link to="#" class="nav-btn">All recipes</router-link>
          <SearchBox v-if="!showMobileMenu" />
          <router-link to="/week-plan" class="nav-btn">Weekly plan</router-link>
          <router-link to="/your-recipes" class="nav-btn"
            >Your recipes</router-link
          >

          <LogOutBtn
            v-if="isLogged == null"
            :btn-text="loginBtn"
            :click="login"
          />
          <LogOutBtn v-else :btn-text="logoutBtn" :click="logout" />
        </div>
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
      isLogged: sessionStorage.getItem("token"),
    };
  },
  methods: {
    showMenu() {
      this.showMobileMenu = !this.showMobileMenu;
    },
    logout() {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem("email");
      sessionStorage.removeItem("userId");
      this.isLogged = false;
      this.$router.push("/");
    },
    login() {
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
header {
  width: 100%;
  position: fixed;
  z-index: 4;
}

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
  white-space: nowrap;
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
  min-width: 70px;
  min-height: 70px;
}

i {
  display: none;
  font-size: 1.5rem;
  color: black;
  cursor: pointer;
  padding: 0px;
}

@media screen and (max-width: 768px) {
  .nav-menu {
    position: relative;
  }

  .nav-items,
  .nav-items a {
    width: 100%;
    padding: 10px 0px;
    text-align: center;
  }

  .nav-items a:hover {
    background-color: #ababab47;
  }

  .open-menu {
    background-color: #ffffff;
    opacity: 1;
    height: 200px;
    transition: all 0.2s ease-out;
    padding: 0px 0px 15px 0px;
    box-shadow: 0 20px 20px -20px gray;
  }

  .closed-menu {
    opacity: 0;
    height: 0;
    padding: 0;
    transition: all 0.2s ease-out;
  }

  .closed-menu * {
    display: none;
  }

  .nav-content {
    flex-direction: column;
  }

  .only-mobile {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 10px 30px;
  }

  #logo {
    display: flex;
  }

  i {
    display: block;
    text-align: right;
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

  .btn {
    margin-top: 10px;
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
