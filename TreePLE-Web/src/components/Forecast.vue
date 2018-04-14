<template>
  <div id="treePLEForecast">
    <h1><b>monTREEal Forecasting </b></h1>

    <br> </br>

    <h2><b>Select Sustainability Attribute: </b></h2>

    <button @click="startBiodiversityIndex" style="margin:15px" >Biodiversity Index
    </button>

    <button @click="startCarbonSequestration" style="margin:15px" >Carbon Sequestration
    </button>

    <button @click="startWaterIndex" style="margin:15px" >WaterIndex
    </button>

    <br> </br>

    <button @click="exitForecasting" style="margin:15px" >Exit Forecasting
    </button>

  </div>
</template>

<script src="./treePLEforecast.js">
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
