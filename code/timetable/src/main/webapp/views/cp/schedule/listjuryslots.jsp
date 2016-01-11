<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<div id="${(disableHeader != null) ? 'page-wrapper-no-layout' : 'page-wrapper'}">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tạo danh sách kíp của hội đồng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/cp/save-a-jury-slot.html" method="POST" role="form" accept-charset="UTF-8">
		<div class="row">
			<div class="col-lg-6">
				<div class="well">
					<h4>Chọn đợt bảo vệ</h4>
					  <c:if test="${listDefenseSessions != null}">
						<select class="form-control" name="defenseSession" id="defenseSession">
							<c:forEach items="${listDefenseSessions}" var="defenseSession">
			               		<option value="${defenseSession.DEFSESS_Code}" >${defenseSession.DEFSESS_Name}</option>
			               	</c:forEach>
	                    </select>
                     </c:if>
				</div>
				<div class="well">
					<h4>Thời gian</h4>
					<input type="text" name="time" id="time" />
					<h4>Thứ tự</h4>
					<input type="text" name="order" id="order" var="0" />
				</div>
			</div>
		</div>
		<!-- /.row -->
		
		<div class="panel panel-default">
				<div class="panel-heading"><h4></h4> 
				    <button type="button" class="form btn btn-primary" onclick="addJurySlot();" id="choose">Thêm</button>
				</div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-professor">
							<thead>
								<tr>
									<th title="Index">Số thứ tự</th>
									<th title="Code">Thời gian</th>
									<th title="Remove">Remove</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listJurySlots != null}">
									<c:forEach items="${listJurySlots}" var="aJurySlot">															
										<tr class="gradeX">
											<td><c:out value="${aJurySlot.jurySlot_Index}"/></td>
										 	<td><c:out value="${aJurySlot.jurySlot_Code}"/></td>
										 	<td class="center"><button type="button" id="removeJurySlot" onclick="v_fRemoveJurySlot('${aJurySlot.jurySlot_Code}');" class="btn btn-danger btn-xs" title="Remove">Remove</button></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

				</div>
			</div>

		
		<div class="row" style="padding-bottom:20px;">
			<div class="col-lg-4">
				<button type="button" class='form btn btn-primary' id="cancel" >Quay về</button>				
			</div>
			<!-- /.col-lg-4 -->
		</div>
		
		<!-- /.row -->
	</form:form>
</div>
<!-- /#page-wrapper -->

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>
	

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#save').click(function(){
			keyword = $('#data-keyword').children();
			var index = 0;
			keyword.each(function(){
				code = $(this).children(".keyword-code").val();
				input = $('<input path="staffKeywords" class="keyword-code" name="staffKeywords['+index+']" type="hidden" value="'+code+'">');
				$('#data-keyword').append(input);	
				index++;		
			});
			
			document.getElementById('form-add').submit();
		});
		
		
		$('#cancel').click(function() {
			window.location = baseUrl + "/cp/home.html";
		});	
		
	});

	
	function addJurySlot()
	{
		var sDefenseSessionCode = $("select#defenseSession option:selected").val();
		var savingJurySlotUrl = "${baseUrl}/cpservice/savejuryslot.html";
		var sTime = $("#time").val();
		var sOrder = $("#order").val();
		
		if (sDefenseSessionCode.length > 0 && sTime.length > 0 && sOrder.length > 0) {
			 $.ajax({
					type: "POST",
					url: savingJurySlotUrl,
					data: "sDefenseSessionCode="+sDefenseSessionCode+"&sTime="+sTime+"&sOrder="+sOrder,
					cache: true,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						$("#status").html( html );
						window.location = baseUrl + "/cp/juryslots.html";
					}
				});
		}else{
			/*
			var sDepartment = '<select class="form-control" style="width:200px;" name="threadDepartment" id="threadDepartment">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#list-department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" style="width:200px;" name="threadStaff" id="threadStaff">';
				sStaffs += '<option value="">Chọn cán bộ</option>';
				sStaffs +=  '</select>';
			$("#list-staff").html( sStaffs );
			*/
		}
	}
	
	function v_fRemoveJurySlot(the_sz_JurySlotCode){
		var r = confirm("Bạn có muốn xóa bản ghi này ?");
		if (r == true) {
			var sRemoveAJurySlotUrl = baseUrl + "/cp/remove-a-jury-slot/"+the_sz_JurySlotCode+".html";
			window.location = sRemoveAJurySlotUrl;
		} else {
		    return false;
		}
	}
	
</script>