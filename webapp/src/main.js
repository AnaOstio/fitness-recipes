import Vue from "vue";
import VueRouter from "vue-router";
import App from "./App.vue";
import router from "./router/routes.js";

Vue.use(VueRouter);

const app = new Vue({
  el: "#app",
  router,
  render: (h) => h(App),
}).$mount("#app");
