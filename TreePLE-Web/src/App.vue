<template>
  <div id="app">
    <ul>
      <li><a class="active" <router-link to="login">Sign In</router-link></a></li>
      <li><a <router-link to="register">Register</router-link></a></li>
      <li style="float:right"><a <router-link to="aboutus">About Us</router-link></a></li>
    </ul>
    <router-view></router-view>
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
}


  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
  }

  li {
    float: left;
    border-right:0.5px solid #bbb;
  }

  li:last-child {
    border-right: none;
  }

  li a {
    display: block;
    color: white;
    text-align: center;
    padding: 8px 20px;
    text-decoration: none;
  }

  li a:hover:not(.active) {
    background-color: #009933
;
  }

  .active {
    background-color: #ff3300;
  }
</style>
