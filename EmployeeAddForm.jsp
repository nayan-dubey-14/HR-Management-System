<jsp:include page='MasterPageTopSection.jsp'/>
<script src='/stylethree/js/EntryChecker.js'></script>
<script src='/stylethree/js/EmployeeModule.js'></script>
<script src='/stylethree/js/EmployeeAddForm.js'></script>
<script>
function addEmployee()
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
var gender;
if(male.checked==true) gender="M";
if(female.checked==true) gender="F";
var i=document.getElementById('indian');
var indian=null;
if(i.checked==true) indian='Y';
var basicSalary=document.getElementById('basicSalary').value;
var panNumber=document.getElementById('panNumber').value;
var aadharCardNumber=document.getElementById('aadharCardNumber').value;
var emp={
"name":name,
"designationCode":designationCode,
"dateOfBirth":date,
"gender":gender,
"indian":indian,
"basicSalary":basicSalary,
"panNumber":panNumber,
"aadharCardNumber":aadharCardNumber
};
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
if(response=="Employee Added Successfully")
{
alert('Employee Added');
window.location='Employees.jsp';
}
else if(response=="PAN and Aadhar Card number exists")
{
panNumberError.innerHTML="PAN number exists";
aadharCardNumberError.innerHTML="Aadhar Card Number exists";
}
else if(response=="PAN number exists")
{
panNumberError.innerHTML="PAN number exists";
}
else if(response=="Aadhar Card Number exists")
{
aadharCardNumberError.innerHTML="Aadhar Card Number exists";
}
}
else
{
alert('Some problem has occured');
}
}
};
xmlHttpRequest.open('POST','addEmployee',true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send(JSON.stringify(emp));
}
function populateDesignations()
{
checkValidEntry();
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
var selectElem=document.getElementById('designation');
var optionElem;
optionElem=document.createElement('option');
optionElem.value=-1;
optionElem.text="<select>";
selectElem.appendChild(optionElem);
for(var i=0;i<response.length;i++)
{
optionElem=document.createElement('option');
optionElem.value=response[i].code;
optionElem.text=response[i].title;
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
window.addEventListener('load',populateDesignations);
</script>
<h2>Employee (Add Module)</h2>
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
<td><button type='button' onclick='addEmployee()'>Add</button>
<button type='button' onclick='cancelAdditionButton()'>Cancel</button></td>
</tr>
</table>
<jsp:include page='MasterPageBottomSection.jsp'/>
<form  id='cancelButtonForm' action='/stylethree/Employees.jsp'>
</form>