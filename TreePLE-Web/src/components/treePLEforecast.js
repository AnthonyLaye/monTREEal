export default {
  name: 'treePLEforecast',
  data () {
    return {
    }
  },

  methods: {
    startBiodiversityIndex: function() {

      this.$router.push('bioindex');
    },
    startCarbonSequestration: function() {

      this.$router.push('carbonsequestration');
    },
    startCanopy: function() {

      this.$router.push('canopy');
    }
  }
}
