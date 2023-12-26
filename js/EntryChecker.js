function checkValidEntry()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="No") window.location="LoginForm.jsp";
}
}
};
xmlHttpRequest.open('GET','entryChecker',false);
xmlHttpRequest.send();
}