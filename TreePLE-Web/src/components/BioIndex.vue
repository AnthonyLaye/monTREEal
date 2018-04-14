<template>
  <div id="BiodiversityIndex">
    <h1><b>monTREEal Forecasting </b></h1>

    <br> </br>

    <h2><b>Biodiversity Index </b></h2>

    <button @click="startBiodiversityIndex" style="margin:15px" >Biodiversity Index
    </button>

    <button @click="startCarbonSequestration" style="margin:15px" >Carbon Sequestration
    </button>

    <button @click="startWaterIndex" style="margin:15px" >Water Index
    </button>

    <br> </br>
    <table align="center">
      <th> Biodiversity Index = {{biodiversityindex}} </th>
    </table>

    <table align="center">
      <tr>
        <th> Set Range for Calculation: </th>
        <br> </br>
        <td>
          <input type="text" v-model="latitude" placeholder = "Area Latitude:" >
        </td>
        <td>
          <input type="text" v-model="longitude" placeholder = "Area Longitude:">
        </td>
        <td>
          <input type="text" v-model="radius" placeholder = "Radius(km):">
        </td>
        <td>
          <button @click="getTreesInArea(latitude, longitude, radius)" style="margin:15px" >Get Trees in Area
          </button>
        </td>
      </tr>
    </table>

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

    <table align="center">
      <tr>
        <td>
          <input type="text" v-model="treeSpecies" placeholder = "Tree Species">
        </td>
        <td>
          <button @click="addToForecast(treeSpecies)" style="margin:15px" >Add Tree To Forecast
          </button>
        </td>
      </tr>
    </table>
<!--
    <table>
      <tr>
      <tr v-for="tree in trees" >
        <td> {{tree}} </td>
        </td>
      </tr>
    </table>
-->

    <br> </br>

    <button @click="calculateIndex()" style="margin:15px" > Calculate!
    </button>

    <button @click="getAllTrees()" style="margin:15px" > Refresh Tree List
    </button>

    <br> </br>

    <button @click="exitForecasting" style="margin:15px" >Exit Forecasting
    </button>

  </div>
</template>

<script src="./biodiversityindex.js">
</script>

<style>
  #treePLE {
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
