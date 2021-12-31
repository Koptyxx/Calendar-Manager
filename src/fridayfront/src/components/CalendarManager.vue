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
        <li><a class="dropdown-item"  v-on:click="showEvent">New calendar</a></li>
        <li><a class="dropdown-item"  v-on:click="showEvent">Load a calendar from existing file</a></li>
        <li><a class="dropdown-item"  v-on:click="showEvent">Export my calendar</a></li>
      </ul>
    </li>
    <li class="nav-item">
      <router-link to="/login">
        <a class="nav-link">Deconnexion</a>
      </router-link>
    </li>
  </ul>
  <div class="container">
    <h2 class="text-decoration-underline text-danger">Next Event</h2>
    <div class="row">
      <div class="col">
        <h4 class="text-decoration-underline">Description</h4>
      </div>
      <div class="col">
        <h4 class="text-decoration-underline">Location</h4>
      </div>
      <div class="col">
        <h4 class="text-decoration-underline">Heure</h4>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <h6>{{this.nextTODO.description}}</h6>
      </div>
      <div class="col">
        <h6>{{this.nextTODO.location}}</h6>
      </div>
      <div class="col">
        <h6>{{this.nextTODO.dates}}</h6>
      </div>
    </div>
    <div class="row">
      <h2 class="text-decoration-underline text-danger">Events of the day</h2>
      <div class="row">
        <div class="col">
          <h4 class="text-decoration-underline">Description</h4>
        </div>
        <div class="col">
          <h4 class="text-decoration-underline">Location</h4>
        </div>
        <div class="col">
          <h4 class="text-decoration-underline">Heure</h4>
        </div>
      </div>
      <div class="row mt-4 mb-2" v-for="todo in todaysTODO" :key="todo">
        <div class="col">
          <h6>{{todo.description}}</h6>
        </div>
        <div class="col">
          <h6>{{todo.location}}</h6>
        </div>
        <div class="col">
          <h6>{{todo.dates}}</h6>
        </div>
      </div>

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

      <button type="button"
              v-on:click="selectingTodaysRDV"
              class="btn btn-danger mt-3">Delete Event</button>
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
    const todaysTODO = [];
    return {
      incId: todos.length,
      todos,
      todaysTODO,
      nextTODO: undefined,

      username: JSON.parse(localStorage.getItem('user')),
      isAllDay: false,
      mode: "dateTime",
      displayAddEvent: false,
      description: "",
      location: "",
      date: new Date()
    };
  },
  methods: {
    changeAllDay(){
      this.isAllDay = !this.isAllDay;
      this.mode = this.mode === "dateTime" ? "date" : "dateTime";
      console.log(this.todaysTODO);
    },
    showEvent(){
      console.log("hey je veux faire ça !")
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

      let data_date = this.date.getFullYear().toString();
      if((this.date.getMonth() + 1).toString().length === 1){
        data_date += ("0" + (this.date.getMonth()+1).toString());
      }
      else{
        data_date += (this.date.getMonth()+1).toString();
      }
      if(this.date.getDate().toString().length === 1){
        data_date += ("0" + this.date.getDate().toString());
      }
      else{
        data_date += this.date.getDate().toString();
      }
      data_date += "T";
      if(this.date.getHours().toString().length === 1){
        data_date += ("0" + this.date.getHours().toString());
      }
      else{
        data_date += this.date.getHours().toString();
      }
      if(this.date.getMinutes().toString().length === 1){
        data_date += ("0" + this.date.getMinutes().toString());
      }
      else{
        data_date += this.date.getMinutes().toString();
      }
      if(this.date.getSeconds().toString().length === 1){
        data_date += ("0"+this.date.getSeconds().toString());
      }
      else{
        data_date += this.date.getSeconds().toString();
      }

      let url = "http://localhost:8080/event/save";
      fetch(url, {
        method:'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          description: this.description,
          location: this.location,
          start:  data_date,
          username: this.username,
          isAllDay: this.isAllDay
        })
      });
    },
    test (){
      let url = "http://localhost:8080/event/find/username/" + this.username;
      fetch(url)
          .then(res => res.json())
          .then(res => {
            for(let i in res){
              const datas = res[i].calendar;
              for(let k in datas["components"]){
                const data = datas["components"][k]

                if(data["name"] === "VEVENT"){
                  this.todos.push({
                    description: data["description"]["value"],
                    dates: data["startDate"]["date"],
                    location: data["location"]["value"],
                  });

                  const today = new Date();
                  const date_event = data["startDate"]["date"];
                  let year = "";
                  let month = "";
                  let day = "";
                  let hours = "";
                  let minutes = "";
                  for(let i = 0; i < 16; i++){
                    if(i < 4)
                      year += date_event[i];
                    if (i > 4 && i < 7)
                      month += date_event[i]
                    if(i > 7 && i < 10)
                      day += date_event[i];
                    if(i > 10 && i < 13)
                      hours += date_event[i];
                    if(i > 13 && i < 16)
                      minutes += date_event[i];
                  }
                  const dat = new Date(parseInt(year,10), parseInt(month, 10) - 1,  parseInt(day, 10));
                  dat.setHours(parseInt(hours,10), parseInt(minutes, 10));

                  if(this.nextTODO === undefined){
                    this.nextTODO = {
                      description: data["description"]["value"],
                      dates: data["startDate"]["date"],
                      location: data["location"]["value"],
                    }
                  }
                  /*else{
                    let year = "";
                    let month = "";
                    let day = "";
                    let hours = "";
                    let minutes = "";
                    for(let i = 0; i < 16; i++){
                      if(i < 4)
                        year += this.nextTODO.dates[i];
                      if (i > 4 && i < 7)
                        month += this.nextTODO.dates[i];
                      if(i > 7 && i < 10)
                        day += this.nextTODO.dates[i];
                      if(i > 10 && i < 13)
                        hours += this.nextTODO.dates[i];
                      if(i > 13 && i < 16)
                        minutes += this.nextTODO.dates[i];
                    }
                    const next = new Date(parseInt(year,10), parseInt(month, 10) - 1,  parseInt(day, 10));
                    next.setHours(parseInt(hours,10), parseInt(minutes, 10));
                    /*const tday = new Date();
                     const addtday = this.millitday.getFullYear() + tday.getMonth() + tday.getDate() + tday.getHours() + tday.getMinutes();
                     const addnext = next.getFullYear() + next.getMonth() + next.getDate() + next.getHours() + next.getMinutes();
                     const adddat = dat.getFullYear() + dat.getMonth() + dat.getDate() + dat.getHours() + dat.getMinutes();


                  }*/
                  if(dat.getMonth() === today.getMonth() && dat.getFullYear() === today.getFullYear() && dat.getDate() === today.getDate()){

                    this.todaysTODO.push({
                      description: data["description"]["value"],
                      location: data["location"]["value"],
                      dates: data["startDate"]["date"]
                    });
                  }
                }
              }
            }

          })
    },

    showAddEventForm() {
      this.displayAddEvent = true
    },

    selectingTodaysRDV() {
      const tdays = [];
      const today = new Date();
      for(let k in this.todos){
        const date_event = this.todos[k].dates;
        let year = "";
        let month = "";
        let day = "";
        for(let i = 0; i < 10; i++){
          if(i < 4)
            year += date_event[i];
          if (i > 4 && i < 7)
            month += date_event[i]
          if(i > 7 && i < 10)
            day += date_event[i];
        }
        const dat = new Date(parseInt(year,10), parseInt(month, 10) - 1,  parseInt(day, 10));
        if(dat.getMonth() === today.getMonth() && dat.getFullYear() === today.getFullYear() && dat.getDate() === today.getDate()){
          this.tdays.push({
            description: this.todos[k].description,
            location: this.todos[k].location,
            dates: this.todos[k].dates
          })
        }
      }
      return tdays;
    }
  },
  created: function(){
    this.test();
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
