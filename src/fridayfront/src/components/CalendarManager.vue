<template>
    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>My Calendar</title>
    </head>
  <body>
    <ul class="nav nav-tabs justify-content-end">
      <li class="nav-item">
        <a class="nav-link">Samy Ghamri</a>
      </li>

      <li class="nav-item">
        <button class="btn dropdown-toggle text-primary" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
          Files
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li><a class="dropdown-item" href="#">New calendar</a><a class="dropdown-item" href="#">Load a calendar from
            existing
            file</a></li>
          <li><a class="dropdown-item" href="#">Export my calendar</a></li>
        </ul>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/login">Deconnexion</a>
      </li>
    </ul>
  <div class="container">
    <div class="row">
      <p>informations intelligente à propos du prochain rdv</p>
      <div class="col">Date</div>
      <div class="col">Heure</div>
      <div class="col">Lieu</div>
      <div class="col">Météo</div>
    </div>
    <div class="row">
      <p> liste des RDVs de la journée par ordre chronologique avec les informations principale (heure, lieu, etc)</p>
    </div>
    <div class="row">
      <p>Un calendrier du mois avec par jour, une petite barre horizontale indiquant la présence d'un RDV </p>
    </div>
  </div>
    <Calendar />
    <DatePicker v-model="date" />
  </body>
</template>

<script>
import { Calendar, DatePicker } from 'v-calendar';

export default {
  components: {
    Calendar,
    DatePicker,
  },
  data() {
    return {
      date: new Date(),
    };
  },
}
</script>

<style scoped>

</style>