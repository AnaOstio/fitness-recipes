export default {
  async getUserRecipes(userId) {
    try {
      return (response = await fetch(
        `http://localhost:8080/recipes/user/${userId}`,
        {
          method: "GET",
          credentials: "include",
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
        .then((response) => response.json())
        .then((result) => {
          return result;
        }));
    } catch (error) {}
  },
};
