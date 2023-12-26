var moduleDiv=document.getElementById('module');
var b=document.createElement('b');
b.innerHTML='Designations';
moduleDiv.appendChild(b);
var br=document.createElement('br');
moduleDiv.appendChild(br);
var a=document.createElement('a');
a.setAttribute('href','/stylethree/Employees.jsp');
a.innerHTML='Employees';
moduleDiv.appendChild(a);
br=document.createElement('br');
moduleDiv.appendChild(br);
a=document.createElement('a');
a.setAttribute('href','/stylethree/index.jsp');
a.innerHTML='Home';
moduleDiv.appendChild(a);

