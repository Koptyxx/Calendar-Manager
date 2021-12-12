<template>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>My Calendar</title>

  </head>
  <body>
    <ul class="nav nav-tabs justify-content-end">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Files</a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#">New Calendar</a>
          <a class="dropdown-item" href="#">Load a calendar from existing file</a>
          <a class="dropdown-item" href="#">Export my calendar</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Deconnexion</a>
      </li>
    </ul>
  <div>

  </div>
  </body>
</template>

<script>
export default {
  name: "Calendar"
}
</script>

<style scoped>

</style>