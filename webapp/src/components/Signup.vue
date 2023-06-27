<template>
  <section class="login" id="login">
    <h1>Create an account</h1>
    <form @submit="submitForm">
      <label for="Username">Username:</label>
      <input type="text" id="Username" v-model="username" />
      <div v-if="errorsUsername.length">
        <ul>
          <li v-for="error in errorsUsername">{{ error }}</li>
        </ul>
      </div>

      <label for="email">Email:</label>
      <input type="text" id="email" v-model="email" />
      <div v-if="errorsEmail.length">
        <ul>
          <li v-for="error in errorsEmail">{{ error }}</li>
        </ul>
      </div>

      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" />

      <label for="repeatPassword">Confirm Password:</label>
      <input type="password" id="repeatPassword" v-model="repeatPassword" />
      <div v-if="errorsPassword.length" class="errors">
        <ul>
          <li v-for="error in errorsPassword">{{ error }}</li>
        </ul>
      </div>

      <button type="submit">Sign up</button>
    </form>
    <a href="/login">Have already an account? Log in!</a>
  </section>
</template>

<script>
export default {
  name: "Signup",
  data: function () {
    return {
      errorsEmail: [],
      errorsPassword: [],
      errorsUsername: [],
      email: "",
      password: "",
      username: "",
      repeatPassword: "",
    };
  },

  methods: {
    submitForm: function (e) {
      this.errorsEmail = [];
      this.errorsPassword = [];
      this.errorsUsername = [];

      if (!this.username || !this.username.trim().length) {
        this.errorsUsername.push("Email field is required");
      }

      if (!this.email || !this.email.trim().length) {
        this.errorsEmail.push("Email field is required");
      }
      if (!this.validEmail(this.email)) {
        this.errorsEmail.push("Email address must be valid.");
      }

      if (!this.password) {
        this.errorsPassword.push("Password field is required");
      }

      if (!this.repeatPassword) {
        this.errorsPassword.push("Confirm password field is required");
      }

      if (this.repeatPassword !== this.password) {
        this.errorsPassword.push(
          "Confirm password and password must be the same"
        );
      }

      e.preventDefault();

      if (
        !this.errorsPassword.length &&
        !this.errorsEmail.length &&
        !this.errorsUsername.length
      ) {
        console.log("Sin errores");
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
section {
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
  display: block;
}

a {
  display: block;
}
</style>
