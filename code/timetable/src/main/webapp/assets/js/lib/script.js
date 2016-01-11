function alertSuccess(mes)
{
	$("#alert-mes").slideUp(300);
	htmlmes = '<div onclick="offAlert(this)" class="alert alert-success alert-dismissible"  id="alert-success"><button type="button" class="close"><span class="sr-only">Close</span></button><strong>Well done!</strong> '+mes+'</div>';
	$("#alert-mes").html(htmlmes);
	$("#alert-mes").slideDown(300);
}
function alertWarning(mes)
{
	$("#alert-mes").slideUp(300);
	htmlmes = '<div onclick="offAlert(this)" class="alert alert-warning alert-dismissible"  id="alert-warning"><button type="button" class="close"><span class="sr-only">Close</span></button><strong>Warning!</strong> '+mes+'</div>';
	$("#alert-mes").html(htmlmes);
	$("#alert-mes").slideDown(300);
}
function offAlert(x){
	$(x).slideUp(300);
}