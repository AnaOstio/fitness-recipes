<template>
  <section class="login" id="login">
    <h1>Login</h1>
    <form @submit="submitForm">
      <label for="email">Email:</label>
      <input type="text" id="email" v-model="email" />
      <div v-if="errorsEmail.length">
        <ul>
          <li v-for="error in errorsEmail">{{ error }}</li>
        </ul>
      </div>

      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" />
      <div v-if="errorsPassword.length" class="errors">
        <ul>
          <li v-for="error in errorsPassword">{{ error }}</li>
        </ul>
      </div>

      <button type="submit">Log in</button>
    </form>
    <a href="/signup">Create new account!</a>
  </section>
</template>

<script>
import userService from "@/services/userService";
export default {
  name: "Login",
  data: function () {
    return {
      errorsEmail: [],
      errorsPassword: [],
      email: "",
      password: "",
    };
  },

  methods: {
    submitForm: function (e) {
      this.errorsEmail = [];
      this.errorsPassword = [];
      if (!this.email || !this.email.trim().length) {
        this.errorsEmail.push("Email field is required");
      }
      if (!this.validEmail(this.email)) {
        this.errorsEmail.push("Email address must be valid.");
      }

      if (!this.password) {
        this.errorsPassword.push("Password field is required");
      }

      e.preventDefault();

      if (!this.errorsPassword.length && !this.errorsEmail.length) {
        userService.login(this.email, this.password)
          .then((res) => {
            if (res.status >= 200 && res.status < 400) {
              console.log("esta completada esta parte")
            } else {
              this.errorsPassword.push("Credentials error, try it again")
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    validEmail: function (email) {
      let re =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
  },
};
</script>

<style>
/* section {
  font-family: "Inter", sans-serif;
  position: absolute;
  left: 50%;
  top: 50%;
  background-color: #d8dada;
  padding: 25px;
  border-radius: 13px;
  max-width: 400px;
  width: 100%;
  transform: translate(-50%, -50%);
}

form {
  display: flex;
  flex-direction: column;
}

input[type="text"],
input[type="password"] {
  margin-top: 5px;
  border-radius: 5px;
  margin-bottom: 10px;
  border: 1px solid #d8dada;
  padding: 5px;
}

button {
  margin-bottom: 10px;
  max-width: 50%;
  width: 100%;
  align-self: center;
  background-color: rgb(0, 255, 127);
  color: rgb(73, 41, 86);
  height: 30px;
  border-radius: 13px;
}

a,
h1 {
  margin-top: 6px;
  text-align: center;
}

a {
  display: block;
} */
</style>
