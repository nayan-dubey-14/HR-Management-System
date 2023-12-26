function validateForm(form)
{
var title=form.title.value.trim();
var titleError=document.getElementById('titleErrorSection');
titleError.innerHTML='';
if(title.length==0)
{
titleError.innerHTML='required';
form.title.focus();
return false;
}
return true;
}
function cancelAdditionButton()
{
document.getElementById('cancelButtonForm').submit();
}
