
// var juryhtml = '<div class="col-md-3">';
// 	juryhtml += '<div class="panel panel-primary">';
// 				juryhtml += '<div class="panel-heading juries-heading">';
// 				juryhtml += '    <h3 class="panel-title">Nguyen Anh Tan - 20092343 <span class="badge pull-right"> 1 </span></h3>';

// 				juryhtml += '</div>';
// 				juryhtml += '<div class="panel-body subject-project">';
// 				juryhtml += '	Nghiên cứu triển khai chữ ký số điện tử';
// 				juryhtml += '</div>';
// 				juryhtml += '<div class="list-group list-item-jury">';
					
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control" title="GVHD">';
// 				juryhtml += '		  <option>Chon giao vien huong dan</option>';
// 				juryhtml += '		  <option>Pham Quang Dung</option>';
// 				juryhtml += '		  <option>Nguyen Duc Nghia</option>';
// 				juryhtml += '		  <option>Huynh Thanh Binh</option>';
// 				juryhtml += '		  <option>Ta Tuan Anh</option>';
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon phan bien 1</option>';
// 				juryhtml += '		  <option>Pham Quang Dung</option>';
// 				juryhtml += '		  <option>Nguyen Duc Nghia</option>';
// 				juryhtml += '		  <option>Huynh Thanh Binh</option>';
// 				juryhtml += '		  <option>Ta Tuan Anh</option>';
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon phan bien 2</option>';
// 				juryhtml += '		  <option>Pham Quang Dung</option>';
// 				juryhtml += '		  <option>Nguyen Duc Nghia</option>';
// 				juryhtml += '		  <option>Huynh Thanh Binh</option>';
// 				juryhtml += '		  <option>Ta Tuan Anh</option>';
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon chu tich</option>';
// 				juryhtml += '		  <option>Pham Quang Dung</option>';
// 				juryhtml += '		  <option>Nguyen Duc Nghia</option>';
// 				juryhtml += '		  <option>Huynh Thanh Binh</option>';
// 				juryhtml += '		  <option>Ta Tuan Anh</option>';
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon thu ki</option>';
// 				juryhtml += '		  <option>Pham Quang Dung</option>';
// 				juryhtml += '		  <option>Nguyen Duc Nghia</option>';
// 				juryhtml += '		  <option>Huynh Thanh Binh</option>';
// 				juryhtml += '		  <option>Ta Tuan Anh</option>';
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon uy vien</option>';
// 				juryhtml += '		  <option>Pham Quang Dung</option>';
// 				juryhtml += '		  <option>Nguyen Duc Nghia</option>';
// 				juryhtml += '		  <option>Huynh Thanh Binh</option>';
// 				juryhtml += '		  <option>Ta Tuan Anh</option>';
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon kip</option>';
// 				juryhtml += '		  <option>1</option>';
// 				juryhtml += '		  <option>2</option>';
// 				juryhtml += '		  <option>3</option>';
// 				juryhtml += '		  <option>4</option>';
	  
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '	<div class="list-group-item">';
// 				juryhtml += '	    <select class="form-control">';
// 				juryhtml += '		  <option>Chon phong</option>';
// 				juryhtml += '		  <option>1</option>';
// 				juryhtml += '		  <option>2</option>';
// 				juryhtml += '		  <option>3</option>';
// 				juryhtml += '		  <option>4</option>';
	  
// 				juryhtml += '		</select>';
// 				juryhtml += '	</div>';
// 				juryhtml += '</div>';
// 				juryhtml += '<div class="panel-footer"><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></button><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-star"></span></button><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-question-sign"></span></button></div>';
// 			juryhtml += '</div>';
// 		juryhtml += '</div>';

// $("#row-juries").append(juryhtml);
// $("#row-juries").append(juryhtml);
// $("#row-juries").append(juryhtml);
// juryhtml='<div class="row">'+juryhtml+juryhtml+juryhtml+juryhtml+'</div>';
// $("#detail-juries").append(juryhtml);


$(".juries-heading").click(function(){
  $(this).siblings(".list-item-jury").slideToggle(300);
});

$("#btn-view-detail").click(function(){
	$(".list-item-jury").slideDown();
});
$("#btn-view-less").click(function(){
	$(".list-item-jury").slideUp();
});

$('#backToTopBtn').click(function(){
    $('html,body').animate({scrollTop:0},'slow');return false;
});