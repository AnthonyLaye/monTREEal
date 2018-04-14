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
      <th> Water Index = {{waterindex}} </th>
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
          <input type="text" v-model="radius" placeholder = "Radius:">
        </td>
      </tr>
    </table>

    <table>
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

    <table>
      <tr>
        <th> Species </th>
      <tr v-for="tree in newForecastTrees" >
        <td> {{tree}} </td>
        </td>
      </tr>
    </table>

    <select v-model="removedTreesFromForecast">
        <option disabled value="">Please select a Tree to remove</option>
        <option v-for="tree in totalTrees">
          {{tree}} </option>
      </select>

    <button @click="removeFromForecast(removedTreesFromForecast)" style="margin:15px" >Remove Tree From Forecast
    </button>

    <br> </br>

    <button @click="calculateWaterIndex(latitude, longitude, radius)" style="margin:15px" > Calculate!
    </button>

    <br> </br>

    <button @click="exitForecasting" style="margin:15px" >Exit Forecasting
    </button>

  </div>
</template>

<script src="./waterindex.js">
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
