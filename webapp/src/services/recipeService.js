export default {
  async getUserRecipes(userId) {
    return await fetch(`http://localhost:8080/recipes/user/${userId}`, {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((result) => {
        return result;
      });
  },

  async addRecipe(newRecipe) {
    const response = await fetch("http://localhost:8080/recipes/add/1", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newRecipe),
    }).catch((error) => {
      console.log(error);
    });
    const data = await response;
    return data;
  },

  async deleteRecipe(id) {
    return await fetch(`http://localhost:8080/recipes/delete/${id}`, {
      method: "DELETE",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((result) => {
        return result;
      });
  },
  async updateRecipe(recipe) {
    const response = await fetch(
      `http://localhost:8080/recipes/update/${recipe.id}`,
      {
        method: "PUT",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(recipe),
      }
    ).catch((error) => {
      console.log(error);
    });
    const data = await response.json();
    return data;
  },
};
