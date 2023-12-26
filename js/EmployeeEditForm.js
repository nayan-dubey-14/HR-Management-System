function validateForm(form)
{
var valid=true;
var firstFocus;

var name=form.name.value.trim();
var nameErrorSection=document.getElementById("nameErrorSection");
nameErrorSection.innerHTML='';
if(name.length==0)
{
nameErrorSection.innerHTML='Name Required';
firstFocus=form.name;
valid=false;
}

var designationCode=form.designation.value;
var designationCodeErrorSection=document.getElementById("designationErrorSection");
designationCodeErrorSection.innerHTML='';
if(designationCode==-1)
{
designationCodeErrorSection.innerHTML='Select Designation';
if(firstFocus==null) firstFocus=form.designationCode;
valid=false;
}

var dob=form.date.value;
var dobErrorSection=document.getElementById("dateErrorSection");
dobErrorSection.innerHTML='';
if(dob.length==0)
{
dobErrorSection.innerHTML='Select Date';
if(firstFocus==null) firstFocus=form.dateErrorSection;
valid=false;
}

var male=document.getElementById("male");
var female=document.getElementById("female");
var genderErrorSection=document.getElementById("genderErrorSection");
genderErrorSection.innerHTML='';
if(male.checked==false && female.checked==false)
{
genderErrorSection.innerHTML='Select Gender';
valid=false;
}

var basicSalary=form.basicSalary.value.trim();
var flag=true;
var basicSalaryErrorSection=document.getElementById("basicSalaryErrorSection");
basicSalaryErrorSection.innerHTML='';
if(basicSalary.length==0)
{
basicSalaryErrorSection.innerHTML='Basic Salary Required';
if(firstFocus==null) firstFocus=form.basicSalary;
valid=false;
}
var e='0123456789.';
for(var i=0;i<basicSalary.length;i++)
{
if(e.indexOf(basicSalary.charAt(i))==-1) 
{
basicSalaryErrorSection.innerHTML='Invalid basic salary';
if(firstFocus==null) firstFocus=form.basicSalary;
valid=false;
flag=false;
break;
}
}
if(flag==true)
{
var indexOfDot=basicSalary.indexOf('.');
if(indexOfDot!=-1)
{
if(basicSalary.length-indexOfDot-1>2)
{
basicSalaryErrorSection.innerHTML='Invalid basic salary';
if(firstFocus==null) firstFocus=form.basicSalary;
valid=false;
}
}
}
var panNumber=form.panNumber.value.trim();
var panNumberErrorSection=document.getElementById("panNumberErrorSection");
panNumberErrorSection.innerHTML='';
if(panNumber.length==0)
{
panNumberErrorSection.innerHTML='PAN Number Required';
if(firstFocus==null) firstFocus=form.panNumber;
valid=false;
}

var aadharCardNumber=form.aadharCardNumber.value.trim();
var aadharCardNumberErrorSection=document.getElementById("aadharCardNumberErrorSection");
aadharCardNumberErrorSection.innerHTML='';
if(aadharCardNumber.length==0)
{
aadharCardNumberErrorSection.innerHTML='Aadhar Card Number Required';
if(firstFocus==null) firstFocus=form.aadharCardNumber;
valid=false;
}


if(firstFocus!=null) firstFocus.focus();
return valid;
}
function cancelAdditionButton()
{
document.getElementById('cancelButtonForm').submit();
}
