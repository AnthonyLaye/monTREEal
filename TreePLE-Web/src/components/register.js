import axios from 'axios'
var frontendUrl = 'http://ecse321-13.ece.mcgill.ca:8087'
var backendUrl = 'http://ecse321-13.ece.mcgill.ca:8080'

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }})

export default {
  name: 'treePLERegister',
  data () {
    return {
      username: '',
      useremail: '',
      userpsw: '',
      picked: 'Scientist',
      error: ''
    }
  },

  methods: {
    register: function (name, email, password, type) {
      AXIOS.post('/treePLE/register/' + name + '?email=' + email + '&password=' + password + '&role=' + type, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.
        this.error = response.error
        console.log(this.error)
        if (this.error) {
          alert('Error')
        } else {
          alert('Successfully registered!')
          this.$router.push('login')
        }
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
    }
  }
}
