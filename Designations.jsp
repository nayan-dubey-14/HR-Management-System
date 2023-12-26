<jsp:include page='MasterPageTopSection.jsp'/>
<script src='/stylethree/js/DesignationModule.js'></script>
<script src='/stylethree/js/EntryChecker.js'></script>
<script>
function populateDesignations()
{
checkValidEntry();
var populateDesignationGridTable=document.getElementById('populateDesignationGridTable');
var populateDesignationGridTableBody=populateDesignationGridTable.getElementsByTagName("tbody")[0];
var populateDesignationGridTableBodyRow=populateDesignationGridTableBody.getElementsByTagName("tr")[0];
populateDesignationGridTableBodyRow.remove();
var dynamicRow;
var dynamicRowCells;
var cells;
var serialNumber=0;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var designations=JSON.parse(this.responseText);
for(var i=0;i<designations.length;i++)
{
dynamicRow=populateDesignationGridTableBodyRow.cloneNode(true);
populateDesignationGridTableBody.appendChild(dynamicRow);
dynamicRowCells=dynamicRow.getElementsByTagName("td");
for(var e=0;e<dynamicRowCells.length;e++)
{
cells=dynamicRowCells[e];
var cellsId=cells.getAttribute("placeHolder");
if(cellsId==null) continue;
if(cellsId=="serialNumber") cells.innerHTML=++serialNumber;
if(cellsId=="code") cells.innerHTML=designations[i].code;
if(cellsId=="title") cells.innerHTML=designations[i].title;
if(cellsId=="editOption") cells.innerHTML="<a href='/stylethree/DesignationEditForm.jsp?code="+designations[i].code+"'>Edit</a>";
if(cellsId=="deleteOption") cells.innerHTML="<a href='/stylethree/DeleteDesignation.jsp?code="+designations[i].code+"'>Delete</a>";
}
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
<h1>Designations</h1>
<table border='1' id='populateDesignationGridTable'>
<thead>
<tr>
<th colspan='4' class='add-link'>
<a href='/stylethree/DesignationAddForm.jsp'>Add new designation</a>
</th>
</tr>
<tr>
<th class='serial-number'>S.No.</th>
<th class='designations'>Designations</th>
<th class='edit-link'>Edit</th>
<th class='delete-link'>Delete</th>
</tr>
</thead>
<tbody>
<tr>
<td style='text-align:right;padding:5px' placeHolder='serialNumber'></td>
<td style='text-align:left' placeHolder='title'></td>
<td style='text-align:center' placeHolder='editOption'></td>
<td style='text-align:center' placeHolder='deleteOption'></td>
</tr>
</tbody>
</table>

<jsp:include page='MasterPageBottomSection.jsp'/>