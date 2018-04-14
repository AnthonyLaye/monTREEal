<template>
  <div id="Home">
    <h1><b>Welcome to monTREEal Home Page! </b></h1>
    <button @click="" style="margin:15px" >About Us
    </button>

    <button @click="startShowTrees()" style="margin:15px"> Inspect Trees
    </button>

    <button @click="startForecasting()" style="margin:15px" >Forecasting
    </button>

    <p>
      <!-- <span style="color:red">Error: Message text comes here</span> -->
    </p>
  </div>
</template>
<script src="./Home.js">
</script>

<style>
  #home {
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