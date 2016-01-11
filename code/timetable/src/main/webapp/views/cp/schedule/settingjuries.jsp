<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">
<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
  <style>
  .custom-combobox {
    position: relative;
    display: inline-block;
  }
  .custom-combobox-toggle {
    position: absolute;
    top: 0;
    bottom: 0;
    margin-left: -1px;
    padding: 0;
  }
  .custom-combobox-input {
    margin: 0;
    padding: 5px 10px;
  }
  </style>
<div id="${(disableHeader != null) ? 'page-wrapper-no-layout' : 'page-wrapper'}">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Sắp xếp hội đồng phản biện</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/cp/save-defenses.html" method="POST" commandName="juriesForm" role="form" accept-charset="UTF-8">
		<div class="row">
		<c:if test="${status != null}">	              
		<div class="alert alert-success">
			${status}
		</div>
		</c:if>
		</div>
		<!-- /.row -->
		
		<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<table class="table table-striped table-bordered table-hover" id="dataTables-Juries">
							<thead>
								<tr>
									<th title="Name">Học viên</th>
									<th title="Department">Đề tài</th>
									<th title="Faculty">GV Hướng dẫn</th>
									<th title="Faculty">Phản biện 1</th>
									<th title="Faculty">Phản biện 2</th>
									<th title="Faculty">Chủ tịch</th>
									<th title="Faculty">Thư ký</th>
									<th title="Faculty">Ủy viên</th>
									<th title="Faculty">Thời gian</th>
									<th title="Faculty">Địa điểm</th>
									<th title="Faculty">HĐ</th>
									<th title="Faculty">Lớp-khóa</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listMasterDefenseJuryThesis != null}">
									<c:forEach items="${listMasterDefenseJuryThesis}" var="aMasterDefenseJuryThesis">															
										<tr class="gradeX">
										 	<td><c:out value="${aMasterDefenseJuryThesis.studentName}"/></td>
										 	<td><c:out value="${aMasterDefenseJuryThesis.thesisName}"/></td>
										 	<td><c:out value="${aMasterDefenseJuryThesis.mentorName}"/></td>
										 	<td><div id="val-defenseder01-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.examiner1Name}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.examiner1Code}" name="defenseder01" /> </div><span onclick="editJury('dialog-form-master', 'defenseder01-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-defenseder02-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.examiner2Name}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.examiner2Code}" name="defenseder02" /> </div> <span onclick="editJury('dialog-form-master', 'defenseder02-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-president-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.presidentName}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.presidentCode}" name="president" /> </div> <span onclick="editJury('dialog-form-master', 'president-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-secretary-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.secretaryName}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.secretaryCode}" name="secretary" /> </div> <span onclick="editJury('dialog-form-master', 'secretary-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-commissioner-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.externalMemberName}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.externalMemberCode}" name="commissioner" /> </div> <span onclick="editJury('dialog-form-master', 'commissioner-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-slot-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.theTime}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.theTime}" name="slot" /> </div> <span onclick="editJury('dialog-form-info', 'slot-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-room-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.roomPlaceCode}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.roomPlaceCode}" name="room" /> </div> <span onclick="editJury('dialog-form-room', 'room-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-no-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.coucilNo}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.coucilNo}" name="no" /> </div> <span onclick="editJury('dialog-form-info', 'no-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-sclass-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.classCode}"/></td>
										 	<div class="defense-values">
										 		<input id="masterDefenseThesis" name="masterDefenseThesis" value="${aMasterDefenseJuryThesis.masterDefenseJuryCode}" type="hidden" />
										 	</div>
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
				<button type="submit" class="btn btn-primary" id="add">Lưu</button>
				<button type="button" class='form btn btn-primary' id="cancel" >Quay về</button>				
			</div>
			<!-- /.col-lg-4 -->
		</div>
		
		<!-- /.row -->
	</form:form>
</div>

<div class="hide">
	<div id="dialog-form-master"  title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <select name="master-name" id="master-name" class="form-control">
			  	<c:if test="${listStaffs != null}">
	               	<c:forEach items="${listStaffs}" var="staff">
	               		<option value="${staff.staff_Code}" >${staff.staff_Name}</option>
	               	</c:forEach>
                </c:if>
			  </select>
		 </div>
	  </form>
	</div>
	
	<div id="dialog-form-room" title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <select name="room-add" id="room-add" class="form-control">
				  	<c:if test="${listRooms != null}">
		            	<c:forEach items="${listRooms}" var="room">
		            		<option value="${room.r_Code}" >${room.r_Code}</option>
		            	</c:forEach>
	            	</c:if>
			  </select>
		 </div>
	  </form>
	</div>
	
	<div id="dialog-form-info" title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <input name="jury-info" id="jury-info" class="form-control" />
		 </div>
	  </form>
	</div>
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
</script>

<!-- Dialog input value  --> 
<script type="text/javascript">
	
function editJury(the_sz_TypeForm, the_sz_Id) {
				  	var sz_currentElement = '';
				  	var sz_NameInputChanged = '';
				    var cDialog, form,
					    name = $( "#master-name" ),
					    allFields = $( [] ).add( name ),
					    tips = $( ".validateTips" );
				    var nameVal = '', 
				    	nameTitle = '';
				    function addUser() {
				    	switch (the_sz_TypeForm){
					    	case 'dialog-form-master':
					    		nameVal= $("select#master-name option:selected").val();
							    nameTitle = $("select#master-name option:selected").text();
							    break;
					    	case 'dialog-form-room':
					    		nameVal= $("select#room-add option:selected").val();
							    nameTitle = $("select#room-add option:selected").text();
							    break;
					    	case 'dialog-form-info' :
					    		nameVal= $("input#jury-info").val();
					    		nameTitle= $("input#jury-info").val();
					    		break;
					    	default :
					    		nameVal= $("input#jury-info").val();
				    			nameTitle= $("input#jury-info").val();
				    	}
				      
				      $( sz_currentElement ).html("");
				      if ( nameVal != "" ) {
				        $( sz_currentElement ).append(nameTitle + "<input name='"+sz_NameInputChanged+"' type='hidden' value='"+nameVal+"' />");
				        cDialog.dialog( "close" );
				      }
				      return true;
				    }
				 	
				    cDialog = $( "#"+the_sz_TypeForm ).dialog({
				      autoOpen: false,
				      height: 170,
				      width: 350,
				      modal: true,
				      buttons: {
				        "Sửa": addUser,
				        "Hủy": function() {
				            cDialog.dialog( "close" );
				        }
				      },
				      close: function() {
				        form[ 0 ].reset();
				        allFields.removeClass( "ui-state-error" );
				      }
				    });
				 
				    form = cDialog.find( "form" ).on( "submit", function( event ) {
				      event.preventDefault();
				      addUser();
				    });
				
				    
				      if(the_sz_Id != "")
				   	  {
				    	  sz_currentElement = "#val-"+the_sz_Id;
				    	  a_currentElements = the_sz_Id.split("-");
				    	  sz_NameInputChanged = a_currentElements[0];
				   	  }
				      cDialog.dialog( "open" );
				}

</script>
