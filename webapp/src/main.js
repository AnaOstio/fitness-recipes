import Vue from "vue";
import VueRouter from "vue-router";
import Login from "./components/Login.vue";
import Signup from "./components/Signup.vue";
import App from "./App.vue";

Vue.use(VueRouter);

const routes = [
  { path: "/login", component: Login },
  { path: "/signup", component: Signup },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

const app = new Vue({
  el: "#app",
  router,
  render: (h) => h(App),
}).$mount("#app");
