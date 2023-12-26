<!DOCTYPE HTML>
<head lang='en'>
<meta charset='utf-8'>
<title>HR Application</title>
<link rel='stylesheet' type='text/css' href='/stylethree/css/loginStyles.css'>
<script src='/stylethree/js/LoginForm.js'></script>
<script>
function doLogin()
{
var errorSpan=document.getElementById('error');
errorSpan.innerHTML='';
var tmp=validateForm(document.getElementById('detailsForm'));
if(tmp==false) return;
var username=document.getElementById('username').value;
var password=document.getElementById('password').value;
var administrator={
"username":username,
"password":password
};
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="Invalid username/password") errorSpan.innerHTML=response;
else
{
window.location="index.jsp";
}
}
else
{
alert('Some problem has occured');
}
}
};
xmlHttpRequest.open('POST','login',true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send(JSON.stringify(administrator));
}
</script>
</head>
<body>
<!--main container div starts-->
<div class='main-container'>

<!--header div starts-->
<div class='header'>
<img src='/stylethree/images/logo1.png' class='logo'><div class='brand-name'>Thinking Machines</div>
</div>   <!--header div ends-->

<!--down div starts-->
<div class='down-container'>
<center>
<h2><b>Administrator</b></h2>
<form id='detailsForm' action='/stylethree/index.jsp'>
<table>
<tr><span class='error' id='error'></span><br><tr>
<tr><td>Username:&nbsp;<input type='text' id='username' name='username' size=20 maxlength=15></td>
<td><span id='usernameErrorSection' class='error'></span><br><br></td></tr>
<tr><td>Password:&nbsp;<input type='password' id='password' name='password' size=20 maxlength=15></td>
<td><span id='passwordErrorSection' class='error'></span><br><br></td></tr>
</table><br>
<button type='button' onclick='doLogin()'>Login</button>
</form>
</center>
</div>
<!--down div ends-->
</div>
</body>
</html>