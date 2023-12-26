function validateForm(form)
{
var username=form.username.value.trim();
var focusFlag;
var usernameErrorSection=document.getElementById('usernameErrorSection');
usernameErrorSection.innerHTML='';
var result=true;
if(username.length==0)
{
if(result==true) result=false;
if(focusFlag==null) focusFlag=form.username;
usernameErrorSection.innerHTML='required';
}
var password=form.password.value.trim();
var passwordErrorSection=document.getElementById('passwordErrorSection');
passwordErrorSection.innerHTML='';
if(password.length==0)
{
if(result==true) result=false;
if(focusFlag==null) focusFlag=form.password;
passwordErrorSection.innerHTML='required';
}
if(focusFlag!=null) focusFlag.focus();
return result;
}