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
			<h1 class="page-header">Tạo danh sách thành viên hội đồng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/cp/save-a-jury-members.html" method="POST" role="form" accept-charset="UTF-8">
		<div class="row">
			<div id="status" class="alert alert-success"></div>
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
					<h4>Danh sách giảng viên</h4>
					<c:if test="${listMembers != null}">
						<select class="form-control" name="juryMember" id="juryMember" >
							<c:forEach items="${listMembers}" var="aMember">
			               		<option value="${aMember.staff_Code}" >${aMember.staff_Name}</option>
			               	</c:forEach>
                    	</select>
                    </c:if>   				
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
	
		</div>
		<!-- /.row -->
		
		<div class="panel panel-default">
				<div class="panel-heading"><h4>Chọn học giảng viên</h4> 
				    <button type="button" class="form btn btn-primary" onclick="addJuryMember();">Thêm</button>
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
									<th title="Name">Tên Giảng viên</th>
									<th title="Session">Đợt bảo vệ</th>
									<th title="Remove">Remove</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listJuryMembers != null}">
									<c:forEach items="${listJuryMembers}" var="aJuryMember">															
										<tr class="gradeX">
										 	<td><c:out value="${aJuryMember.memJuryMember.staff_Name}"/></td>
										 	<td><c:out value="${aJuryMember.defenseSessionJuryMember.DEFSESS_Name}"/></td>
										 	<td class="center"><button type="button" onclick="v_fRemoveJuryMember('${aJuryMember.juryMem_Code}');" class="btn btn-danger btn-xs" title="Remove">Remove</button></td>
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
		$('#cancel').click(function() {
			window.location = baseUrl + "/cp/home.html";
		});	
		
	});

	
	function addJuryMember()
	{
		var sJuryMemberCode = $("select#juryMember option:selected").val();
		var sDefenseSessionCode = $("select#defenseSession option:selected").val();
		var savingJuryMemberUrl = "${baseUrl}/cpservice/savejurymember.html";
		if (sJuryMemberCode.length > 0 ) {
			 $.ajax({
					type: "POST",
					url: savingJuryMemberUrl,
					data: "sJuryMemberCode="+sJuryMemberCode+"&sDefenseSessionCode="+sDefenseSessionCode,
					cache: true,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						$("#status").html( html );
						window.location = baseUrl + "/cp/jurymembers.html";
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
	
	function v_fRemoveJuryMember(the_sz_JuryMemberCode){
		var r = confirm("Bạn có muốn xóa bản ghi này ?");
		if (r == true) {
			var sRemoveAJuryMemberUrl = baseUrl + "/cp/remove-a-jury-member/"+the_sz_JuryMemberCode+".html";
			window.location = sRemoveAJuryMemberUrl;
		} else {
		    return false;
		}
	}
	
</script>