import Vue from "vue";

Vue.config.productionTip = false;

const NotFound = { template: "<p>Page bot found</p>" };
const Home = { template: "<p>Toy en home</p>" };
const About = { template: "<p>Toy en about</p>" };

const routes = {
  "/": Home,
  "/about": About,
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
