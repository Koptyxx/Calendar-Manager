<template>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

  </head>
  <body class="container">
  <div class="text-center">
    <img src="../assets/friday.png" alt="logo" width="250" height="150">
  </div>
  <!-- navs -->
  <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
    <li class="nav-item" role="presentation">
      <a
          class="nav-link active"
          id="tab-login"
          href="#"
          role="tab"
          aria-selected="true"
      >Login</a>
    </li>
    <li class="nav-item" role="presentation">
      <a
          class="nav-link"
          id="tab-register"
          href="./FridayRegistration.vue"
          role="tab"
          aria-selected="false"
      >Register</a>
    </li>
  </ul>
  <div class="tab-content" id="Login">
    <form>
      <!-- Username input -->
      <div class="form-outline mb-4 text-center">
        <label class="form-label" for="loginName">Username</label>
        <input type="text" id="loginName" class="form-control" placeholder="Username" v-model="username"/>
      </div>
      <!-- Password input -->
      <div class="form-outline mb-4 text-center">
        <label class="form-label">Password</label>
        <input :type="passwordFieldType" class="form-control" placeholder="Password" v-model="password"/>
        <input type="checkbox" @click="showPassword">
        <label for="showPassword">Show Password</label>
      </div>


      <!-- Submit button -->
      <div class="text-center">
        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
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
      username: "",
      password: "",
      passwordFieldType : "password"
    }
  },
  methods:{

    showPassword(){
      this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password";
    }
  }
}).mount("#Login");
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
