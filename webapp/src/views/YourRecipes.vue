<template>
  <div>
    <header>
      <nav>
        <router-link to="/" class="nav-btn">
          <!-- <figure><img src="" alt="Logo" /></figure> -->
          <div id="logo">Logo</div>
        </router-link>

        <router-link to="#" class="nav-btn">All recipes</router-link>

        <input type="text" />

        <router-link to="#" class="nav-btn">Weekly plan</router-link>
        <router-link to="#" class="nav-btn">Your recipes</router-link>
        <router-link to="#" class="nav-btn"
          ><button class="btn">Log out</button></router-link
        >
      </nav>
    </header>

    <div class="wrapped">
      <div class="sidebar"></div>
      <main>
        <h1 class="page-title">Your recipes</h1>
        <section>
          <div class="grid grid-recipes">
            <div class="card-recipe">
              <div class="card-recipe-img">Recipe image</div>
              <div class="card-recipe-body">
                <h2 class="recipe-name">Recipes Name</h2>
                <h3 class="recipe-type">Type: breakfast</h3>
                <div class="recipe-time">
                  <div class="time-icon">T</div>
                  <h3 class="time">30 min</h3>
                </div>
              </div>
              <div class="card-recipe-footer">
                <h3 class="recipe-rating">Stars</h3>
                <div class="card-btn-update">U</div>
                <div class="card-btn-delete">D</div>
              </div>
            </div>

            <div class="card-recipe">
              <div class="card-recipe-img">Recipe image</div>
              <div class="card-recipe-body">
                <h2 class="recipe-name">Recipes Name</h2>
                <h3 class="recipe-type">Type: breakfast</h3>
                <div class="recipe-time">
                  <div class="time-icon">T</div>
                  <h3 class="time">30 min</h3>
                </div>
              </div>
              <div class="card-recipe-footer">
                <h3 class="recipe-rating">Stars</h3>
                <div class="card-btn-update">U</div>
                <div class="card-btn-delete">D</div>
              </div>
            </div>

            <div class="card-recipe">
              <div class="card-recipe-img">Recipe image</div>
              <div class="card-recipe-body">
                <h2 class="recipe-name">Recipes Name</h2>
                <h3 class="recipe-type">Type: breakfast</h3>
                <div class="recipe-time">
                  <div class="time-icon">T</div>
                  <h3 class="time">30 min</h3>
                </div>
              </div>
              <div class="card-recipe-footer">
                <h3 class="recipe-rating">Stars</h3>
                <div class="card-btn-update">U</div>
                <div class="card-btn-delete">D</div>
              </div>
            </div>

            <div class="card-recipe">
              <div class="card-recipe-img">Recipe image</div>
              <div class="card-recipe-body">
                <h2 class="recipe-name">Recipes Name</h2>
                <h3 class="recipe-type">Type: breakfast</h3>
                <div class="recipe-time">
                  <div class="time-icon">T</div>
                  <h3 class="time">30 min</h3>
                </div>
              </div>
              <div class="card-recipe-footer">
                <h3 class="recipe-rating">Stars</h3>
                <div class="card-btn-update">U</div>
                <div class="card-btn-delete">D</div>
              </div>
            </div>

            <div class="card-recipe">
              <div class="card-recipe-img">Recipe image</div>
              <div class="card-recipe-body">
                <h2 class="recipe-name">Recipes Name</h2>
                <h3 class="recipe-type">Type: breakfast</h3>
                <div class="recipe-time">
                  <div class="time-icon">T</div>
                  <h3 class="time">30 min</h3>
                </div>
              </div>
              <div class="card-recipe-footer">
                <h3 class="recipe-rating">Stars</h3>
                <div class="card-btn-update">U</div>
                <div class="card-btn-delete">D</div>
              </div>
            </div>
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

main {
  width: 100%;
}

h1 {
  font-size: 60px;
}

nav {
  display: flex;
  justify-content: space-around;
  align-items: center;
  list-style: none;
  padding: 10px;
}

nav input {
  max-width: 350px;
  width: 100%;
  height: 35px;
  border: 0px;
  border-radius: 16px;
  background-color: #eeeeee;
}

.nav-btn {
  text-decoration: none;
  color: black;
}

.page-title {
  max-width: 1280px;
  margin-left: 25px;
  width: calc(100% - 50px);
}

.recipes-view {
  display: flex;
}

.sidebar {
  width: 20%;
  min-width: 200px;
  background-color: aquamarine;
}

.wrapped {
  display: flex;
  margin: 0 auto;
  padding: 40px 0px;
  width: calc(100% - 50px);
}

.btn {
  width: fit-content;
  padding: 10px 20px;
  border: 0px;
  background-color: #00ff7f;
  border-radius: 8px;
}

.btn:hover {
  filter: brightness(95%);
}

.grid-recipes {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  column-gap: 40px;
  row-gap: 50px;
  margin: 40px auto;
  margin-left: 25px;
  max-width: 1280px;
}

.card-recipe {
  height: 390px;
  width: 100%;
  max-width: 350px;
  background-color: #e4e4da;
  border-radius: 12px;
  padding: 13px;
  margin: auto;
}

.card-recipe-body {
  display: flex;
  padding: 15px 0px;
  width: 100%;
  height: 55%;
  flex-direction: column;
  justify-content: space-between;
}

.card-recipe-footer {
  display: flex;
  width: 100%;
  height: 10%;
}

.recipe-time {
  display: flex;
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
}

@media screen and (max-width: 640px) {
  .sidebar {
    display: none;
  }

  .page-title {
    text-align: center;
  }
}

/* Provisional */

.time-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eeeeee;
  width: 10%;
  height: 100%;
  margin-right: 2%;
}

.recipe-rating {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eeeeee;
  height: 100%;
  width: 60%;
}

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

.card-recipe-img {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 35%;
  background-color: #eeeeee;
  border-radius: 8px;
}

#logo {
  display: flex;
  background-color: #eeeeee;
  border-radius: 4px;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 70px;
}
</style>
