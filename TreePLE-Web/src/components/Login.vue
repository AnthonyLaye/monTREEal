<!--
<template>
  <div id="Login">
    <div id="login-box">
        <div id="ul-login">
            <div id="item-login">
              <input v-model="email"  class="input-login" type="text" placeholder="Email" />
            </div>
            <div id="item-login">
              <input v-model="psd"  class="input-login" type="password" placeholder="Password" /></div>
            </div>
        <div id="btn-box">
            <button id="btn btn-login" @click="login(email,psw)">Login</button>
        </div>
    </div>
</div>
</template>

-------------------------------------------
    <table border= "0" align= "center">
      <br> </br>

      <font font-size="50" ><b>Login to monTREEal</b></font>

      <br> </br>

      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Enter Username" name="uname" required>

      <br> </br>

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" required>

      <br> </br>

      <button @click="login(email,psw)">Login</button>
      <br> </br>

    </table>

    <br> </br>
    <br> </br>

    <label> Not a member? Register now!</label>

    <br> </br>

    <button @click="register()"> Register </button>

  </div> 
</template> -->

<!--
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
  	padding: 3px 15px;
  	border-radius: 4px;
  	transition-duration: 0.4s;
  }
  button:hover{
  	background-color: #d7e8db;
  	color: black;
  }

<style>
#Login{
    height: 100%;
    background: url("https://www.salem-one.com/wp-content/uploads/2017/01/Pine-Trees.jpg");
    background-size: cover;
    background-position: center;
}


  #login-box {
    box-sizing: border-box;
    width: 320px;
    border-radius: 4px;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    overflow: hidden;
    height: 260px;
    position: absolute;
    margin-left: -160px;
    margin-top: -160px;
    left: 50%;
    top: 50%;
  }

  #item-login {
    margin: 10px;
    text-align: center; font-size: 18px;
  }

  #input-login {
    border: none;
    background-color: rgba(255, 255, 255, 0.4);
    width: 280px;
    padding: 0 10px;
    font-size: 14px;
    height: 40px;
    border-radius: 4px;
    box-sizing: border-box;
  }
  #button-box{
    width: 100%; text-align: center;
  }

  #btn btn-login {
    width: 280px;
    background-color: #7da6e2;
    color: #fff;
    height: 0.4rem;
    margin: auto;
    border: none;
  }



</style>
-->
<template>
<div id="Login">
  <br></br>
  <div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal">
                <span class="heading">WelCome to monTREEal</span>
                <div class="form-group">
                    <input class="form-control" id="inputEmail3" name="email" placeholder="Email">
                </div>
                <div class="form-group help">
                    <input class="form-control" id="inputPassword3" name="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <button class="btn btn-default" @click="login(email,psw)">Login</button>
                </div>
                <br></br>
            </form>
        </div>
    </div>
    <label> Not a member? Register now!</label>
    <br></br>
</div>
</div>
</template>

<script src="./treePLElogin.js">
</script>

<style>
#Login{
    position: fixed;
    left: 0;
    right: 0;
    z-index: 1;
    display: block;
    width: 100%;

    background-image: url("https://www.salem-one.com/wp-content/uploads/2017/01/Pine-Trees.jpg");
    background-size: cover;
    background-position: center;
}

.form-horizontal{
    position: fixed;
    background: #fff;
    padding-bottom: 40px;
    border-radius: 30px;
    text-align: center;
    position: relative;
    left: 50%;
    opacity: 0.9;

}
.form-horizontal .heading{
    display: block;
    font-size: 35px;
    font-weight: 700;
    padding: 35px 0;
    border-bottom: 1px solid #f0f0f0;
    margin-bottom: 30px;
}
.form-horizontal .form-group{
    padding: 0 40px;
    margin: 0 0 25px 0;
    position: relative;
}
.form-horizontal .form-control{
    background: #f0f0f0;
    border: none;
    border-radius: 20px;
    box-shadow: none;
    padding: 0 25px 0 45px;
    height: 40px;
    transition: all 0.3s ease 0s;
}
.form-horizontal .form-control:focus{
    background: #e0e0e0;
    box-shadow: none;
    outline: 0 none;
}
.form-horizontal .form-group i{
    position: absolute;
    top: 12px;
    left: 60px;
    font-size: 17px;
    color: #c8c8c8;
    transition : all 0.5s ease 0s;
}

.form-horizontal .main-checkbox{
    float: left;
    width: 20px;
    height: 20px;
    background: #11a3fc;
    border-radius: 50%;
    position: relative;
    margin: 5px 0 0 5px;
    border: 1px solid #11a3fc;
}
.form-horizontal .main-checkbox label{
    width: 20px;
    height: 20px;
    position: absolute;
    top: 0;
    left: 0;
    cursor: pointer;
}
.form-horizontal .main-checkbox label:after{
    content: "";
    width: 10px;
    height: 5px;
    position: absolute;
    top: 5px;
    left: 4px;
    border: 3px solid #fff;
    border-top: none;
    border-right: none;
    background: transparent;
    opacity: 0;
    -webkit-transform: rotate(-45deg);
    transform: rotate(-45deg);
}

.form-horizontal .text{
    float: left;
    margin-left: 7px;vb 
    line-height: 20px;
    padding-top: 5px;
    text-transform: capitalize;
}
.form-horizontal .btn{
    float: right;
    font-size: 14px;
    color: #fff;
    background: #00b4ef;
    border-radius: 30px;
    padding: 10px 25px;
    border: none;
    text-transform: capitalize;
    transition: all 0.5s ease 0s;
}


</style>
