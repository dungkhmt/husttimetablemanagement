<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thông tin người dùng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form action="${baseUrl}/cp/edit-staff-detail.html" method="POST" commandName="staffFormEdit" role="form" accept-charset="UTF-8">
		<div class="row">
			<div class="col-lg-4">
				<div class="well">
					<h4>Email</h4>
					<p class="value">${staffEmail}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"}>
						<form:input path="staffEmail" class="form-control" name="staffEmail" type="text" placeholder="Email" value="${staffEmail}"></form:input>
	    				<form:errors path="staffEmail" class="alert-danger"></form:errors>
    				</div>
				</div>
				<div class="well">
					<h4>Họ tên</h4>
					<p class="value">${staffName}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"}>
						<form:input path="staffName" class="form-control" name="staffName" type="text" placeholder="Name" value="${staffName}"></form:input>
	    				<form:errors path="staffName" class="alert-danger"></form:errors>
    				</div>
				</div>
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-4">
				<div class="well">
					<h4>Điện thoại</h4>
					<p class="value">${staffPhone}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<form:input path="staffPhone" class="form-control" name="staffPhone" type="text" placeholder="Phone" value="${staffPhone}"></form:input>
	    				<form:errors path="staffPhone" class="alert-danger"></form:errors>
    				</div>
				</div>
				<div class="well">
					<h4>Bộ môn</h4>
					<p class="value">${staffDepartementName}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<form:select path="staffDepartment" class="form-control" name="staffDepartment">
							<c:forEach items="${departmentList}" var="department">
	                       		<option value="${department.department_Code}" <c:if test="${staffDepartementCode eq department.department_Code}">selected</c:if>>${department.department_Name}</option>
	                       	</c:forEach>
	                    </form:select>
	                    <form:errors path="staffDepartment" class="alert-danger"></form:errors>
                    </div>
				</div>
				<!-- /.col-lg-4 -->
			</div>
		</div>
		<!-- /.row -->
	
		<div class="row">
			<div class="col-lg-4">
				<form:hidden path="staffId" name="staffId" value="0"/>
				<button type="button" ${error == 1 ? "class='btn btn-primary value hidden'" : "class='btn btn-primary value'"} onclick="v_fEditTheStaffInfo(1);">Chỉnh sửa</button>
				<button type="submit" ${error == 1 ? "class='form btn btn-primary'" : "class='form btn btn-primary hidden'"} >Lưu</button>
				<button type="button" ${error == 1 ? "class='form btn btn-primary'" : "class='form btn btn-primary hidden'"} onclick="v_fEditTheStaffInfo(0);">Hủy</button>
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->
	</form:form>
</div>
<!-- /#page-wrapper -->
<script type="text/javascript">
	$(document).ready(function() {
		$('button.cancel').click(function() {
			window.location = baseUrl + "/cp/users.html";
		})
	});
	
	function v_fEditTheStaffInfo(the_i_Mode)
    {
		if(the_i_Mode == 1){
			$("#staffFormEdit").find(".form").each(function(){
				$(this).removeClass("hidden");
			});
			
			$("#staffFormEdit").find(".value").each(function(){
				$(this).addClass("hidden");
			});
		}else{
			$("#staffFormEdit").find(".form").each(function(){
				$(this).addClass("hidden");
			});
			
			$("#staffFormEdit").find(".value").each(function(){
				$(this).removeClass("hidden");
			});
		}
		
		
        /* var sz_AddUrl = "${baseUrl}/cpservice/editthestaff.json";
	      $.ajax({
	          type: "POST",
	          url: sz_AddUrl,
	          data: "id=" + the_i_StaffId,
	          success: function(response) {
	              if (response.status) {
	                  alert(response.message);
	                  //window.location = "${baseUrl}/cp/coursing.html";
	              } else {
	                  alert(response.message);
	              }
	          },
	          error: function(e) {
	              alert('Error: ' + e);
	          }
	      }); */
    }
</script>