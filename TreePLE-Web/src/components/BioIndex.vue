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
          <select selected="blackwalnut" name="treeSpecies" v-model="treeSpecies">
            <option value="blackwalnut" selected> Black Walnut</option>
            <option value="butternut">Butternut</option>
            <option value="redmaple">Red Maple</option>
            <option value="westernredcedar"> Western Cedar</option>
            <option value="whitecedar"> White Cedar </option>
            <option value="easternredcedar"> Eastern Red Cedar </option>
            <option value="douglasfir"> Douglas Fir </option>
            <option value="fir"> Fir </option>
            <option value="amabilisfir"> Amabilis Fir </option>
            <option value="balsamfir"> Balsam Fir</option>
            <option value="subalpinefir"> Subalpine Fir</option>
            <option value="grandfir"> Grand Fir</option>
            <option value="easternhemlock"> Eastern Hemlock</option>
            <option value="westernhemlock"> Western Hemlock</option>
            <option value="hemlock"> Hemlock </option>
            <option value="tamarack"> Tamarack </option>
            <option value="larch"> Larch </option>
            <option value="oak"> Oak </option>
            <option value="westernlarch"> Western Larch </option>
            <option value="whitepine"> White Pine </option>
            <option value="jackpine"> Jack Pine</option>
            <option value="lodgepolepine"> Lodgepole Pine </option>
            <option value="redpine"> Red Pine </option>
            <option value="pine"> Pine </option>
            <option value="maple"> Maple </option>
            <option value="blackspruce"> Black Spruce </option>
            <option value="redspruce"> Red Spruce </option>
            <option value="sitkaspruce"> Sitka Spruce </option>
            <option value="whitespruce"> White Spruce </option>
            <option value="spruce"> Spruce </option>
            <option value="redalder"> Red Alder </option>
            <option value="blackash"> Black Ash </option>
            <option value="whiteash"> White Ash </option>
            <option value="redash"> Red Ash </option>
            <option value="greenash"> Green Ash </option>
            <option value="ash"> Ash </option>
            <option value="basswood"> Basswood </option>
            <option value="beech"> Beech </option>
            <option value="whitebirch"> White Birch </option>
            <option value="yellowbirch"> Yellow Birch </option>
            <option value="birch"> Birch </option>
            <option value="blackcherry"> Black Cherry </option>
            <option value="whiteelm"> White Elm </option>
            <option value="elm"> Elm </option>
            <option value="hickory"> Hickory </option>
            <option value="silvermaple"> Silver Maple </option>
            <option value="sugarmaple"> Sugar Maple </option>
          </select>
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
