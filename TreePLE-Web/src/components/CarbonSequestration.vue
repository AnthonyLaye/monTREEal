<template>
  <div id="CarbonSequestration">
    <h1><b>monTREEal Forecasting </b></h1>

    <br> </br>

    <h2><b>Carbon Sequestration </b></h2>

    <button @click="startBiodiversityIndex" style="margin:15px" >Biodiversity Index
    </button>

    <button @click="startCarbonSequestration" style="margin:15px" >Carbon Sequestration
    </button>

    <button @click="startWaterIndex" style="margin:15px" >Water Index
    </button>

    <br> </br>
    <table align="center">
      <th> Carbon Sequestration = {{carbonsequestration}} kg CO2</th>
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

    <table align="center">
      <tr>
        <td>
          <select name="treeSpecies" v-model="treeSpecies" placeholder = "Tree Species">
            <option value="Black Walnut">Black Walnut</option>
            <option value="Butternut">Butternut</option>
            <option value="Red Maple">Red Maple</option>
            <option value="Western Cedar"> Western Cedar</option>
            <option value="white Cedar"> White Cedar </option>
            <option value="Eastern Red Cedar"> Eastern Red Cedar </option>
            <option value="Douglas Fir"> Douglas Fir </option>
            <option value="Fir"> Fir </option>
            <option value="Amabilis Fir"> Amabilis Fir </option>
            <option value="Balsam Fir"> Balsam Fir</option>
            <option value="Subalpine Fir"> Subalpine Fir</option>
            <option value="Grand Fir"> Grand Fir</option>
            <option value="Eastern Hemlock"> Eastern Hemlock</option>
            <option value="Western Hemlock"> Western Hemlock</option>
            <option value="Hemlock"> Hemlock </option>
            <option value="Tamarack"> Tamarack </option>
            <option value="Larch"> Larch </option>
            <option value="Western Larch"> Western Larch </option>
            <option value="White Pine"> White Pine </option>
            <option value="Jack Pine"> Jack Pine</option>
            <option value="Lodgepole Pine"> Lodgepole Pine </option>
            <option value="Red Pine"> Red Pine </option>
            <option value="Pine"> Pine </option>
            <option value="Maple"> Maple </option>
            <option value="Black Spruce"> Black Spruce </option>
            <option value="Red Spruce"> Red Spruce </option>
            <option value="Sitka Spruce"> Sitka Spruce </option>
            <option value="White Spruce"> White Spruce </option>
            <option value="Spruce"> Spruce</option>
            <option value="Red Alder"> Red Alder </option>
            <option value="Black Ash"> Black Ash </option>
            <option value="White Ash"> White Ash </option>
            <option value="Red Ash"> Red Ash </option>
            <option value="Green Ash"> Green Ash </option>
            <option value="Ash"> Ash </option>
            <option value="Oak"> Oak </option>
            <option value="Basswood"> Basswood </option>
            <option value="Beech"> Beech </option>
            <option value="White Birch"> White Birch </option>
            <option value="Yellow Birch"> Yellow Birch </option>
            <option value="Birch"> Birch </option>
            <option value="Black Cherry"> Black Cherry </option>
            <option value="White Elm"> White Elm </option>
            <option value="Elm"> Elm </option>
            <option value="Hickory"> Hickory </option>
            <option value="Silver Maple"> Silver Maple </option>
            <option value="Sugar Maple"> Sugar Maple </option>
          </select>
          <input type="number" width="60" min="1" max="20000" v-model="treeHeight" placeholder = "Tree Height (cm)">
          <input type="number" width="60" min="1" max="2000" v-model="treeDiameter" placeholder = "Tree Diameter (cm)">
        </td>
        <td>
          <button @click="addToForecast(treeSpecies, treeHeight, treeDiameter)" style="margin:15px" >Add Tree To Forecast
          </button>
        </td>
      </tr>
    </table>

    <br> </br>

    <p>
      <span style="color:red">{{errorAddTree}} </span>
    </p>

    <button @click="calculateSequestration()" style="margin:15px" > Calculate!
    </button>

    <br> </br>

    <button @click="exitForecasting" style="margin:15px" >Exit Forecasting
    </button>

  </div>
</template>

<script src="./carbonsequestration.js">
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
