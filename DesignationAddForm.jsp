<jsp:include page='MasterPageTopSection.jsp'/>
<script src='/stylethree/js/EntryChecker.js'></script>
<script src='/stylethree/js/DesignationAddForm.js'></script>
<script src='/stylethree/js/DesignationModule.js'></script>
<script>
function addDesignation()
{
var titleSpan=document.getElementById('titleSpan');
titleSpan.innerHTML="";
var tmp=validateForm(document.getElementById('detailsForm'));
if(tmp==false) return;
var title=document.getElementById('title').value;
var designation={
"title":title
};
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="Designation Added Successfully")
{
alert('Added Successfully');
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
xmlHttpRequest.open('POST','addDesignation',true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send(JSON.stringify(designation));
}
window.addEventListener('load',checkValidEntry);
</script>
<h2>Designation (Add Module)</h2>
<span class='error' id='titleSpan'></span>
<form id='detailsForm'>
Designation&nbsp;
<input type='text' id='title' name='title' size=36 maxlength=35'>
<span id='titleErrorSection' class='error'></span><br><br>
<button type='button' onclick='addDesignation()' >Add</button>
<button type='button' onclick='cancelAdditionButton()'>Cancel</button>

<jsp:include page='MasterPageBottomSection.jsp'/>

<form  id='cancelButtonForm' action='/stylethree/Designations.jsp'>
</form>