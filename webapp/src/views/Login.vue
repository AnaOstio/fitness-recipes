<template>
  <main>
    <section>
      <div id="logo">Logo</div>
      <form class="login">
        <h1 class="separated">Login</h1>
        <label for="email">Email</label>
        <input class="separated" type="text" id="email" v-model="email" />
        <div class="errorMsg" v-if="errorEmail">
          <span>{{ errorEmail }}</span>
        </div>

        <label for="password">Password</label>
        <input
          class="separated"
          type="password"
          id="password"
          v-model="password"
        />
        <div class="errorMsg" v-if="errorPassword">
          <span>{{ errorPassword }}</span>
        </div>

        <div class="login-btn">
          <LogOutBtn :click="submitForm" :btnText="'Log in'" type="submit" />
          <a href="/signup">Create new account!</a>
        </div>
      </form>
    </section>
  </main>
</template>

<script>
import userService from "@/services/userService";
import LogOutBtn from "@/components/LogOutBtn.vue";

export default {
  name: "Login",
  components: { LogOutBtn },
  data: function () {
    return {
      errorEmail: "",
      errorPassword: "",
      email: "",
      password: "",
    };
  },

  methods: {
    submitForm: function (e) {
      this.errorEmail = "";
      this.errorPassword = "";
      if (!this.email || !this.email.trim().length) {
        this.errorEmail = "Email field is required";
      }

      if (!this.password) {
        this.errorPassword = "Password field is required";
      }

      e.preventDefault();

      if (!this.errorPassword.length && !this.errorEmail.length) {
        userService
          .login(this.email, this.password)
          .then((res) => {
            if (res.status >= 200 && res.status < 400) {
              // save data
              sessionStorage.setItem("token", res.token);
              sessionStorage.setItem("email", res.email);
              sessionStorage.setItem("userId", res.id);

              // move to the next page
              this.$router.push("/your-recipes");
            } else {
              this.errorPassword = "Credentials error, try it again";
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

main {
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Inter", sans-serif;
  width: 100%;
  height: 100%;
}

section {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 40px;
}

#logo {
  display: flex;
  background-color: #eeeeee;
  border-radius: 15px;
  justify-content: center;
  align-items: center;
  width: 120px;
  height: 120px;
  font-size: 25px;
}

.login {
  background-color: #eeeeee;
  padding: 25px;
  border-radius: 13px;
  min-width: 400px;
}

.login-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  padding: 10px;
}

.login-btn button {
  min-width: 200px;
}

.separated {
  margin-bottom: 15px;
}

form {
  display: flex;
  flex-direction: column;
}

input {
  width: 100%;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid rgb(218, 226, 232);
}

label {
  margin-bottom: 10px;
}

.errorMsg {
  color: rgb(255, 70, 70);
  margin-top: -12px;
  margin-bottom: 15px;
}

a,
h1 {
  text-align: center;
}
</style>
