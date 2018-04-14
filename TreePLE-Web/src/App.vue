<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view></router-view>
 <!-- <gmap-map v-bind:center="center" v-bind:zoom="7" style="width: 100%; height: 500px">
      </gmap-map> -->
  </div>
</template>

<script>
  export default {
    data () {
      return {
        center: {lat: 45.5017, lng: -73.5673}
      }
    }
  }
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
