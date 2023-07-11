import Vue from "vue";
import Vuex from "vuex";
import recipeService from "@/services/recipeService";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {
    deleteRecipe(context, payload) {
      recipeService
        .deleteRecipe(payload.id)
        .then((result) => {
          if (result.status == 200) {
            payload.spliceRecipe();
            payload.toggleModal();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateRecipe(context, payload) {
        recipeService
            .updateRecipe(payload.recipe)
            .then((result) => {
                //if (result.status == 200) {
                    payload.toggleModal();
                // }
            }).catch((err) => {
                console.log(err);
            });
    },
  },
});
