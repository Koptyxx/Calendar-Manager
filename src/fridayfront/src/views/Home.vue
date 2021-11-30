<template>

  <div class="home">
    <img alt="Vue logo" src="../assets/friday.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/FridayLogin'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>
