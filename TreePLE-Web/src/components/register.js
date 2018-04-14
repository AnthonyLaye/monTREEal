export default {
  name: 'treePLERegister',
  data () {
    return {
    }
  },

  methods: {
    register: function(name, email, password, type) {
      AXIOS.post('/treePLE/register/' + name  + '?email=' + email + '&password=' + password + '&role=' + type, {}, {})
      .then(response => {
        // JSON responses are automatically parsed.

        this.$router.push('trep');
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
      })
      this.$router.push('login');
    }
  }
}
