const optionHidePageLengthAndSearch = {
	"bFilter" : false,
	"bLengthChange" : false
}

toastr.options = {
		  "closeButton": false,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": true,
		  "positionClass": "toast-top-center",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
};

$(document).ready(function () {
	var frmMessageValue = $("#frmMessage").val();
	if(frmMessageValue != null && typeof frmMessageValue != "undefined" && $.trim(frmMessageValue) !== "") {
		var messageArr = frmMessageValue.split("|");
		if(messageArr && messageArr.length == 2) {
			toastr[messageArr[0]](messageArr[1]);
		}
	}
	
	  $(".result-table").DataTable();
	 
	 $(".result-table-without-pagelength-search").DataTable(optionHidePageLengthAndSearch);
	 
	 $("div[id$='popup']").prependTo("body");
	 
	 $("input").attr({"autocomplete": "off", "autocapitalize" : "off", "spellcheck" : "false", "autocorrect" : "off"});
	 
	 $(".decimal-input").on("keypress keyup blur",function (event) {
		 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	 
	 $(".integer-input").on("keypress keyup blur",function (event) {    
         $(this).val($(this).val().replace(/[^\d].+/, ""));
          if ((event.which < 48 || event.which > 57)) {
              event.preventDefault();
          }
      });
	 
	 $(".date-picker").attr({"data-provide" : "datepicker", "data-date-autoclose" : "true", "data-date-format" : "dd/mm/yyyy"});
	 $(".month-picker").attr(
		{
			"data-provide" : "datepicker",
			 "data-date-autoclose" : "true",
			 "data-date-format" : "mm/yyyy",
			 changeMonth: true,
            changeYear: true,
		});
		
	 $(".time-picker").timepicker({
		 "timeFormat" : "g:i A"
	 });
	 
	 $(".file-upload").change(function() {
		$(this).parent().closest("div").find(".upload-file-name").val($(this).val().split('\\').pop());
	 });
	 
	 if($('.img-popup').length) {
		 $('.img-popup').on('click', function() {
			 if($(this).attr('src') != null && $(this).attr('src') !== '') {
				 var imgModal = '<div class="modal fade" id="image-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
				 imgModal += '<div class="modal-dialog" role="document">';
				 imgModal += '<div class="modal-content">';
				 imgModal += '<div class="modal-header">';
				 if($(this).data('title') != null && $(this).data('title') !== '') {
					 imgModal += '<h5 class="modal-title col-md-6" id="exampleModalLongTitle">' + $(this).data('title') + '</h5>';
				 }
				 imgModal += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
				 imgModal += '</div>';
				 imgModal += '<div class="modal-body">';
				 imgModal += '<img src="' + $(this).attr('src') + '" class="imagepreview" style="width: 100%;" >';
				 imgModal += '</div>';
				 imgModal += '</div>';
				 imgModal += '</div>';
				 imgModal += '</div>';
				 $('body').append(imgModal);
				 $('#image-modal').modal('show');
			 }
		 });
	 }
	      
	 $(document).on('hidden.bs.modal', '#image-modal', function (e) {
		 if($('#image-modal').length) {
			 $('#image-modal').remove();
		 }
	 })
	        
});