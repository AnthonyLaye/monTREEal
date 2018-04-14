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
    exitForecasting: function () {
      this.$router.push('trep')
    },
    startCarbonSequestration: function() {

      this.$router.push('carbonsequestration');
    },
    startWaterIndex: function() {

      this.$router.push('waterindex');
    }
  }
}
