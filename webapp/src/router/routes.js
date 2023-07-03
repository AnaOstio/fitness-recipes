import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import YourRecipes from "../views/YourRecipes.vue";
import VueRouter from "vue-router";

const routes = [
  { path: "/login", name: "Login", component: Login },
  { path: "/signup", name: "Signup", component: Signup },
  { path: "/your-recipes", name: "YourRecipes", component: YourRecipes },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

export default router;
