<jsp:include page='MasterPageTopSection.jsp'/>
<script src='/stylethree/js/EntryChecker.js'></script>
<script src='/stylethree/js/DesignationEditForm.js'></script>
<script src='/stylethree/js/DesignationModule.js'></script>
<script>
var code;
function updateDesignation()
{
var titleSpan=document.getElementById('titleSpan');
titleSpan.innerHTML="";
var tmp=validateForm(document.getElementById('detailsForm'));
if(tmp==false) return;
var title=document.getElementById('title').value;
var designation={
"code":code,
"title":title
};
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="Designation Updated Successfully")
{
alert('Updated Successfully');
window.location="Designations.jsp";
}
else
{
titleSpan.innerHTML=response;
}
}
else
{
alert('Some problem has occured');
}
}
};
xmlHttpRequest.open('POST','updateDesignation',true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send(JSON.stringify(designation));
}
function populateFormByCode()
{
checkValidEntry();
var title=document.getElementById('title');
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
title.value=response.title;
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
<h2>Designation (Edit Module)</h2>
<span class='error' id='titleSpan'></span>
<form id='detailsForm'>
Designation&nbsp;
<input type='text' id='title' name='title' size=36 maxlength=35>
<span id='titleErrorSection' class='error'></span><br><br>
<button type='button' onclick='updateDesignation()'>Update</button>
<button type='button' onclick='cancelAdditionButton()'>Cancel</button>

<jsp:include page='MasterPageBottomSection.jsp'/>
<form  id='cancelButtonForm' action='/stylethree/Designations.jsp'>
</form>
