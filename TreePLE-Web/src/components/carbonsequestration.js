import axios from 'axios'
var frontendUrl = 'http://ecse321-13.ece.mcgill.ca:8087'
var backendUrl = 'http://ecse321-13.ece.mcgill.ca:8080'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'CarbonSequestration',
  data () {
    return {
      carbonsequestration: 0,
      latitude: 0,
      longitude: 0,
      radius: 0,
      treesinArea: []
    }
  },

  methods: {
    startBiodiversityIndex: function () {
      this.$router.push('bioindex')
    },
    exitForecasting: function () {
      this.$router.push('trep')
    },
    startCarbonSequestration: function () {
      this.$router.push('carbonsequestration')
    },
    startWaterIndex: function () {
      this.$router.push('waterindex')
    },
    getTreesInArea: function (latitude, longitude, radius) {
      AXIOS.get('/treePLE/trees/position' + '?latitude=' + latitude + '&longitude=' + longitude + '&distance=' + radius, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.treesinArea = response.data
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    },
    addToForecast: function (species, height, diameter) {
      var temp = {
        'species': species,
        'id': Math.floor((Math.random() * 99999999) + 10000000),
        'height': height,
        'diameter': diameter,
        'age': 0,
        'date': '2018-04-12',
        'latitude': 0,
        'longitude': 0,
        'status': 'Healthy',
        'name': 'Test'
      }
      this.treesinArea.push(temp)
    },
    removeFromForecast: function (treeID) {
      for (var i = 0; i < Object.keys(this.treesinArea).length; i++) {
        if (this.treesinArea[i].id === treeID) {
          this.treesinArea.splice(i, 1)
          break
        }
      }
    },
    calculateSequestration: function (latitude, longitude, radius) {
      AXIOS.get('/treePLE/trees/forecast/carbonsequestration' + '?latitude=' + latitude + '&longitude=' + longitude + '&distance=' + radius, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.carbonsequestration = response.data
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    }
  }
}
