import Vue from "vue";
import VueRouter from "vue-router";
import App from "./App.vue";
import router from "./router/routes.js";
import store from "./store/store";

Vue.use(VueRouter);

const app = new Vue({
  el: "#app",
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
