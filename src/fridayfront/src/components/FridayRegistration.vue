<template>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>

  </head>
  <body class="container">

  <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
    <li class="nav-item" role="presentation">
      <router-link to="/login">
        <a
            class="nav-link"
            id="tab-login"
            role="tab"
            aria-selected="false"
        >Login
        </a>
      </router-link>
    </li>
    <li class="nav-item" role="presentation">
      <router-link to="/register">
        <a class="nav-link active"
            id="tab-register"
            href="#"
            role="tab"
            aria-selected="true"
        >Register
        </a>
      </router-link>
    </li>
  </ul>

  <div id='login'>
      <p v-if="passwords_error">Passwords aren't the same, try again please !</p>
      <form
          action="./Test.vue"
          @submit="testPasswords"
      >

          <div class="form-outline mb-4 text-center">
              <label class="form-label" for="username">Username</label>
              <input type="text" id="username" class="form-control" placeholder="Username" v-model="username"/>
          </div>
          <!-- Password input -->
          <div class="form-outline mb-4 text-center">
              <label class="form-label">Password</label>
              <input :type="passwordFieldType" class="form-control" placeholder="Password" v-model="password"/>
              <input type="checkbox" @click="showPassword">
              <label>Show Password</label>
          </div>

          <div class="form-outline mb-4 text-center">
              <label class="form-label">Repeat password</label>
              <input :type="confirmed_passwordFieldType" class="form-control" placeholder="Enter your password again please" v-model="confirmed_password"/>
              <input type="checkbox" @click="showPassword">
              <label>Show Password</label>
          </div>

          <div>
              <!--<router-link to="/test" v-if="!testPasswords"></router-link>-->
              <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
          </div>



      </form>


  </div>
  </body>
</template>

<script>

export default {
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
      console.log(this.password + " = " + this.confirmed_password)
      console.log("Are they the same passwords : " + this.passwords_error)
    }


  }
};


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
