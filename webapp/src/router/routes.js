import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import YourRecipes from "../views/YourRecipes.vue";
import VueRouter from "vue-router";
import WeeklyPlan from "@/views/WeeklyPlan.vue";

const routes = [
  { path: "/login", name: "Login", component: Login, meta: { auth: false } },
  { path: "/signup", name: "Signup", component: Signup,  meta: { auth: false } },
  { path: "/weekplan", name: "Week Plan", component: WeeklyPlan,  meta: { auth: true } },
  { path: "/your-recipes", name: "YourRecipes", component: YourRecipes,  meta: { auth: true } },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

// check if is authenticated
const  isAuthenticated = () => {
  const username = sessionStorage.getItem('email');
  const token = sessionStorage.getItem('token');

  const authenticated = username !== null && token !== null && username !== undefined && token !== undefined;

  if (!authenticated) {
    router.push({ name: 'Login' });
  }

  return authenticated;
}

router.beforeEach((to, from, next) => {
  if (to.meta.auth) {
    if (isAuthenticated()) {
      next();
    } else {
      next({ name: 'Login' });
    }
  } else {
    next();
  }
});

export default router;
