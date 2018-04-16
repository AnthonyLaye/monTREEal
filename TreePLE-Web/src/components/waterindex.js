import axios from 'axios'
var frontendUrl = 'http://ecse321-13.ece.mcgill.ca:8087'
var backendUrl = 'http://ecse321-13.ece.mcgill.ca:8080'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'WaterIndex',
  data () {
    return {
      waterindex: 0,
      treeDiameter: '',
      latitude: 0,
      longitude: 0,
      radius: 0,
      treesinArea: [],
      errorLocation: '',
      errorAddTree: '',
      center: {lat: 45.5017, lng: -73.5673}
    }
  },

  methods: {
    startBiodiversityIndex: function () {
      this.$router.push('bioindex')
    },
    startCarbonSequestration: function () {
      this.$router.push('carbonsequestration')
    },
    exitForecasting: function () {
      this.$router.push('dashboard')
    },
    startWaterIndex: function () {
      this.$router.push('waterindex')
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
    addToForecast: function (diameter) {
      if (!((diameter > 0) && (diameter < 3500)) === true) {
        this.errorAddTree = 'Diameter must be greater than 0 and less than 3500 cm'
        return
      }
      var temp = {
        'species': 'FORECAST TREE',
        'id': Math.floor((Math.random() * 99999999) + 10000000),
        'height': 0,
        'diameter': diameter,
        'age': 0,
        'date': '2018-04-12',
        'latitude': 0,
        'longitude': 0,
        'status': 'Healthy',
        'name': 'Test'
      }

      this.treesinArea.push(temp)
      this.errorAddTree = ''
    },
    removeFromForecast: function (treeID) {
      for (var i = 0; i < Object.keys(this.treesinArea).length; i++) {
        if (this.treesinArea[i].id === treeID) {
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
    calculateWaterIndex: function () {
      var treeDiameter = []
      for (var i = 0; i < Object.keys(this.treesinArea).length; i++) {
        treeDiameter.push(this.treesinArea[i].diameter)
      }
      AXIOS.get('/treePLE/trees/forecast/water' + '?treeDiameter=' + treeDiameter, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.waterindex = response.data
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    }
  }
}
