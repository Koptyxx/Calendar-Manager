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
    <h2 class="text-decoration-underline text-danger">My Calendar</h2>
    <Calendar
        :attributes="attributes"
        is-expanded
        is-dark/>
    <div class="row">
      <div class="col">
        <form v-if="displayAddEvent">
          <label class="form-label">Description</label>
          <input type="text" id="description" class="form-control" placeholder="Finishing my Java Project" v-model="description"/>
          <div>
            <date-picker class="mb-3 mt-3" is24hr v-model="date" mode="dateTime" :timezone="timezone"/>
          </div>

          <button type="button" v-on:click="addEvent" class="btn btn-success mt-3">Add Event</button>
        </form>
        <button v-if="!displayAddEvent" type="button" v-on:click="showAddEventForm" class="btn btn-success mt-3">Add Event</button>
      </div>
      <div class="col">
        <button type="button" class="btn btn-info mt-3">Modify Event</button>
      </div>
      <div class="col">
        <div>
          <p v-for="todo in todos" :key="todo.description"> {{todo.description}} </p>
        </div>
        <button type="button" class="btn btn-danger mt-3">Delete Event</button>
      </div>
    </div>

  </body>
</template>

<script>
import { Calendar, DatePicker } from 'v-calendar';

export default {
  components: {
    Calendar,
    DatePicker
  },
  data() {
    const todos = [

    ];
    return {
      incId: todos.length,
      todos,
      displayAddEvent: false,
      description: "",
      date: new Date(),
      timezone: ''
    };
  },
  methods: {
    addEvent() {
      this.todos.push(
          {
            description: this.description,
            isComplete: false,
            dates: this.date
          }
      );
      this.displayAddEvent = false
    },
    showAddEventForm() {
      this.displayAddEvent = true
    }
  },
  computed: {
    attributes() {
      return [
        // Attributes for todos
        ...this.todos.map(todo => ({
          dates: todo.dates,
          bar: true,
          popover: {
            label: todo.description
          },
          customData: todo,
        })),
      ];
    },
  },
}
</script>

<style scoped>

</style>