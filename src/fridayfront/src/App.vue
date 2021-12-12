<template>

  <body class="container">
    <div class="text-center">
      <img src="./assets/friday.png" alt="logo" width="250" height="150">
    </div>
    <p>

    </p>

    <nav>
      <router-link to="/login">Login</router-link> |
      <router-link to="/register">register</router-link>
    </nav>
  </body>
  <router-view/>


</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
