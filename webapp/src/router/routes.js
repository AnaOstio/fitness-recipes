import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import VueRouter from "vue-router";

const routes =  [
  { path: "/login", name: "Login", component: Login },
  { path: "/signup", name: "Signup", component: Signup },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

export default router;