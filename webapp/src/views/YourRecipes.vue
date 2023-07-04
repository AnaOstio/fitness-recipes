<template>
  <div>
    <Header />

    <div class="wrapped">
      <SideBar />
      <main>
        <div class="flex-top">
          <PageTitle />
          <AddRecipeBtn />
        </div>
        <section>
          <div class="grid grid-recipes">
            <RecipeCard />
            <RecipeCard />
            <RecipeCard />
            <RecipeCard />
            <RecipeCard />
            <RecipeCard />
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import SideBar from "@/components/SideBar.vue";
import PageTitle from "@/components/PageTitle.vue";
import RecipeCard from "@/components/RecipeCard.vue";
import AddRecipeBtn from "@/components/AddRecipeBtn.vue";

export default {
  name: "YourRecipes",
  components: {
    Header,
    SideBar,
    PageTitle,
    RecipeCard,
    AddRecipeBtn,
  },
};
</script>

<style>
.grid-recipes {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  column-gap: 40px;
  row-gap: 50px;
  margin: 40px auto;
  margin-left: 25px;
  max-width: 1280px;
}

.flex-top {
  display: flex;
  max-width: 1280px;
  position: relative;
  margin-left: 25px;
}

@media screen and (max-width: 1350px) {
  .grid-recipes {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media screen and (max-width: 1050px) {
  .grid-recipes {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media screen and (max-width: 800px) {
  .grid-recipes {
    grid-template-columns: repeat(1, 1fr);
  }

  .flex-top {
    flex-direction: column;
    height: 130px;
    justify-content: space-between;
    align-items: center;
  }
}

@media screen and (max-width: 640px) {
  .grid-recipes,
  .flex-top {
    margin-left: 0;
  }
}

/* Common */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

main {
  width: 100%;
}

.wrapped {
  display: flex;
  margin: 0 auto;
  padding: 40px 0px;
  width: calc(100% - 50px);
}

.btn {
  height: fit-content;
  white-space: nowrap;
  padding: 10px 20px;
  border: 0px;
  background-color: #00ff7f;
  border-radius: 8px;
  font-size: larger;
}

.btn:hover {
  filter: brightness(95%);
}

/* Provisional */

.card-btn-update,
.card-btn-delete {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eeeeee;
  width: 18%;
  height: 100%;
  margin-left: 2%;
}
</style>
