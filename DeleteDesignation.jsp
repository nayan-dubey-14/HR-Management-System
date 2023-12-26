<jsp:include page='MasterPageTopSection.jsp'/>
<script src='/stylethree/js/EntryChecker.js'></script>
<script src='/stylethree/js/DesignationDeleteForm.js'></script>
<script src='/stylethree/js/DesignationModule.js'></script>
<script>
var code;
function deleteDesignation()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="Designation Deleted Successfully")
{
alert('Deleted Successfully');
}
window.location="Designations.jsp";
}
else
{
alert('Some problem has occured');
}
}
};
var dataToSend="code="+encodeURI(code);
xmlHttpRequest.open('POST','deleteDesignation',true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(dataToSend);
}
function populateFormByCode()
{
checkValidEntry();
var title=document.getElementById('titleSpan');
title.innerHTML="";
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="Invalid")
{
window.location="Designations.jsp";
}
else
{
title.innerHTML=response.title;
}
}
else
{
alert('Some problem has occured');
}
}
};
var urlParameter=new URLSearchParams(window.location.search);
code=urlParameter.get("code");
var dataToSend="getByCode?code="+encodeURI(code);
xmlHttpRequest.open('GET',dataToSend,true);
xmlHttpRequest.send();
}
window.addEventListener('load',populateFormByCode);
</script>

<h2>Designation (Delete Module)</h2>
<form id='detailsForm'>
Designation : &nbsp; <span id='titleSpan'></span><br>
Are you sure you want to delete this designation?<br><br>
<button type='button' onclick='deleteDesignation()'>Delete</button>
<button type='button' onclick='cancelAdditionButton()'>Cancel</button>
<jsp:include page='MasterPageBottomSection.jsp'/>
<form  id='cancelButtonForm' action='/stylethree/Designations.jsp'>
</form>