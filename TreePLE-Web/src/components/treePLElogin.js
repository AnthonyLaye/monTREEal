import axios from 'axios'
var frontendUrl = 'http://ecse321-13.ece.mcgill.ca:8087'
var backendUrl = 'http://ecse321-13.ece.mcgill.ca:8080'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'treePLElogin',
  data () {
    return {
      status: 'Null'
    }
  },

  methods: {
    register: function() {

      this.$router.push('register');
    },
    login: function(email, password) {
      AXIOS.get('/treePLE/login' + '?email=' + email + '&password=' + password, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.status = response.data
        if(this.status === 'Resident'){
          this.$router.push('residentdashboard')
        }
        else if(this.status === 'Scientist'){
          this.$router.push('dashboard')
        }
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    //  this.$router.push('trep');
    },
  }
}
