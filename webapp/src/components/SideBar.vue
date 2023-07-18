<template>
  <div class="sidebar">
    <section class="aggregation">
      <p class="aggregation-title">Type o meal</p>
      <div
        v-for="(type, index) in typeOfMeal"
        :key="index"
        class="aggregation-options"
      >
        <input type="checkbox" :id="type" :name="type" />
        <label :for="type">{{ type }}</label>
      </div>
    </section>

    <section class="aggregation">
      <p class="aggregation-title">Average rating</p>

      <div
        v-for="(rate, index1) in rates"
        :key="index1"
        class="stars aggregation-options"
      >
        <i
          v-for="(rateStar, index2) in rate"
          :key="index2"
          class="fa-solid fa-star"
          :class="{ rated: rateStar, unrated: !rateStar }"
        ></i>
        or more
      </div>
    </section>

    <section class="aggregation">
      <p class="aggregation-title">Preparation time</p>

      <div
        v-for="(time, index1) in times"
        :key="index1"
        class="aggregation-options time"
      >
        Less than {{ time }} minutes
      </div>

      <form class="preparatio-time-filter">
        <label for="prepTime">Limit time</label>
        <div class="d-flex-row">
          <input type="number" id="prepTime" min="0" />

          <button @click="" type="button" class="btn-time-filter">Go</button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
export default {
  name: "Sidebar",
  data() {
    return {
      typeOfMeal: [
        "Breakfast",
        "Lunch",
        "Dinner",
        "Dessert",
        "Mid-morning meal",
      ],
      rates: [],
      times: [15, 30, 45, 60, 120],
    };
  },
  created() {
    for (let i = 0; i < 4; ++i) {
      let rate = [false, false, false, false, false];
      this.rates.push(rate.fill(true, 0, i + 1));
    }
  },
};
</script>

<style>
.sidebar {
  width: 20%;
  min-width: 200px;
  padding-top: 15px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.aggregation {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.aggregation-title {
  margin-bottom: 8px;
  font-weight: bold;
}

.aggregation-options {
  display: flex;
  width: fit-content;
  gap: 10px;
}

.rating {
  width: 100%;
  padding: 15px 15px 0px 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 18px;
}

.stars {
  display: flex;
  gap: 10px;
}

.rated {
  color: rgb(255, 204, 0);
}

.unrated {
  color: rgb(224, 224, 224);
}

.separated {
  margin-bottom: 15px;
}

.d-flex-row {
  display: flex;
  max-width: 160px;
  gap: 10px;
}

.preparatio-time-filter {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 5px;
}

input[id="prepTime"] {
  width: 100%;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid rgb(218, 226, 232);
}

.btn-time-filter {
  border: 0;
  padding: 0px 10px;
  border-radius: 0.5rem;
}

.btn-time-filter,
.time,
.stars {
  cursor: pointer;
}

.time:hover,
.stars:hover {
  color: rgb(255, 157, 0);
}

.btn-time-filter:hover {
  filter: brightness(95%);
}

@media screen and (max-width: 640px) {
  .sidebar {
    display: none;
  }
}
</style>
