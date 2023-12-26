<jsp:include page='MasterPageTopSection.jsp'/>
<script src='/stylethree/js/EntryChecker.js'></script>
<script src='/stylethree/js/EmployeeEditForm.js'></script>
<script src='/stylethree/js/EmployeeModule.js'></script>
<script>
var employeeId;
var code;
var gender;
var indian;
function updateEmployee()
{
var panNumberError=document.getElementById('panNumberErrorSection');
panNumberError.innerHTML="";
var aadharCardNumberError=document.getElementById('aadharCardNumberErrorSection');
aadharCardNumberError.innerHTML="";
var tmp=validateForm(document.getElementById('detailsForm'));
if(tmp==false) return;
var name=document.getElementById('name').value;
var designationCode=document.getElementById('designation').value;
var date=document.getElementById('date').value;
var male=document.getElementById('male');
var female=document.getElementById('female');
if(male.checked==true) gender="M";
if(female.checked==true) gender="F";
var i=document.getElementById('indian');
if(i.checked==true) indian="Y";
else indian=null;
var basicSalary=document.getElementById('basicSalary').value;
var panNumber=document.getElementById('panNumber').value;
var aadharCardNumber=document.getElementById('aadharCardNumber').value;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=this.responseText;
if(response=="Employee Updated Successfully")
{
alert('Employee Updated');
window.location='Employees.jsp';
}
else
{
var dataSplits=response.split(",");
if(dataSplits.length==1)
{
panNumberError.innerHTML=dataSplits[0];
}
else
{
if(dataSplits[0]!="") panNumberError.innerHTML=dataSplits[0];
aadharCardNumberError.innerHTML=dataSplits[1];
}
}
}
else
{
alert('Some problem has occured');
}
}
};
var dataToSend="employeeId="+encodeURI(employeeId)+"&name="+encodeURI(name)+"&designationCode="+encodeURI(designationCode)+"&date="+encodeURI(date)+"&gender="+encodeURI(gender)+"&indian="+encodeURI(indian)+"&basicSalary="+encodeURI(basicSalary)+"&panNumber="+encodeURI(panNumber)+"&aadharCardNumber="+encodeURI(aadharCardNumber);
xmlHttpRequest.open('POST','updateEmployee',true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(dataToSend);
}
function populateDesignations()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=this.responseText;
var dataSplits=response.split(",");
var selectElem=document.getElementById('designation');
var optionElem;
optionElem=document.createElement('option');
optionElem.value=1;
optionElem.text="<select>";
selectElem.appendChild(optionElem);
for(var i=0;i<dataSplits.length;i+=2)
{
optionElem=document.createElement('option');
optionElem.value=dataSplits[i];
optionElem.text=dataSplits[i+1];
if(dataSplits[i]==code) optionElem.selected=true;
selectElem.appendChild(optionElem);
}
}
else
{
alert('Some problem has occured');
}
}
};
xmlHttpRequest.open('GET','populateDesignation',true);
xmlHttpRequest.send();
}
function populateFormByEmployeeId()
{
checkValidEntry();
var name=document.getElementById('name');
var designationCode=document.getElementById('designation');
var date=document.getElementById('date');
var male=document.getElementById('male');
var female=document.getElementById('female');
var i=document.getElementById('indian');
var basicSalary=document.getElementById('basicSalary');
var panNumber=document.getElementById('panNumber');
var aadharCardNumber=document.getElementById('aadharCardNumber');
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=this.responseText;
if(response=="INVALID")
{
window.location="Employees.jsp";
}
else
{
var dataSplits=response.split(",");
employeeId=dataSplits[0];
name.value=dataSplits[1];
code=dataSplits[2];
gender=dataSplits[4];
if(gender=="M") male.checked=true;
if(gender=="F") female.checked=true;
date.value=dataSplits[5];
basicSalary.value=dataSplits[6];
indian=dataSplits[7];
if(indian=="true") i.checked=true;
panNumber.value=dataSplits[8];
aadharCardNumber.value=dataSplits[9];
populateDesignations();
}
}
else
{
alert('Some problem has occured');
}
}
};
var urlParameter=new URLSearchParams(window.location.search);
employeeId=urlParameter.get("employeeId");
var dataToSend="getByEmployeeId?employeeId="+encodeURI(employeeId);
xmlHttpRequest.open('GET',dataToSend,true);
xmlHttpRequest.send();
}
window.addEventListener('load',populateFormByEmployeeId);
</script>
<h2>Employee (Edit Module)</h2>
<form id='detailsForm'>
<table>
<tr>
<td>Name</td>
<td>
<input type='text' id='name' name='name' size=51 maxlength=50>
<span id='nameErrorSection' style='color:red'></span><br>
</td>
</tr>
<tr>
<td>Designation</td>
<td><select id='designation' name='designation'>
</select>
<span id='designationErrorSection' style='color:red'></span><br>
</td>
</tr>
<tr>
<td>Date of birth</td>
<td><input type='date' id='date' name='date'>
<span id='dateErrorSection' style='color:red'></span><br></td>
</tr>
<tr>
<td>Gender</td>
<td>Male<input type='radio' id='male' name='gender'>
Female<input type='radio' id='female' name='gender'>&nbsp;
<span id='genderErrorSection' style='color:red'></span></td>
</tr>
<tr>
<td>Indian?</td>
<td><input type='checkbox' id='indian' name='indian'></td>
</tr>
<tr>
<td>Basic Salary</td>
<td><input type='text' id='basicSalary' name='basicSalary' size='13' maxlength='12'>
<span id='basicSalaryErrorSection' style='color:red'></span></td>
</tr>
<tr>
<td>PAN Number</td>
<td><input type='text' id='panNumber' name='panNumber' maxlength='15' size='16'>
<span id='panNumberErrorSection' style='color:red'></span></td>
</tr>
<tr>
<td>Aadhar Card Number</td>
<td><input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16'>
<span id='aadharCardNumberErrorSection' style='color:red'></span></td>
</tr>
<tr>
<td><button type='button' onclick='updateEmployee()'>Update</button>
<button type='Button' onclick='cancelAdditionButton()'>Cancel</button></td>
</tr>
</table>
<jsp:include page='MasterPageBottomSection.jsp'/>
<form  id='cancelButtonForm' action='/stylethree/Employees.jsp'>
</form>

