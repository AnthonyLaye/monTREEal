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
      radius: 0
    }
  },

  methods: {
    startBiodiversityIndex: function () {
      this.$router.push('bioindex')
    },
    startCarbonSequestration: function () {
      this.$router.push('carbonsequestration')
    },
    startWaterIndex: function () {
      this.$router.push('waterindex')
    },
    calculateIndex: function(latitude, longitude, radius){

      AXIOS.get('/treePLE/trees/forecast/biodiversity' + '?latitude=' + latitude + '&longitude=' + longitude + '&distance=' + radius, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.biodiversityindex = response.data
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      });
    },
    addToForecast: function(){

    }
  }
}
