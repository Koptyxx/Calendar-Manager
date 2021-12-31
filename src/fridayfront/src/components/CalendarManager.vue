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
        <li><a class="dropdown-item"  v-on:click="resetCalendar">New calendar</a></li>
        <li>
          <div>
            <p>Load a calendar from file</p>
            <input type="text" v-model="filePath">
            <button class="small" type="button" v-on:click="setCalendarFromFile">Load</button>
          </div>
        </li>
        <li><a class="dropdown-item"  v-on:click="exportCalendar">Export my calendar</a></li>
      </ul>
    </li>
    <li class="nav-item">
      <router-link to="/login">
        <a class="nav-link">Log out</a>
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
        <h4 class="text-decoration-underline">Starting at</h4>
      </div>
    </div>
    <div class="row" v-if="this.nextTODO !== undefined">
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
      <button type="button" v-on:click="showEvent" class="btn btn-info mt-3">Modify Event</button>
    </div>
    <div class="col">
      <ul class="list-group">
        <a class="list-group-item list-group-item-action" v-for="todo in this.todos" :key="todo">{{todo.description}} : {{todo.dates}}</a>
      </ul>
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
      filePath: "",
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
    setCalendarFromFile(){
      let url = "http://localhost:8080/event/create/";
      fetch(url, {
        method:'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          file: this.filePath,
          username: this.username
        })
      })
      .then(res => console.log(res));
    },

    exportCalendar(){
        let url = "http://localhost:8080/event/write/";
            fetch(url , {
              method: "POST"
            })
    }
    ,
    resetCalendar(){
      let url = "http://localhost:8080/event/delete/";
      fetch(url, {
        method: "DELETE",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username: this.username
        })
      })
      .then(res => console.log(res));
      document.location.reload(true);
    }
    ,
    pushInTodayTODOS(){
      const tday = new Date();
      if(tday.getFullYear() === this.date.getFullYear() && tday.getMonth() === this.date.getMonth() && tday.getDate() === this.date.getDate()){
        this.todaysTODO.push(
            {
              description: this.description,
              dates: this.date,
              location: this.location
            }
        );
      }
    }
    ,
    dataDateAtRightFormat(){
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
      return data_date;
    },

    pushInTodos(){
      this.todos.push(
          {
            description: this.description,
            dates: this.date,
            location: this.location
          }
      );
    }
    ,
    addEvent() {
      this.pushInTodos();
      this.displayAddEvent = false
      this.pushInTodayTODOS();
      const data_date = this.dataDateAtRightFormat();
      this.dataDateAtRightFormat(data_date);
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
    pushTodoFromQuery(data){
      this.todos.push({
        description: data["description"]["value"],
        dates: data["startDate"]["date"],
        location: data["location"]["value"],
      });
    }
    ,
    fromJSONtoStringDate(date_event){
      let year = ""; let month = ""; let day = ""; let hours = ""; let minutes = "";
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
      return {year, month, day, hours, minutes};
    },
    pushNextTodo(data){
      this.nextTODO = {
        description: data["description"]["value"],
        dates: data["startDate"]["date"],
        location: data["location"]["value"],
      }
    },
    pushTodaysTodoFromQuery(data){
      this.todaysTODO.push({
        description: data["description"]["value"],
        location: data["location"]["value"],
        dates: data["startDate"]["date"]
      });
    }
    ,
    starter (){
      let url = "http://localhost:8080/event/find/username/" + this.username;
      fetch(url)
          .then(res => res.json())
          .then(res => {
            for(let i in res){
              const datas = res[i].calendar;
              for(let k in datas["components"]){
                const data = datas["components"][k]
                if(data["name"] === "VEVENT"){
                  this.pushTodoFromQuery(data);
                  const today = new Date();
                  const date_event = data["startDate"]["date"];
                  const dateFormat = this.fromJSONtoStringDate(date_event);
                  const dat = new Date(parseInt(dateFormat.year,10), parseInt(dateFormat.month, 10) - 1,  parseInt(dateFormat.day, 10));
                  dat.setHours(parseInt(dateFormat.hours,10), parseInt(dateFormat.minutes, 10));
                  if(this.nextTODO === undefined){
                    this.pushNextTodo(data);
                  }
                  /*else{
                    let year = "";
                    let month = "";
                    let day = "";
                    /*let hours = "";
                    let minutes = "";
                    for(let i = 0; i < 16; i++){
                      if(i < 4)
                        year += this.nextTODO.dates[i];
                      if (i > 4 && i < 7)
                        month += this.nextTODO.dates[i];
                      if(i > 7 && i < 10)
                        day += this.nextTODO.dates[i];
                     /* if(i > 10 && i < 13)
                        hours += this.nextTODO.dates[i];
                      if(i > 13 && i < 16)
                        minutes += this.nextTODO.dates[i];
                    }
                    const actual = year+"-"+month+"-"+day;
                    const tday = today.getFullYear().toString()+"-"+(today.getMonth()+1).toString()+"-"+today.getDate().toString();
                    const challenger = dat.getFullYear().toString()+"-"+(dat.getMonth()+1).toString()+"-"+dat.getDate().toString()
                    const diffinMSactual = new Date(tday) - new Date(actual);
                    const diffinMSchallenger = new Date(tday) - new Date(challenger);
                    console.log(actual, tday, challenger, diffinMSactual, diffinMSchallenger)
                    if(diffinMSactual < 0){
                      if(diffinMSchallenger >= 0) {
                        this.nextTODO = {
                          description: data["description"]["value"],
                          dates: data["startDate"]["date"],
                          location: data["location"]["value"],
                        }
                      }
                      else {
                        this.nextTODO = null;
                      }
                    }
                    /*if(diffinMSchallenger < diffinMSactual && diffinMSchallenger >= 0){
                      this.nextTODO = {
                        description: data["description"]["value"],
                        dates: data["startDate"]["date"],
                        location: data["location"]["value"],
                      }
                    }
                  }*/
                  if(dat.getMonth() === today.getMonth() && dat.getFullYear() === today.getFullYear() && dat.getDate() === today.getDate()){
                    this.pushTodaysTodoFromQuery(data);
                  }
                }
              }
            }
          });
    },

    showAddEventForm() {
      this.displayAddEvent = true
    },

    selectingTodaysRDV() {
      const tdays = [];
      const today = new Date();
      for(let k in this.todos){
        const date_event = this.todos[k].dates;
        const dateFormat = this.fromJSONtoStringDate(date_event);
        const dat = new Date(parseInt(dateFormat.year,10), parseInt(dateFormat.month, 10) - 1,  parseInt(dateFormat.day, 10));
        dat.setHours(parseInt(dateFormat.hours,10), parseInt(dateFormat.minutes, 10));
        if(dat.getMonth() === today.getMonth() && dat.getFullYear() === today.getFullYear() && dat.getDate() === today.getDate()){
          this.tdays.push({description: this.todos[k].description, location: this.todos[k].location, dates: this.todos[k].dates});
        }
      }
      return tdays;
    }
  },
  created: function(){
    this.starter();
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
