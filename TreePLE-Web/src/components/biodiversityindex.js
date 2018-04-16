import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://ecse321-13.ece.mcgill.ca:8087'
var backendUrl = 'http://ecse321-13.ece.mcgill.ca:8080'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'BiodiversityIndex',
  data () {
    return {
      biodiversityindex: 0,
      latitude: 0,
      longitude: 0,
      radius: 0,
      trees: [],
      treesinArea: [],
      tree: Object,
      treeSpecies: [],
      addedTrees: [],
      errorLocation: '',
      center: {lat: 45.5017, lng: -73.5673},
    }
  },
  created: function() {
    AXIOS.get('/treePLE/trees')
    .then(response => {
      this.trees = response.data
    })
    .catch(e => {
      var errorMsg = e.response.data.message
      console.log(errorMsg)
    });
  },
  methods: {
    startBiodiversityIndex: function () {
      this.$router.push('bioindex')
    },
    exitForecasting: function () {
      this.$router.push('dashboard')
    },
    startCarbonSequestration: function () {
      this.$router.push('carbonsequestration')
    },
    startWaterIndex: function () {
      this.$router.push('waterindex')
    },
    getAllTrees: function() {
      AXIOS.get('/treePLE/trees')
      .then(response => {
        // JSON responses are automatically parsed.
        this.trees = response.data
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
      })
    },
    getTreesInArea: function (latitude, longitude, radius) {
      if (latitude > 85 || latitude < -85) {
        this.errorLocation = 'Latitude must be between -85 and 85! '
        return
      }
      if (longitude > 180 || longitude < -180) {
        this.errorLocation = 'Longitude must be between -180 and 180! '
        return
      }
      if (radius > 500 || radius <= 0) {
        this.errorLocation = 'Please keep the radius between 1 and 500 km!'
        return
      }
      AXIOS.get('/treePLE/trees/position' + '?latitude=' + latitude + '&longitude=' + longitude + '&distance=' + radius, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.treesinArea = response.data
        this.errorLocation = ''
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    },
    addToForecast: function (species){
      if (species.length === 0) {
        return
      }
        var temp = new Object();
                temp["species"] = species;
                temp["id"] = Math.floor((Math.random() * 99999999) + 10000000);
                temp["height"] = 0;
                temp["diameter"] = 0;
                temp["age"] = 0;
                temp["date"] = '2018-04-12';
                temp["latitude"] = 0;
                temp["longitude"] = 0;
                temp["status"] = "Healthy"
                temp["name"] = "Test"

        this.treesinArea.push(temp)
    },
    removeFromForecast: function (treeID){
        for(var i =0; i < Object.keys(this.treesinArea).length; i++){
          if(this.treesinArea[i].id == treeID){
            this.treesinArea.splice(i, 1)
            break
          }
        }
    },

    getLocation: function (tree) {
      return {
        lat: tree.latitude,
        lng: tree.longitude
      }
    },

    calculateIndex: function (){
      if (Object.keys(this.treesinArea).length == 0) {
        return{
        }
      }
      var treeSpecies = []
      for(var i = 0; i < Object.keys(this.treesinArea).length; i++){
        treeSpecies.push(this.treesinArea[i].species)
      }
      AXIOS.get('/treePLE/trees/forecast/biodiversityindex' + '?treesInArea=' + treeSpecies, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.biodiversityindex = response.data
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    }
  }
}
