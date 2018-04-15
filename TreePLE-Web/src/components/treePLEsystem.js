//1. Create constructor methods (Dtos)
//2. Add data variables to the export declaration of the component
//3. Add an initialization function below the data part
//4. Add methods

import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://ecse321-13.ece.mcgill.ca:8087'
var backendUrl = 'http://ecse321-13.ece.mcgill.ca:8080'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TreeDto(species, height, age, date, diameter, id, status, location, latitude, longitude) {
  this.species = species;
  this.height = height;
  this.age = age;
  this.date = date;
  this.diameter = diameter;
  this.id = id;
  this.status = status;
  this.latitude = latitude;
  this.longitude = longitude;
}

export default {
  name: 'treePLEsystem',
  data() {
    return {
      center: {lat: 45.5017, lng: -73.5673},
      trees: [],
      errorTrees: '',
      location: [],
      surveyError: ''
    }
  },

  created: function () {
    AXIOS.get('/treePLE/trees')
    .then(response => {
      this.trees = response.data
      this.errorTrees = ''
    })
    .catch(e => {
      this.errorEvent = e
    });
  },

  methods: {
    listAllTrees: function () {
      AXIOS.get('/treePLE/trees')
      .then(response => {
        // JSON responses are automatically parsed.
        this.trees = response.data
        this.errorTrees = ''
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorTrees = errorMsg
      })

    },

    getLocation: function (tree) {
      return {
        lat: tree.latitude,
        lng: tree.longitude
      }
    },

    survey: function (id, name, height, diameter, status) {
      if (id === undefined) {
        this.surveyError = 'Please select an ID'
        return
      }
      if (name === undefined || name === ''){
        this.surveyError = 'Please enter your name'
        return
      }
      if (!((height > 0) && (height < 20000)) === true) {
        this.surveyError = "Please enter a height greater than 0 and less than 20 000 cm"
        return
      }
      if (!((diameter > 0) && (diameter < 3500)) === true) {
        this.surveyError = "Please enter a diameter greater than 0 and less than 3 500 cm"
        return
      }
      if (status === undefined) {
        this.surveyError = 'Please select a tree status'
        return
      }
      var date = new Date().toISOString().slice(0,10);  //Get todays date, taken from online on stack overflow

      AXIOS.post('/treePLE/survey/tree/' + id + '?date=' + date + '&personName=' + name + '&height=' + height + '&diameter=' + diameter + '&status=' + status, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.listAllTrees()
        this.surveyError = ''
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorTrees = errorMsg
      })
    },

    startForecastPage: function () {
      this.$router.push('forecast')
    }
  }
}
