import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import VueRouter from "vue-router";
import WeeklyPlan from "@/views/WeeklyPlan.vue";

const routes =  [
  { path: "/login", name: "Login", component: Login },
  { path: "/signup", name: "Signup", component: Signup },
  { path: "/weekplan", name: "Week Plan", component: WeeklyPlan },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

export default router;