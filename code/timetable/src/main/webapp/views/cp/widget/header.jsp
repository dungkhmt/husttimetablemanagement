<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


	                        
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="home.html">Viện Công nghệ Thông tin và Truyền Thông - ĐHBK Hà Nội</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  
                <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="<c:url value="${baseUrl}/cp/profile.html"/>"><i class="fa fa-user fa-fw"></i> Thông tin người dùng</a>
                </li>
                <li><a href="<c:url value="${baseUrl}/cp/changepass.html"/>"><i class="fa fa-gear fa-fw"></i> Cài đặt</a>
                </li>
                <li class="divider"></li>
                <li><a href="<c:url value="/j_spring_security_logout" />"><i class="fa fa-sign-out fa-fw"></i> Đăng xuất</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->
	
	<c:if test="${disableHeader == null}">
	    <div class="navbar-default sidebar" id="sidebar" role="navigation">
	        <div class="sidebar-nav navbar-collapse">
	            <ul class="nav" id="nav-accordion">
	            	<!-- 
	                <li class="sidebar-search">
	                    <div class="input-group custom-search-form">
	                        <input type="text" class="form-control" placeholder="Search...">
	                        <span class="input-group-btn">
	                        <button class="btn btn-default" type="button">
	                            <i class="fa fa-search"></i>
	                        </button>
	                    </span>
	                    </div>
	                </li>
	                <!-- /input-group -->
	                <c:if test="${currentUserRole eq 'ROLE_ADMIN'}">
		                <li>
		                    <a class="${users}" href="<c:url value="${baseUrl}/cp/users.html"/>"><i class="fa fa-user fa-fw"></i> Quản lý Users</a>
		                </li>
		                <li>
		                    <%-- <a class="${staffs}" href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Quản lý Nhân sự</a> --%>
		                </li>
	                </c:if>
	                <li>
	                    <a class="${papers}" href="<c:url value="${baseUrl}/cp/papers.html"/>"><i class="fa fa-book"></i> Quản lý Bài báo</a>
	                </li>
	                <li>
	                    <a class="${topics}" href="<c:url value="${baseUrl}/cp/topics.html"/>"><i class="fa fa-list-alt fa-fw"></i> Quản lý Đề tài</a>
	                </li>
	                <li>
	                    <a class="${patents}" href="<c:url value="${baseUrl}/cp/patents.html"/>"><i class="fa fa-file fa-fw"></i> Quản lý Bằng sáng chế</a>
	                </li>
	                <c:if test="${currentUserRole eq 'ROLE_ADMIN'}">
		                <li>
		                    <%-- <a class="${departments}" href="#"><i class="fa fa-table fa-fw"></i> Quản lý Bộ môn</a> --%>
		                </li>
		                <li>
		                    <%-- <a class="${falcuties}" href="#"><i class="fa fa-edit fa-fw"></i> Quản lý Khoa/Viện</a> --%>
		                </li>
		                <%-- <li>
		                    <a class="${settings}" href="#"><i class="fa fa-wrench fa-fw"></i> Settings</a>
		                </li> --%>
	                
		                <li>
		                    <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary.html"/>"><i class="fa fa-dashboard fa-fw"></i> Tổng hợp</a>
		                </li>
		            </c:if>
		                
		                <li>
		                    <a class="${classes}" href="<c:url value="${baseUrl}/cp/classes.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý lớp</a>
		                </li>
		                
		                <li>
		                    <a class="${professor}" href="<c:url value="${baseUrl}/cp/professors.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý giảng viên</a>
		                </li>
		                
		                <li>
		                    <a class="${student}" href="<c:url value="${baseUrl}/cp/students.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý học viên</a>
		                </li>
		                
		                <li>
		                    <a class="${thesis}" href="<c:url value="${baseUrl}/cp/listStudentToAssignThesis.html"/>"><i class="fa fa-dashboard fa-fw"></i> Phân đề tài cao học</a>
		                </li>
		                
		                <li>
		                    <a class="${thesis}" href="<c:url value="${baseUrl}/cp/listThesis.html"/>"><i class="fa fa-dashboard fa-fw"></i> Danh sách đề tài cao học</a>
		                </li>
		                
			            <li>
		                    <a class="${scheduling}" href="#"><i class="fa fa-wrench fa-fw"></i> Xếp lịch<span class="fa arrow"></span></a>
		                    <ul class="nav nav-second-level">
		                    	<li>
		                            <a class="${defensesession}" href="<c:url value="${baseUrl}/cp/defensesession.html"/>"><i class="fa fa-dashboard fa-fw"></i>Quản lý đợt bảo vệ</a>
		                        </li>
		                        <li>
		                            <a class="${scheduling}" href="<c:url value="${baseUrl}/cp/scheduling.html"/>"><i class="fa fa-dashboard fa-fw"></i>Danh sách học viên bảo vệ</a>
		                        </li>
		                        <li>
		                        	<a class="${scheduling}" href="<c:url value="${baseUrl}/cp/jurymembers.html"/>"><i class="fa fa-dashboard fa-fw"></i>Danh sách thành viên hội đồng</a>
		                        </li>
		                        <li>
		                        	<a class="${scheduling}" href="<c:url value="${baseUrl}/cp/juryrooms.html"/>"><i class="fa fa-dashboard fa-fw"></i>Danh sách phòng</a>
		                        </li>
		                        <li>
		                        	<a class="${scheduling}" href="<c:url value="${baseUrl}/cp/juryslots.html"/>"><i class="fa fa-dashboard fa-fw"></i>Danh sách kíp</a>
		                        </li>
		                        
		                        <li>
		                            <a class="${scheduling}" href="<c:url value="${baseUrl}/cp/settingjuries.html"/>"><i class="fa fa-dashboard fa-fw"></i>Xếp Hội Đồng</a>
		                        </li>
		                        <c:if test="${currentUserRole eq 'ROLE_ADMIN' || currentUserRole eq 'SUPER_ADMIN'}">
			                        
		                        </c:if>
		                    </ul>
		                    <!-- /.nav-second-level -->
		                </li>
		                
	                
	            </ul>
	        </div>
	        <!-- /.sidebar-collapse -->
	    </div>
	    <!-- /.navbar-static-side -->
    </c:if>
</nav>
