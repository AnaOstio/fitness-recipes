import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import VueRouter from "vue-router";
import WeeklyPlan from "@/views/WeeklyPlan.vue";
import Recipes from "@/views/Recipes.vue";
import PageNotFound from "@/views/404.vue";

const routes = [
    { path: "/", name: "Home", component: Login, meta: { auth: false } },
    { path: "/login", name: "Login", component: Login, meta: { auth: false } },
    { path: "/signup", name: "Signup", component: Signup,  meta: { auth: false } },
    { path: "/week-plan", name: "Week Plan", component: WeeklyPlan,  meta: { auth: true } },
    { path: "/your-recipes", name: "Your Recipes", component: Recipes,  meta: { auth: true }, props: { ownRecipes: true } },
    { path: "/all-recipes", name: "All Recipes", component: Recipes,  meta: { auth: true }, props: { ownRecipes: false } },
    { path: "*", component: PageNotFound },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

// check if is authenticated
const  isAuthenticated = () => {
  const username = sessionStorage.getItem('email');
  const token = sessionStorage.getItem('token');

  const authenticated = username !== null && token !== null
        && username !== undefined && token !== undefined;

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
