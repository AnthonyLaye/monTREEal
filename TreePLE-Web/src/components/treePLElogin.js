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
      useremail:'',
      userpsw:'',
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
          alert("You have successfully logged in as a Resident!")
          this.$router.push('residentdashboard')
        }
        else if(this.status === 'Scientist'){
          alert("You have successfully logged in as a Scientist!")
          this.$router.push('dashboard')
        }
        else{
          alert("Failed to Login! Check your login information!")
        }
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    },
  }
}
