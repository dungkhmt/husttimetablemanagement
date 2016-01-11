<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">
	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Chỉnh sửa User</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Chỉnh sửa User
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/users.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/edit-user-detail.html" method="POST" commandName="userFormEdit" role="form">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="username">Tên User</label>
                                <form:input path="username" class="form-control" name="username" value="${dataUser['username']}" type="text" placeholder="Username"></form:input>
								<form:errors path="username" class="alert-danger"></form:errors>
                            </div>
                            <div class="form-group">
                                <label for="epassword">Mật khẩu</label>
                                <form:input path="epassword" class="form-control" name="epassword" type="password" placeholder="Password"></form:input>
								<form:errors path="epassword" class="alert-danger"></form:errors>
                            </div>
                            <div class="form-group">
                                <label>Kích hoạt ?</label>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="activated" <c:if test="${dataUser['activated'] == 1}">checked</c:if> value="1"></input>Kích hoạt
                                    </label>
                                </div>
                            </div>
                            <form:hidden path="password" name="password" value="password"/>
                            <form:hidden path="userId" name="userId" value="${dataUser['userId']}"/>
                            <form:hidden path="userRoleId" name="userRoleId" value="${dataUser['userRoleId']}"/>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                            <button type="reset" class="btn btn-primary cancel">Hủy</button>
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                        <div class="col-lg-6">
                        	<div class="form-group">
                                <label for="email">Email</label>
                                <form:input path="email" class="form-control" name="email" value="${dataUser['email']}" placeholder="Email"></form:input>
								<form:errors path="email" class="alert-danger"></form:errors>
                            </div>
                            
                            <div class="form-group">
                                <label>Role</label>
                                <form:select path="role" class="form-control" name="role">
                                     <c:if test="${currentUserRole eq 'ROLE_ADMIN'}">
                                     	<option value="ROLE_ADMIN" <c:if test="${dataUser['userRole'] == 'ROLE_ADMIN'}">selected</c:if>>Quản trị</option>
                                     </c:if>
                                     <c:if test="${dataUser['userRole'] eq 'ROLE_USER' && currentUserName ne dataUser['username']}">
                                     	<option value="ROLE_USER" <c:if test="${dataUser['userRole'] == 'ROLE_USER'}">selected</c:if> >Người dùng</option>
                                     </c:if>
                                </form:select>
                            </div>
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                    </div>
                    <!-- /.row (nested) -->
                    </form:form>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->
<script type="text/javascript">
<!--
	
//-->
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/users.html";
	})
});
</script>
