export default {
  async login(email, password) {
    let aux = {
      email: email,
      password: password,
    };

    const response = await fetch("http://localhost:8080/authenticate/login", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(aux),
    });
    const data = await response.json();
    return data;
  },
};
