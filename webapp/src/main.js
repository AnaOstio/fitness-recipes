import Vue from "vue";
import Login from "./components/Login.vue";
import Signup from "./components/Signup.vue";

Vue.config.productionTip = false;

const NotFound = { template: "<p>Page not found</p>" };
const Home = { template: "<p>Toy en home</p>" };
const About = { template: "<p>Toy en about</p>" };

const routes = {
  "/": Home,
  "/about": About,
  "/login": Login,
  "/signup": Signup,
};

const app = new Vue({
  el: "#app",
  data: {
    currentRoute: window.location.pathname,
  },
  computed: {
    ViewComponent() {
      const matchingView = routes[this.currentRoute];
      return matchingView || NotFound;
    },
  },
  render(h) {
    return h(this.ViewComponent);
  },
});
