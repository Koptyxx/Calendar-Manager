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
        <a class="nav-link">{{ username }}</a>
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
          <label class="form-label">Location :</label>
          <input type="text" id="location" class="form-control" placeholder="8 avenue des Champs Elysée, Paris" v-model="location"/>
          <div>
            <date-picker class="mb-1 mt-3" is24hr is-required :mode="mode" v-model="date"/>
          </div>
          <div>
            <input type="checkbox" @click="changeAllDay">
            <label class="form-label p-2">All day</label>
          </div>
          <button type="button" v-on:click="addEvent" class="btn btn-success mt-3">Add Event</button>
        </form>
        <button v-if="!displayAddEvent" type="button" v-on:click="showAddEventForm" class="btn btn-success mt-3">Add Event</button>
      </div>
      <div class="col">
        <button type="button" v-on:click="test" class="btn btn-info mt-3">Modify Event</button>
      </div>
      <div class="col">

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

    const todos = [];

    return {
      incId: todos.length,
      todos,
      username: JSON.parse(localStorage.getItem('user')),
      isAllDay: false,
      mode: "dateTime",
      displayAddEvent: false,
      description: "",
      location: "",
      date: new Date(),

    };
  },
  methods: {
    changeAllDay(){
      this.isAllDay = !this.isAllDay;
      this.mode = this.mode === "dateTime" ? "date" : "dateTime";
    },
    showEvent(){
      console.log("Nouvel Event : \n Descritpion : " + this.description + "\nLocation : " + this.location + "\n Date : " + this.date);
    },

    addEvent() {
      this.todos.push(
          {
            description: this.description,
            dates: this.date,
            location: this.location
          }
      );
      this.displayAddEvent = false

     let url = "http://localhost:8080/event/save";
      fetch(url, {
        method:'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          description: this.description,
          location: this.location,
          start:  "20211231T170000",
          username: this.username,
          isAllDay: this.isAllDay
        })
      });
    },
    test (){
      /*'use strict';
      const ical = require('ical');*/
      let url = "http://localhost:8080/event/find/username/" + this.username;
      fetch(url)
          .then(res => res.json())
          .then(res => {
            const datas = res[0].calendar;
            for(let k in datas["components"]){
              const data = datas["components"][k]
              if(data["name"] === "VEVENT"){
                this.todos.push({
                  description: data["description"]["value"],
                  dates: data["dateStamp"]["dateTime"],
                  location: data["location"]["value"],
                });
              }
            }
          })

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