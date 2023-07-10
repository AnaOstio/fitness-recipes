import Vue from "vue";
import Vuex from "vuex";
import recipeService from "@/services/recipeService";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {
    deleteRecipe({ id }) {
      recipeService
        .deleteRecipe(id)
        .then((result) => {
          if (result.status == 200) {
            // TODO OK message
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
