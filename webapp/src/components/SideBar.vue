<template>
  <div
    :class="{
      'sidebar-wrapper': showMobileSidebar || !onMobile,
      'sidebar-wrapper-mobile-closed': !showMobileSidebar && onMobile,
    }"
  >
    <div class="sidebar">
      <section class="aggregation">
        <p class="aggregation-title">Type o meal</p>
        <div
          v-for="(type, index) in typeOfMeal"
          :key="index"
          class="aggregation-options"
        >
          <input
            type="checkbox"
            :id="type"
            :name="type"
            :value="type"
            @change="setTypeOfMealFilter($event)"
          />
          <label :for="type">{{ type }}</label>
        </div>
      </section>

      <section class="aggregation">
        <p class="aggregation-title">Average rating</p>

        <div
          v-for="(rate, index1) in rates"
          :key="index1"
          class="stars aggregation-options"
          @click="setRateFilter(index1)"
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
          @click="setTimeFilter(time)"
        >
          Less than {{ time }} minutes
        </div>

        <form class="preparatio-time-filter">
          <label for="prepTime">Limit time</label>
          <div class="d-flex-row">
            <input type="number" id="prepTime" min="0" v-model="timeFilter" />

            <button
              @click="setTimeFilter(timeFilter)"
              type="button"
              class="btn-time-filter"
            >
              Go
            </button>
          </div>
        </form>
      </section>

      <div v-show="onMobile" @click="showSidebar" class="sidebar-close-btn">
        <i class="fa-sharp fa-solid fa-circle-xmark"></i>
      </div>
    </div>

    <div
      v-show="onMobile && !showMobileSidebar"
      @click="showSidebar"
      class="sidebar-open-btn"
    >
      <i class="fa-solid fa-caret-down"></i>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";

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
      typeOfMealFilter: {},
      rateFilter: ref(0),
      timeFilter: 0,
      showMobileSidebar: false,
      onMobile: false,
    };
  },
  mounted() {
    window.addEventListener("resize", this.onResize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResize);
  },
  created() {
    for (let i = 0; i < 4; ++i) {
      let rate = [false, false, false, false, false];
      this.rates.push(rate.fill(true, 0, i + 1));
    }

    this.onResize();
  },
  methods: {
    onResize() {
      this.onMobile = window.innerWidth <= 640;
    },

    showSidebar() {
      this.showMobileSidebar = !this.showMobileSidebar;
    },

    setTypeOfMealFilter(e) {
      this.typeOfMealFilter[e.target.value] = e.target.checked;

      this.$emit("filter", {typeOfMeal: this.getTypeMealValues(),
                                      averageRating: this.rateFilter,
                                      timePreparation: this.timeFilter});
    },

    setRateFilter(index) {
      this.rateFilter = index + 1;

      this.$emit("filter", {typeOfMeal: this.getTypeMealValues(),
                                      averageRating: this.rateFilter,
                                      timePreparation: this.timeFilter});
    },

    setTimeFilter(time) {
      this.timeFilter = time;


      this.$emit("filter", {typeOfMeal: this.getTypeMealValues(),
                                      averageRating: this.rateFilter,
                                      timePreparation: this.timeFilter});
    },

    getTypeMealValues(){
      let typeMealFilters = [];
      for (let key in this.typeOfMealFilter) {
        if (this.typeOfMealFilter[key]) {
          typeMealFilters.push(key);
        }
      }
      return typeMealFilters;
    }
  },
};
</script>

<style>
.sidebar {
  width: 20%;
  height: 100%;
  min-width: 230px;
  padding: 35px 15px 20px 15px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  background-color: #ffff;
  position: relative;
}

.sidebar-wrapper {
  height: 100%;
  min-width: 230px;
  width: 20%;
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

.sidebar-wrapper-mobile-closed {
  width: 0px;
}

.sidebar-wrapper-mobile-closed .sidebar {
  display: none;
}

.sidebar-close-btn {
  position: absolute;
  top: 12px;
  right: 12px;
}

.sidebar-open-btn {
  position: fixed;
  top: 290px;
  left: 0px;
  z-index: 3;
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eeeeeeb8;
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
}

.sidebar-open-btn:hover {
  cursor: pointer;
  filter: brightness(95%);
}

.sidebar-open-btn i {
  font-size: 30px;
  transform: rotate(-90deg);
}

.sidebar-close-btn i::before {
  color: rgb(129, 129, 129);
}

.sidebar-close-btn i::before {
  font-size: 20px;
}

.sidebar-close-btn i:hover {
  filter: brightness(70%);
  cursor: pointer;
}

@media screen and (max-width: 640px) {
  .sidebar-wrapper {
    background-color: rgba(168, 168, 168, 0.596);
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 90px;
    z-index: 3;
    transition: width 0.2s ease-out;
    overflow: hidden;
    overflow-y: auto;
  }
}
</style>
