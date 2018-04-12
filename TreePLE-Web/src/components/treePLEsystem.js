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

function TreeDto(species, height, age, date, diameter, id) {
  this.species = species;
  this.height = height;
  this.age = age;
  this.date = date;
  this.diameter = diameter;
  this.id = id;
}
export default {
  name: 'treePLEsystem',
  data() {
    return {
      trees: [],
      errorTrees: ''
    }
  },
  created: function() {
    AXIOS.get('/treePLE/trees')
    .then(response => {
      this.trees = response.data
    })
    .catch(e => {
      this.errorEvent = e;
    });
  },

  methods: {
    listAllTrees: function() {
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
    startForecastPage: function(){

      this.$router.push('forecast')
    }
  }
}
