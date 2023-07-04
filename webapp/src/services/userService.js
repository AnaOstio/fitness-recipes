export default {
  async login(email, password) {
    let aux = {
      email: email,
      password: password,
    };

    console.log(JSON.stringify(aux));

    const response = await fetch("http://localhost:8080/api/login", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(aux),
    });
    const data = await response;
    return data;
  },
};
