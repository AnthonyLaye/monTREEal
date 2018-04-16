<!--
<template>
  <div id="Register">

    <h1> Register </h1>

    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Username" name="name" required>

    <br> </br>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>

    <br> </br>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <br> </br>

    <label for="usertype"><b>Type of User: </b></label>

    <input type="radio" name="usertype" value="Scientist"> Scientist
    <input type="radio" name="usertype" value="Resident" checked="checked"> Resident<br>

    <br> </br>

    <button @click="register(name, email, psw, usertype)"> Register </button>
    <br> </br>

  </div>
</template> -->


<template>
  <div id="Register">
    <div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal">
                <span class="heading">WelCome to Join monTREEal</span>

                <div class="form-group">
                    <input v-model="username" class="form-control"  placeholder="Name">
                </div>
                <div class="form-group">
                    <input v-model="useremail" class="form-control"  placeholder="Email">
                </div>
                <div class="form-group help">
                    <input v-model="userpsw" type="password" class="form-control"  placeholder="Password">
                </div>
                <div>
                  <input type="radio" name="picked" value="Scientist" v-model="picked">
                  <label>Scientist</label>
                  <br>
                  <input type="radio" name="picked" value="Resident" v-model="picked">
                  <label>Resident</label>
                  <br>
                  <span>You will be registered as a :<strong>{{ picked }}</strong></span>
                </div>
                <br>
                <div class="form-group">
                    <button class="btn btn-default" @click="register(username,useremail,userpsw,picked)">Register Now</button>
                </div>
            </form>
        </div>
    </div>
    <label> Alread a member with us? Sign in now!</label>
    <br></br>
  </div>
</div>
</template>




<script src="./register.js">
</script>

<style>
#Register{
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

.form-horizontal .main-checkbox input[type=checkbox]{
    visibility: hidden;
}
.form-horizontal .main-checkbox input[type=checkbox]:checked + label:after{
    opacity: 1;
}

</style>
