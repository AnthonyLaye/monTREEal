<template>
  <div id="TreePLE">
    <h1><b>Welcome to monTREEal! </b></h1>
    <button @click="listAllTrees()" style="margin:15px" >List All Trees
    </button>
    <table align = "center" style = "width:60%">
      <tr>
      	<th> ID </th>
      	<th> Species </th>
      	<th> Height </th>
      	<th> Diameter </th>
      	<th> Age </th>
      	<th> Date Planted </th>
      <tr v-for="tree in trees" >
        <td> {{tree.id}} </td>
        <td> {{tree.species}} </td>
        <td> {{tree.height}} </td>
        <td> {{tree.diameter}} </td>
        <td> {{tree.age}} </td>
        <td> {{tree.date}} </td>
        </td>
      </tr>
    </table>
    <p>
      <!-- <span style="color:red">Error: Message text comes here</span> -->
    </p>
  </div>
</template>
<script src="./treePLEsystem.js">
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
