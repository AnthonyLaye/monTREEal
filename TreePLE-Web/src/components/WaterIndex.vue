<template>
  <div id="WaterIndex">
    <h1><b>monTREEal Forecasting </b></h1>

    <br> </br>

    <h2><b>Water Index </b></h2>

    <button @click="startBiodiversityIndex" style="margin:15px" >Biodiversity Index
    </button>

    <button @click="startCarbonSequestration" style="margin:15px" >Carbon Sequestration
    </button>

    <button @click="startWaterIndex" style="margin:15px" >Water Index
    </button>

    <br> </br>
    <table align="center">
      <th> Water Index = {{waterindex}} litres of water per month </th>
    </table>

    <table align="center">
      <tr>
        <th> Set Range for Calculation (latitude, longitude, radius): </th>
        <br> </br>
        <td>
          <input type="number" min="-85" max="85" v-model="latitude" placeholder = "Area Latitude:" >
        </td>
        <td>
          <input type="number" min="-180" max="180" v-model="longitude" placeholder = "Area Longitude:">
        </td>
        <td>
          <input type="number" min="0.1" max="200" v-model="radius" placeholder = "Radius(km):">
        </td>
        <td>
          <button @click="getTreesInArea(latitude, longitude, radius)" style="margin:15px" >Get Trees in Area
          </button>
        </td>
        <tr>
          <button @click="addToForecast(treeDiameter)" style="margin:15px" >Add Tree To Forecast
          </button>
          <input type="number" min="1" width="60" max="2000" v-model="treeDiameter" placeholder = "Tree Diameter (cm)">
          <b></b>
          <button @click="calculateWaterIndex()" style="margin:15px" > Calculate!
          </button>
        </tr>
      </tr>
    </table>

    <br> </br>

    <p>
      <span style="color:red">{{errorLocation}} </span>
    </p>

    <table align = "center" style = "width:60%">
      <tr>
        <th> ID </th>
        <th> Species </th>
        <th> Height </th>
        <th> Diameter </th>
        <th> Age </th>
        <th> Date Planted </th>
      <tr v-for="tree in treesinArea" >
        <td> {{tree.id}} </td>
        <td> {{tree.species}} </td>
        <td> {{tree.height}} </td>
        <td> {{tree.diameter}} </td>
        <td> {{tree.age}} </td>
        <td> {{tree.date}} </td>
        </td>
        <button @click="removeFromForecast(tree.id)" style="margin:15px" >Remove Tree from Forecast
        </button>
      </tr>
    </table>

    <br> </br>

    <br> </br>


    <p>
      <span style="color:red">{{errorAddTree}} </span>
    </p>

    <gmap-map v-bind:center="center" v-bind:zoom="7" style="width: 1000px; height: 500px; margin: auto">
      <gmap-marker v-for="tree in treesinArea" :key="tree.id" :position="getLocation(tree)">
      </gmap-marker>
    </gmap-map>

    <br> </br>

    <button @click="exitForecasting" style="margin:15px" >Exit Forecasting
    </button>
    <br> </br>

    <br> </br>
  </div>
</template>

<script src="./waterindex.js">
</script>

<style>
  #WaterIndex {
    background: #f4efeb;
    font-family: 'Avenir', sans-serif;
    color: #27472e;
  }
  h1 {
  	font-size: 36px;
  }
  th{
  	font-size: 18px;
  }
  td {
  	font-size: 14px;
  }
  button {
  	background-color:#27472e;
  	color: white;
  	border: 1px solid black;
  	font-size: 16px;
  	padding: 7px 15px;
  	border-radius: 4px;
  	transition-duration: 0.4s;
  }
  button:hover{
  	background-color: #d7e8db;
  	color: black;
  }
</style>
