<template>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

  </head>

  <body class="container">


    
  <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
    <li class="nav-item" role="presentation">
        <a
            class="nav-link active"
            id="tab-login"
            role="tab"
            aria-selected="true"
        >Login</a
        >
    </li>
    <li class="nav-item" role="presentation">
      
        <a
            class="nav-link"
            id="tab-register"
            role="tab"
            aria-selected="false"
        >Register</a
        >
  
    </li>
  </ul>

    <!--
    <div class="tab-content" id="Login">
      <form>
        <div class="form-outline mb-4 text-center">
          <label class="form-label">Username</label>
          <input type="text" id="username" class="form-control" placeholder="Username"/>
        </div>
        <div class="form-outline mb-4 text-center">
          <label class="form-label">Password</label>
          <input type="password" id="password" class="form-control" placeholder="Password"/>
          <input type="checkbox" id="showPassword">
          <label>Show Password</label>
        </div>
        -->
      
        <div class="text-center">
          <button 
                  @click="test"
          
          >
          
              Sign in
          </button>
          <p>Count : {{count}}</p>
        </div>

        <!--
      </form>
    </div>
    -->
  
  </body>

</template>

<script setup>
import {ref} from 'vue'
const count = ref(0)

function test(){
    count.value++
}
</script>
