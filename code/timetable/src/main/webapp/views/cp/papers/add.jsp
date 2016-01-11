<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">

<style>.ui-datepicker-calendar {display: none;}​</style>

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>		   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm mới bài báo</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới bài báo
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/papers.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/save-a-paper.html" method="POST" commandName="paperFormAdd" role="form" enctype="multipart/form-data">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label>Nhóm*</label>
	                                    <form:select path="paperCatCode" class="form-control" name="paperCatCode" onchange="v_fChangeCategory();">
	                                    	<c:forEach items="${paperCategory}" var="paperCat">
		                                        <option value="${paperCat.PCAT_Code}">${paperCat.PCAT_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="paperCatCode" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="paperPubName">Tên bài báo*</label>
	                                    <form:input path="paperPubName" class="form-control" name="paperPubName" placeholder="Pubication Name"></form:input>
	    								<form:errors path="paperPubName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="paperJConfName">Tên tạp chí*</label>
	                                    <form:input path="paperJConfName" class="form-control" name="paperJConfName" placeholder="Journal Conference Name"></form:input>
	    								<form:errors path="paperJConfName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="paperISSN">Chỉ số ISSN</label>
	                                    <form:input path="paperISSN" class="form-control" name="paperISSN" placeholder="ISSN"></form:input>
	    								<form:errors path="paperISSN" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                	<label for="patentAuthors">Năm kê khai*</label>
		                                <form:select path="patentReportingAcademicDate" class="form-control" name="patentReportingAcademicDate" >
		                                	<c:forEach items="${patentReportingAcademicDateList}" var="patentDate">
		                                     <option value="${patentDate.ACAYEAR_Code}">${patentDate.ACAYEAR_Code}</option>
		                                   	</c:forEach>
		                                </form:select>
		                                <form:errors path="patentReportingAcademicDate" class="alert-danger"></form:errors>
	                            	</div>
	                                <form:hidden path="paperAutConHours" class="form-control" name="paperAutConHours" placeholder="Author Converted Hours"></form:hidden>
	                                <form:hidden path="paperJIndexCode" class="form-control" name="paperJIndexCode" placeholder="Journal Index"></form:hidden>
	                                <button type="submit" class="btn btn-primary" id="addANewPaper">Lưu</button>
	                                <!-- <button type="reset" class="btn btn-primary">Clear</button> -->
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
	                        		<%-- <div class="form-group">
	                                    <label for="paperAutConHours">Author Converted Hours*</label>
	                                    <form:hidden path="paperAutConHours" class="form-control" name="paperAutConHours" disabled="disabled" placeholder="Author Converted Hours"></form:hidden>
	    								<form:errors path="paperAutConHours" class="alert-danger"></form:errors>
	                                </div> --%>
	                                
	                                <div class="form-group">
	                                    <label for="paperYear">Năm xuất bản*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(format : YYYY)</i></label>
	                                    <form:input path="paperYear" class="form-control year" name="paperYear" readonly="true" placeholder="YYYY"></form:input>
	    								<form:errors path="paperYear" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="paperVolumn">Volumn</label>
	                                    <form:input path="paperVolumn" class="form-control" name="paperVolumn" placeholder="Volumn"></form:input>
	    								<form:errors path="paperVolumn" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="paperAuthorList">Tác giả</label>
	                                    <form:input path="paperAuthorList" class="form-control" name="paperAuthorList" placeholder="Authors"></form:input>
	    								<form:errors path="paperAuthorList" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="paperFileUpload">File bài báo*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(File size is 20 MB maximum)</i></label>
	                                    <form:input path="paperFileUpload" name="paperFileUpload" type="file" placeholder="Source File"></form:input>
	    								<form:errors path="paperFileUpload" class="alert-danger"></form:errors>
	                                </div>
	                                <!-- <div class="form-group">
	                                    <label>Journal Index*</label>
	                                    <form:select path="paperJIndexCode" disabled="disabled" class="form-control" name="paperJIndexCode">
		                                    <c:forEach items="${journalList}" var="journal">
		                                        <option value="${journal.JNAL_IndexCode}">${journal.JNAL_IndexCode}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="paperJIndexCode" class="alert-danger"></form:errors>
	                                </div>  -->
	                                <div class="form-group">
	                                    <label for="paperPubConHours">Số giờ chuyển đổi*</label>
	                                    <form:input path="paperPubConHours" class="form-control" name="paperPubConHours" disabled="true" placeholder="Publilcation Converted Hours"></form:input>
	    								<form:errors path="paperPubConHours" class="alert-danger"></form:errors>
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
var paperConvertedHours = ${paperConvertedHours};

$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/papers.html";
	});
	
	$("#addANewPaper").click(function(){
		$("#paperPubConHours").removeAttr("disabled");
	});
	
	$('.year').datepicker({
		changeMonth: false,
        changeYear: true,
        showButtonPanel: true,
        dateFormat : 'yy',
        stepMonths: 12,
		onClose: function() {
	        var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker('setDate', new Date(iYear, 1));
     	},
     	beforeShow: function() {
     		if ((selDate = $(this).val()).length > 0)
 	        {
 	          iYear = selDate.substring(selDate.length - 4, selDate.length);
 	          $(this).datepicker('option', 'defaultDate', new Date(iYear, 1));
 	          $(this).datepicker('setDate', new Date(iYear, 1));
 	        }
  	    }
  	});
	// Hide month when choose a year
	$("#paperYear").click(function () {
		$(".ui-datepicker-month").hide();
	});
	
	$("#paperYear").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $(".ui-datepicker-month").hide();
    });
	
	// Init changing country
	v_fChangeCategory();
});

function v_fChangeCategory(){
	var sCateCode = $("#paperCatCode").val();
	var iConvertedHours = 0;
	if((paperConvertedHours != undefined || paperConvertedHours != "undefined") && paperConvertedHours != ""){
		for(var key in paperConvertedHours)
		{
			if(key == sCateCode){
				iConvertedHours = paperConvertedHours[key];
			}
		}
	}
	$("#paperPubConHours").val(iConvertedHours);
}

</script>

