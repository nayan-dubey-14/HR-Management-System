<jsp:include page='MasterPageTopSection.jsp' />
<script src='/stylethree/js/EntryChecker.js'></script>
<script src='/stylethree/js/Employees.js'></script>
<script src='/stylethree/js/EmployeeModule.js'></script>
<script>
function dynamicRowOnClickAction(row,employeeId)
{
return function()
{
selectRow(row,employeeId);
}
}
function populateEmployeesGridTable()
{
checkValidEntry();
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var response=JSON.parse(this.responseText);
var employee;
for(var i=0;i<response.length;i++)
{
employee=new Employee();
employee.employeeId=response[i].id;
employee.name=response[i].name;
employee.designationCode=response[i].designationCode;
employee.designation=response[i].title;
employee.gender=response[i].gender;
employee.dateOfBirth=response[i].dateOfBirth;
employee.basicSalary=response[i].basicSalary;
employee.isIndian=response[i].isIndian;
employee.panNumber=response[i].panNumber;
employee.aadharCardNumber=response[i].aadharCardNumber;
employees[i]=employee;
}
}
else
{
alert('Some problem');
}
}
};
xmlHttpRequest.open('GET','populateEmployee',false);
xmlHttpRequest.send();
var employeesGridTable=document.getElementById('employeesGridTable');
var employeesGridTableBody=employeesGridTable.getElementsByTagName("tbody")[0];
var employeesGridTableBodyRow=employeesGridTableBody.getElementsByTagName("tr")[0];
employeesGridTableBodyRow.remove();
var dynamicRow;
var dynamicRowCellsTemplate;
var dynamicRowCells;
for(var k=0;k<employees.length;k++)
{
dynamicRow=employeesGridTableBodyRow.cloneNode(true);
employeesGridTableBody.append(dynamicRow);
dynamicRow.onclick=dynamicRowOnClickAction(dynamicRow,employees[k].employeeId);
dynamicRowCellsTemplate=dynamicRow.getElementsByTagName("td");
for(var i=0;i<dynamicRowCellsTemplate.length;i++)
{
dynamicRowCells=dynamicRowCellsTemplate[i];
var dynamicRowCellsId=dynamicRowCells.getAttribute("placeHolderId");
if(dynamicRowCellsId==null) continue;
if(dynamicRowCellsId=="serialNumber") dynamicRowCells.innerHTML=k+1;
if(dynamicRowCellsId=="employeeId") dynamicRowCells.innerHTML=employees[k].employeeId;
if(dynamicRowCellsId=="name") dynamicRowCells.innerHTML=employees[k].name;
if(dynamicRowCellsId=="designationCode") dynamicRowCells.innerHTML=employees[k].designationCode;
if(dynamicRowCellsId=="designation") dynamicRowCells.innerHTML=employees[k].designation;
if(dynamicRowCellsId=="gender") dynamicRowCells.innerHTML=employees[k].gender;
if(dynamicRowCellsId=="isIndian") 
{
if(employees[k].isIndian==true) dynamicRowCells.innerHTML="Yes";
else dynamicRowCells.innerHTML="No";
}
if(dynamicRowCellsId=="basicSalary") dynamicRowCells.innerHTML=employees[k].basicSalary;
if(dynamicRowCellsId=="dateOfBirth") dynamicRowCells.innerHTML=employees[k].dateOfBirth;
if(dynamicRowCellsId=="panNumber") dynamicRowCells.innerHTML=employees[k].panNumber;
if(dynamicRowCellsId=="aadharCardNumber") dynamicRowCells.innerHTML=employees[k].aadharCardNumber;
if(dynamicRowCellsId=="editOption") dynamicRowCells.innerHTML="<a href='/stylethree/EmployeeEditForm.jsp?employeeId="+employees[k].employeeId+"'>Edit</a>";
if(dynamicRowCellsId=="deleteOption") dynamicRowCells.innerHTML="<a href='/stylethree/DeleteEmployee.jsp?employeeId="+employees[k].employeeId+"'>Delete</a>";
}
}
}
window.addEventListener('load',populateEmployeesGridTable);
</script>

<h1>Employees</h1>
<!--right top division starts here-->
<div class='right-top-container'>
<table border='1' id='employeesGridTable'>
<thead>
<tr>
<th colspan='6' class='add-link'>
<a href='/stylethree/EmployeeAddForm.jsp'>Add Employee</a>
</th>
</tr>
<tr>
<th style='width:90px'>S.No.</th>
<th style='width:140px'>Id</th>
<th style='width:200px'>Name</th>
<th style='padding:5px;width:170px'>Designation</th>
<th style='width:60px'>Edit</th>
<th style='width:60px'>Delete</th>
</tr>
</thead>
<tbody>
<tr style='cursor:pointer'>
<td style='text-align:right;padding:5px'  placeHolderId='serialNumber'></td>
<td style='text-align:right' placeHolderId='employeeId'></td>
<td placeHolderId='name'></td>
<td style='text-align:left' placeHolderId='designation'></td>
<td style='text-align:center' placeHolderId='editOption'></td>
<td style='text-align:center' placeHolderId='deleteOption'></td>
</tr>
</tbody>
</table>
</div>  <!--right top division ends here-->
<!-- right bottom division starts here-->
<div class='right-bottom-container'>
<label class='details-label'>Details</label>
<table width='100%'>
<tr>
<td style='padding:10px'>Employee Id:<span id='details_employeeId'></span></td>
<td>Name:<span id='details_name'></span></td>
<td>Designation:<span id='details_designation'></span></td>
</tr>
<tr>
<td style='padding:10px'>Gender:<span id='details_gender'></span></td>
<td>Is Indian:<span id='details_isIndian'></span></td>
<td>Basic Salary:<span id='details_basicSalary'></span></td>
</tr>
<tr>
<td style='padding:10px'>Date of birth:<span id='details_dateOfBirth'></span></td>
<td>PAN number:<span id='details_panNumber'></span></td>
<td>Aadhar card number:<span id='details_aadharCardNumber'></span></td>
</tr>
</table>
</div>  <!--right bottom division ends here-->

<jsp:include page='MasterPageBottomSection.jsp'/>