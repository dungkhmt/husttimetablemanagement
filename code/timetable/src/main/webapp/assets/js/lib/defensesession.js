
$(".defense-session").click(function(){

	var id = $(this).children(".id-defense").val();	
	var dateDefense = $(this).children(".date-defense").val();	
	var desDefense = $(this).children(".description-defense").val();	
	var actDefense = $(this).children(".active-defense").val();	
	$("#edit-date-session").val(dateDefense);
	$("#edit-description-session").val(desDefense);
	if(actDefense > 0)
	{
		actDefense = true;
	}else{
		actDefense = false;
	}
	$("#edit-active-session").attr("checked",actDefense);
	$("#edit-id-session").val(id);
});

$("#add-defense").click(function(){
	dateDefense = $("#date-session").val();
	desDefense = $("#description-session").val();
	actDefense = $("#active-defense").prop('checked');
	if(actDefense == true){
		actDefense = 1;
	}
	else{
		actDefense = 0;
	}
	formDefense = {"dateDefense":dateDefense,"desDefense":desDefense,"actDefense":actDefense};

	$.ajax({
		url:'addDefenseSession.json',
		data:formDefense,
		dataType: "html",
		success:function(s,x){					
			addRowSession(s,dateDefense,actDefense,desDefense);
			alertSuccess("You added success")
		},
		error:function(data, textStatus, jqXHR){
			alertWarning("textStatus");
		}
	});
});
function addRowSession(id,date,act,des)
{
	
	if(act == true){
		var rhtml = '<a href="#" class="list-group-item list-group-item-success defense-session" data-toggle="modal" data-target="#form-edit-defensession">'+date+'<input type="hidden" value='+id+' class="id-defense"/><input type="hidden" value='+act+' class="active-defense"/><input type="hidden" value='+des+' class="description-defense"/><input type="hidden" value='+date+' class="date-defense"/></a>	';
		$("#list-active").append(rhtml);
	}else{
		var rhtml = '<a href="#" class="list-group-item disabled defense-session" data-toggle="modal" data-target="#form-edit-defensession">'+date+'<input type="hidden" value='+id+' class="id-defense"/><input type="hidden" value='+act+' class="active-defense"/><input type="hidden" value='+des+' class="description-defense"/><input type="hidden" value='+date+' class="date-defense"/></a>	';
		$("#list-deactive").append(rhtml);
	}
}
function removeRowSession(id){
	$(".defense-session").each(function(){
		if($(this).children(".id-defense").val() == id)
		{
			$(this).remove();
		}
	});
}


$("#delete-session").click(function(){
	$.ajax({
		url:'delDefenseSession.json',
		data:{"id":$("#edit-id-session").val()},
		dataType: "html",
		success:function(s,x){
			removeRowSession($("#edit-id-session").val());			
			alertSuccess("You deleted success");
		},
		error:function(data, textStatus, jqXHR){
			alertWarning("textStatus");
		}
	});
});
function updateRowSession(id,date,des,act){
	if(act == true)
	{
		act = 1;
	}else{
		act = 0;
	}
	$(".defense-session").each(function(){
		if($(this).children(".id-defense").val() == id)
		{
			if($(this).children(".active-defense").val() == act)
			{
				htmla = date+'<input type="hidden" value='+id+' class="id-defense"/><input type="hidden" value='+act+' class="active-defense"/><input type="hidden" value='+des+' class="description-defense"/><input type="hidden" value='+date+' class="date-defense"/>';
				$(this).html(htmla);
				$(this).animate({'background-color': '#bce8f1'},'slow');
				$(this).animate({'background-color': '#dff0d8'},'slow');
				
			}else{
				$(this).remove();
				addRowSession(id,date,act,des);
			}
		}
	});
}
$("#save-session").click(function(){
	dt = {"id":$("#edit-id-session").val(),"date":$("#edit-date-session").val(),"des":$("#edit-description-session").val(),"act":$("#edit-active-session").prop("checked")};
	$.ajax({
		url:'updateDefenseSession.json',
		data:dt,
		dataType: "html",
		success:function(s,x){			
			updateRowSession(dt.id,dt.date,dt.des,dt.act);			
			alertSuccess("You update success");
		},
		error:function(data, textStatus, jqXHR){
			$("#form-edit-defensession").hide(300);
			alertWarning("textStatus");
		}
	});
});
