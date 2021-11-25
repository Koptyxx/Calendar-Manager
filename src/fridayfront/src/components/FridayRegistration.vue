<template>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>

  </head>
  <body class="container">
  <div class="text-center">
    <img src="../assets/friday.png" alt="logo" width="250" height="150">
  </div>
  <!-- navs -->
  <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
    <li class="nav-item" role="presentation">
      <a
          class="nav-link"
          id="tab-login"
          href="./FridayLogin.vue"
          role="tab"
          aria-selected="false"
      >Login</a
      >
    </li>
    <li class="nav-item" role="presentation">
      <a
          class="nav-link active"
          id="tab-register"
          href="#"
          role="tab"
          aria-selected="true"
      >Register</a
      >
    </li>
  </ul>
  <div class="tab-content" id="Registration">
    <p v-if="passwords_error">Passwords aren't the same, try again please !</p>
    <form @submit="testPasswords" action="#">
      <!-- Username input -->
      <div class="form-outline mb-4 text-center">
        <label class="form-label" for="registerUsername">Username</label>
        <input type="text" id="registerUsername" class="form-control" placeholder="Username" title="Only letters are allowed for usernames" v-model="username"/>
      </div>

      <!-- Password input -->
      <div class="form-outline mb-4 text-center">
        <label class="form-label" for="registerPassword">Password</label>
        <input :type="passwordFieldType" id="registerPassword" class="form-control" placeholder="Password" v-model="password"/>
        <input type="checkbox" @click="showPassword">
        <label for="showPassword">Show Password</label>
      </div>

      <!-- Repeat Password input -->
      <div class="form-outline mb-4 text-center">
        <label class="form-label" for="registerRepeatPassword">Repeat password</label>
        <input :type="confirmed_passwordFieldType" id="registerRepeatPassword" class="form-control" placeholder="Password" v-model="confirmed_password"/>
        <input type="checkbox" @click="showConfirmedPassword">
        <label for="showConfirmedPassword">Show Confirmed Password</label>
      </div>

      <!-- Submit button -->
      <div class="text-center">
        <button type="submit" class="btn btn-primary btn-block mb-3">Register</button>
      </div>
    </form>
  </div>
  </body>
  </html>

</template>

<script>
const {createApp} = require("vue");
createApp({
  data(){
    return {
      username: null,
      password: null,
      passwordFieldType: "password",
      confirmed_password: null,
      confirmed_passwordFieldType: "password",
      passwords_error: null
    }
  },
  methods:{
    showPassword(){
      this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password";
    },

    showConfirmedPassword(){
      this.confirmed_passwordFieldType = this.confirmed_passwordFieldType === "password" ? "text" : "password";
    },

    testPasswords(){
      this.passwords_error = this.confirmed_password !== this.password;
    }
  }
}).mount("#Registration");
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
