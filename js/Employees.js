function Employee()
{
this.employeeId=0;
this.name="";
this.designationCode=0;
this.designation="";
this.gender="";
this.isIndian=true;
this.basicSalary=0;
this.dateOfBirth="";
this.panCard="";
this.aadharCardNumber="";
}
var selected=null;
var employees=[];
function selectRow(row,id)
{
if(selected!=null)
{
selected.style.background='white';
selected.style.color='black';
}
row.style.background='#8383af';
row.style.color='white';
selected=row;
var i=0;
for(i=0;i<employees.length;i++)
{
if(employees[i].employeeId==id) break;
}
var emp=employees[i];
document.getElementById('details_employeeId').innerHTML=emp.employeeId;
document.getElementById('details_name').innerHTML=emp.name;
document.getElementById('details_designation').innerHTML=emp.designation;
document.getElementById('details_gender').innerHTML=emp.gender;
if(emp.isIndian==true)
{
document.getElementById('details_isIndian').innerHTML='Yes';
}
else
{
document.getElementById('details_isIndian').innerHTML='No';
}
document.getElementById('details_basicSalary').innerHTML=emp.basicSalary;
document.getElementById('details_dateOfBirth').innerHTML=emp.dateOfBirth;
document.getElementById('details_panNumber').innerHTML=emp.panNumber;
document.getElementById('details_aadharCardNumber').innerHTML=emp.aadharCardNumber;
}