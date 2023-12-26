<script src='/stylethree/js/EntryChecker.js'></script>
<jsp:include page='MasterPageTopSection.jsp'/>
<script>
window.addEventListener('load',checkValidEntry);
var moduleDiv=document.getElementById('module');
var a=document.createElement('a');
a.setAttribute('href','/stylethree/Designations.jsp');
a.innerHTML='Designations';
moduleDiv.appendChild(a);
var b=document.createElement('br');
moduleDiv.appendChild(b);
a=document.createElement('a');
a.setAttribute('href','/stylethree/Employees.jsp');
a.innerHTML='Employees';
moduleDiv.appendChild(a);
</script>
<h1>WELCOME</h1>
<jsp:include page='MasterPageBottomSection.jsp'/>