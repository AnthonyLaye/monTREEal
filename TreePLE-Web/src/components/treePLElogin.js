// 1. Create constructor methods (Dtos)
// 2. Add data variables to the export declaration of the component
// 3. Add an initialization function below the data part
// 4. Add methods

export default {
  name: 'treePLElogin',
  data () {
    return {
    }
  },

  methods: {
    register: function() {

      this.$router.push('register');
    },
    login: function(email, password) {
      /*AXIOS.get('/treePLE/trees/login' + '?email=' + email + '&password=' + password, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.

        this.$router.push('trep');
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })*/
      this.$router.push('trep');
    },
  }
}
