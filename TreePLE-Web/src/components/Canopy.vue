<template>
  <div id="Canopy">
    <h1><b>monTREEal Forecasting </b></h1>

    <br> </br>

    <h2><b>Canopy </b></h2>

    <button @click="startBiodiversityIndex" style="margin:15px" >Biodiversity Index
    </button>

    <button @click="startCarbonSequestration" style="margin:15px" >Carbon Sequestration
    </button>

    <button @click="startCanopy" style="margin:15px" >Canopy
    </button>

    <br> </br>

    <span> </span>

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

    <button @click="startForecast()" style="margin:15px" >Calculate!
    </button>

  </div>
</template>

<script src="./canopy.js">
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
