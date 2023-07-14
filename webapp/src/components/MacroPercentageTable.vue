<template>
  <table class="separated">
    <caption>
      Macronutrients Percentages in Gr.*
    </caption>
    <tr>
      <th>Protein</th>
      <td>
        <input
          id="protein"
          type="number"
          v-model="macronutrientsPercentages.protein"
        />
      </td>
    </tr>
    <tr>
      <th>Carbohydrates</th>
      <td>
        <input
          id="carbo"
          type="number"
          v-model="macronutrientsPercentages.carbohydrates"
        />
      </td>
    </tr>
    <tr>
      <th>Greases</th>
      <td>
        <input
          id="greases"
          type="number"
          v-model="macronutrientsPercentages.greases"
        />
      </td>
    </tr>
    <tr>
      <th>Fiber</th>
      <td>
        <input
          id="fiber"
          type="number"
          v-model="macronutrientsPercentages.fiber"
        />
      </td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";

export default {
  name: "MacroPercentageTable",
  props: ["macros"],
  data() {
    return {
      macronutrientsPercentages: {
        protein: "",
        carbohydrates: "",
        greases: "",
        fiber: "",
      },
    };
  },
  created() {
    this.macronutrientsPercentages = { ...this.macros };
  },
  watch: {
    macronutrientsPercentages() {
      this.$emit("setMacroContent", this.macronutrientsPercentages);
    },

    macros(newMacro) {
      this.macronutrientsPercentages = newMacro;
    },
  },
};
</script>

<style scoped>
.separated {
  margin-bottom: 15px;
}

caption {
  text-align: left;
  margin-bottom: 10px;
}

input {
  width: 100%;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid rgb(218, 226, 232);
}

table {
  width: 50%;
}

th {
  background-color: rgb(217, 227, 235);
  padding: 0px 10px;
}

@media screen and (max-width: 1150px) {
  table {
    width: 100%;
  }
}
</style>
