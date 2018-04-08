<template>
  <div id="Login">

    <h1> Welcome to monTREEal! </h1>

    <br> </br>

    <table border= "1" align= "center">
      <br> </br>

      <font font-size="50" ><b>Login to monTREEal</b></font>

      <br> </br>

      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Enter Username" name="uname" required>

      <br> </br>

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" required>

      <br> </br>

      <button @click="login()">Login</button>
      <br> </br>

    </table>

    <br> </br>
    <br> </br>

    <label> Not a member? Register now!</label>

    <br> </br>

    <button @click="register()"> Register </button>

  </div>
</template>
<script src="./treePLElogin.js">
</script>

<style>
  #treePLE{
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
