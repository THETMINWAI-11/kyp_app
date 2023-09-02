$(document).ready(function() {

	var tncAnswerRow = function() {
		var count = $('#tbody-tnc-answer-list > tr').length;
		return '<tr>'
			+ '<td style="display : none;"><input type="hidden" name="answerList['+count+'].answerId"/></td>'
			+ '<td style="display : none;"><input type="hidden" name="answerList['+count+'].createdTime"/></td>'
			+ '<td style="vertical-align: middle; text-align: center;">'+(count + 1 )+'</td>'
			+ '<td><input name="answerList['+count+'].answer" type="text" class="form-control" required="required"  autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"/></td>'
			+ '<td>'
			+ '<select name="answerList['+count+'].status" class="custom-select" required="required">'
			+ $(document).find('#status-list-id').html()
			+ '</select>'
			+ '</td>'
			+ '<td><a class="btn btn-sm btn-primary btn-delete-tnc-answer"><i class="icon icon-times"></i></a></td>'
			+ '</tr>';
	};
	
	var reorderTcnAnswerTable = function() {
		if($('#tbody-tnc-answer-list').length){
			var i = 1;
			$('#tbody-tnc-answer-list > tr').each(function(i, row) {
				$(row).find("td").each(function(j, td) {
					switch (j) {
					case (0):
						$(td).children().attr("name", "answerList["+i+"].answerId");
						break;
					case (1):
						$(td).children().attr("name", "answerList["+i+"].createdTime");
						break;
					case (2):
						$(td).html(i + 1);
						break;
					case (3): 
						$(td).children().attr("name", "answerList["+i+"].answer");
						break;
					case (4):
						$(td).children().attr("name", "answerList["+i+"].status");
						break;
					}
				});
			});
		};
	};
	
	$('#btn-save-booking-rate').on('click', function() {
		if($('#booking-rate-confirm-popup').length){
			$('#booking-rate-confirm-popup').modal('show');
		}
	});
	
	$('#btn-confirm-booking-rate').on('click', function() {
		if($('#frm-booking-rate').length) {
			$('#frm-booking-rate').submit();
		}
	});
	
	if($('#btn-system-setting-update').length) {
		$('#btn-system-setting-update').on('click', function() {
			if($('#system-setting-update-confirm-popup').length){
				$('#system-setting-update-confirm-popup').modal('show');
			}
		});
	}
	
	if($('#btn-confirm-system-setting-update').length) {
		$('#btn-confirm-system-setting-update').on('click', function() {
			if($('#frm-system-setting-update').length) {
				$('#frm-system-setting-update').submit();
			}
		});
	}
	
	if($('#btn-add-booking-rate').length) {
		$('#btn-add-booking-rate').on('click', function() {
			if($('#tbody-booking-rate-list').length) {
				$('#tbody-booking-rate-list').append(bookingRateRow());
				 $(".decimal-input").on("keypress keyup blur",function (event) {
					 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
			         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
			             event.preventDefault();
			         }
			     });
			}
		});
	}
	
	var bookingRateRow = function() {
		var count = $('#tbody-booking-rate-list > tr').length;
		return '<tr>'
			+ '<td style="vertical-align: middle; text-align: center;">'+(count + 1)+'</td>'
			+ '<td><input name="bookingRates['+count+'].fromKm" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
			+ '<td><input name="bookingRates['+count+'].toKm" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
			+ '<td><input name="bookingRates['+count+'].rate" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
			+ '<td><input name="bookingRates['+count+'].peakRate" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
			+ '<td><a class="btn btn-sm btn-primary btn-delete-booking-rate"><i class="icon icon-times"></i></a></td>'
			+ '</tr>';
	};
	
	$(document).on('click', '.btn-delete-booking-rate', function() {
		$(this).closest('tr').remove();
		reorderBookingRateTable();
	});
	
	var reorderBookingRateTable = function() {
		if($('#tbody-booking-rate-list').length){
			var i = 1;
			$('#tbody-booking-rate-list > tr').each(function(i, row) {
				$(row).find("td").each(function(j, td) {
					switch (j) {
					case (0):
						$(td).html(i + 1);
						break;
					case (1): 
						$(td).children().attr("name", "bookingRates["+i+"].fromKm");
						break;
					case (2):
						$(td).children().attr("name", "bookingRates["+i+"].toKm");
						break;
					case (3):
						$(td).children().attr("name", "bookingRates["+i+"].rate");
						break;
					case (4):
						$(td).children().attr("name", "bookingRates["+i+"].peakRate");
						break;
					}
				});
			});
		};
	};
	if($('#menu-panel').length) {
		
		$('input[type="checkbox"][data-is-parent=1]').on('click', function() {
			clickParentChkbox(this);
		});
		
		$('input[type="checkbox"][data-is-parent=2]').on('click', function() {
			clickChildChkbox(this);
		});
		
		
		var clickParentChkbox = function(e) {
			var id = $(e).data('id');
			var chk = $(e).prop('checked');
			$('input[type="checkbox"][data-parent-menu-id="'+id+'"]').prop('checked', chk).change();
		}
		
		var clickChildChkbox = function(e) {
			var id = $(e).data('id');
			var pid = $(e).data('parent-menu-id');
			var chk = $(e).prop('checked');
			if(chk) {
				$('input[type="checkbox"][data-id="'+pid+'"]').prop('checked', true).change();
			} else {
				if($('input[type="checkbox"][data-parent-menu-id="'+pid+'"]:checked').length == 0) {
					$('input[type="checkbox"][data-id="'+pid+'"][data-is-parent="1"]').prop('checked', false).change();
				}
			}
		}
		
	}
	
	if($('#id-select-role-for-menu').length) {
		$('#id-select-role-for-menu').on('change', function() {
			if($('#id-select-role-for-menu').val()){
				$.ajax({
				    type : "POST",
				    contentType: 'application/json',
				    url : "get-menu-by-role.json?roleId=" + $('#id-select-role-for-menu').val(),
				    dataType: 'json',
			        async: true,
				    success: function(data){
				    	if(data.menus) {
				    		for(let menu of data.menus) {
				    			$('#menu-panel input[type="checkbox"]').each(function() {
				    				if(menu.id == $(this).data('id')) {
				    					if(menu.authorized) {
				    						$(this).prop('checked', true);
				    					} else {
				    						$(this).prop('checked', false);
				    					}
				    					return false;
				    				}
				    			});
				    		}
				    	}
			        }
				});
			}
		});
	}
	if($('#id-hid-menu-json').length && $('#id-hid-menu-json').val() && $('#left-menu-ul').length) {
		let menuList = JSON.parse($('#id-hid-menu-json').val());
		var menuString = '';
		
		for(let menu of menuList) {
			if(menu && menu.id && menu.isParent == 1) {
				menuString += '<li class="sidenav-item has-subnav">';
				menuString += '<a href="javascript:;" aria-haspopup="true">';
				menuString += '<span class="sidenav-icon icon ' + menu.icon + '"></span>';
				menuString += '<span class="sidenav-label">' + menu.name + '</span>';
				menuString += '</a>';
				menuString += '<ul class="sidenav level-2 collapse">';
				menuString += '<li class="sidenav-heading">' + menu.name + '</li>';
				
				let childMenuList = menuList.filter(function(m) {return m.parentMenuId == menu.id});
				if(childMenuList) {
					for(let child of childMenuList) {
						menuString += '<li><a href="' + child.url + '">' + child.name + '</a></li>';
					}
				}
				
				menuString += '</ul>';
				menuString += '</li>';
			}
		}
		$('#left-menu-ul').html('');
		$('#left-menu-ul').html(menuString);
		
		var sliding = false;
		$('li.sidenav-item.has-subnav').on('click dblclick', function(e) {
			if(!sliding){
				sliding = true;
				$(this).toggleClass('active open');
				$(this).siblings('li.active.open').each(function() {
					$(this).removeClass('active open');
					$(this).find('> ul.sidenav.collapse').slideToggle(300);
				});
				$(this).find('> ul.sidenav.collapse').slideToggle(300, function() {
					sliding = false;
				});
			}
		});
	}
	
	if($('.print-this').length && $('#btn-print-driver-qr').length) {
		$('#btn-print-driver-qr').on('click', function() {
			$('.print-this').clone().appendTo('body');
			$('.print-this:last').show();
			$('.print-this:last').printThis();
			$('.print-this:last').remove();
		});
	}
	
	if($('.driver-select-chk-to-delete').length) {
		let driverTable = $(".result-table").DataTable();
		$(document).on('change', '.driver-select-chk-to-delete', function() {
			let idList = [];
			driverTable.rows().every(function(index, element) {
			   var row = $(this.node());
			   var chk = row.find('td').eq(0).find('input[type="checkbox"][class="driver-select-chk-to-delete"]');
			   if(chk && chk.prop('checked')) {
				   idList.push(chk.data('driver-id'));
			   }
			 });
		});
		
		if($('#btn-delete-driver-list').length && $('#btn-delete-driver-list').is(":visible")) {
			$('#btn-delete-driver-list').on('click', function() {
				let driverTable = $(".result-table").DataTable();
				let idList = [];
				driverTable.rows().every(function(index, element) {
				   var row = $(this.node());
				   var chk = row.find('td').eq(0).find('input[type="checkbox"][class="driver-select-chk-to-delete"]');
				   if(chk && chk.prop('checked')) {
					   idList.push(chk.data('driver-id'));
				   }
				 });
				
				if(idList && idList.length) {
					$('#hid-driver-id-list').val(idList);
					$('#driver-list-delete-popup').modal('show');
					console.log($('#btn-hidden-delete-driver-list').length);
					console.log($('#btn-confirm-driver-list-delete').is(":visible"));
					if($('#btn-hidden-delete-driver-list').length) {
						$('#btn-confirm-driver-list-delete').on('click', function() {
							console.log('Click works');
							$('#btn-hidden-delete-driver-list').click();
						});
					}
				}
			});
		}
	}
	
	// Insurance Replacement
	
	if($('#insurance-replacement-form').length) {
		
		let prepareDriverSearchCriteria = function(isFromDriver) {
			
			const prefixSelector = isFromDriver ? 'from' : 'to';
			
			let searchCriteria = {};
			let vehicleCriteria = {};
			
			if(!isFromDriver && $('#insurnce-replacement-from-driver-id').length && $('#insurnce-replacement-from-driver-id').val()) { // If from driver is selected, exclude this from driver in to driver list
				searchCriteria.driverId = $('#insurnce-replacement-from-driver-id').val();
			}
			
			if($('#'+prefixSelector+'-driver-driverId').length) {
				searchCriteria.driverDisplayId = $('#'+prefixSelector+'-driver-driverId').val();
			}
			if($('#'+prefixSelector+'-driver-name').length) {
				searchCriteria.name = $('#'+prefixSelector+'-driver-name').val();
			}
			if($('#'+prefixSelector+'-driver-phone').length) {
				searchCriteria.phoneNo = $('#'+prefixSelector+'-driver-phone').val();
			}
			if($('#'+prefixSelector+'-driver-nrc').length) {
				searchCriteria.nrc = $('#'+prefixSelector+'-driver-nrc').val();
			}
			if($('#'+prefixSelector+'-driver-vehicle-prefix').length) {
				vehicleCriteria.vehicleNoPreFix = $('#'+prefixSelector+'-driver-vehicle-prefix').val();
			}
			if($('#'+prefixSelector+'-driver-vehicle-number').length) {
				vehicleCriteria.vehicleNo = $('#'+prefixSelector+'-driver-vehicle-number').val();
			}
			searchCriteria.vehicle = vehicleCriteria;
			console.log(prefixSelector, searchCriteria);
			return searchCriteria;
		};
		
		let prepareDriverRows = function (list, isFromDriver) {
			const prefixSelector = isFromDriver ? 'from' : 'to';
			if(list && list.length > 0) {
				let row = '';
				let i = 1;
				for(let d of list) {
					
					d.driverDisplayId = d.driverDisplayId ? d.driverDisplayId : '';
					d.name = d.name ? d.name : '';
					d.phoneNo = d.phoneNo ? d.phoneNo : '';
					d.nrc = d.nrc ? d.nrc : '';
					d.registrationTime = d.registrationTime ? d.registrationTime : '';
					d.approveTime = d.approveTime ? d.approveTime : '';
					d.ownerTypeDesc = d.ownerTypeDesc ? d.ownerTypeDesc : '';
					d.shiftTypeDesc = d.shiftTypeDesc ? d.shiftTypeDesc : '';
					d.averageRating = d.averageRating ? d.averageRating : '';
					d.remainingCredit = d.remainingCredit ? d.remainingCredit : 0;
					d.totalRewardPoint = d.totalRewardPoint ? d.totalRewardPoint : 0;
					if(d.vehicle) {
						d.vehicle.vehicleNoPreFix = d.vehicle.vehicleNoPreFix ? d.vehicle.vehicleNoPreFix : '';
						d.vehicle.vehicleNo = d.vehicle.vehicleNo ? d.vehicle.vehicleNo : '';
					} else {
						d.vehicle.vehicleNoPreFix = '';
						d.vehicle.vehicleNo = '';
					}
					
					d.vehicleNo = d.vehicle.vehicleNoPreFix ? d.vehicle.vehicleNoPreFix + '/' + d.vehicle.vehicleNo : d.vehicle.vehicleNo;
					
					row += '<tr>';
					row += '<td><input name="'+prefixSelector+'-driver-radio" type="radio" data-driver-id='+d.driverId+'></td>';
					row += '<td>'+i+'</td>';
					row += '<td>'+d.driverDisplayId+'</td>';
					row += '<td>'+d.name+'</td>';
					row += '<td>'+d.phoneNo+'</td>';
					row += '<td>'+d.nrc+'</td>';
					row += '<td>'+d.vehicleNo+'</td>';
					row += '<td>'+d.registrationTime+'</td>';
					row += '<td>'+d.approveTime+'</td>';
					row += '<td>'+d.ownerTypeDesc+'</td>';
					row += '<td>'+d.shiftTypeDesc+'</td>';
					row += '<td>'+d.averageRating+'</td>';
					row += '<td>'+d.remainingCredit+'</td>';
					row += '<td>'+d.totalRewardPoint+'</td>';
					row += '</tr>';
					i++;
				}
				return row;
			}
		}
		
		if($('#from-driver-search-button').length && $('#from-driver-result-list').length) {
			$('#from-driver-search-button').on('click', function() {
				
				if($('#insurnce-replacement-from-driver-id').length) { // Reset previous selected From driver, when Search button click
					$('#insurnce-replacement-from-driver-id').val(null);
				}
				
				$('#from-driver-result-list').html('');
				$.ajax({
				    type : 'POST',
				    contentType: 'application/json',
				    url : 'search-from-driver-for-insurance-replacement.json',
				    dataType: 'json',
				    data: JSON.stringify(prepareDriverSearchCriteria(true)),
			        async: true,
				    success: function(data){
				    	if(data && data.length) {
				    		let dt = $('#from-driver-result-list').closest('table').DataTable();
				    		dt.destroy();
				    		$('#from-driver-result-list').html(prepareDriverRows(data, true));
				    		$('#from-driver-result-list').closest('table').DataTable(optionHidePageLengthAndSearch);
				    	}
			        }
				});
			});
		}
		
		if($('#to-driver-search-button').length && $('#to-driver-result-list').length) {
			$('#to-driver-search-button').on('click', function() {
				
				if($('#insurnce-replacement-to-driver-id').length) {  // Reset previous selected To driver, when Search button click
					$('#insurnce-replacement-to-driver-id').val(null);
				}
				
				$('#to-driver-result-list').html('');
				$.ajax({
				    type : 'POST',
				    contentType: 'application/json',
				    url : 'search-to-driver-for-insurance-replacement.json',
				    dataType: 'json',
				    data: JSON.stringify(prepareDriverSearchCriteria(false)),
			        async: true,
				    success: function(data){
				    	if(data && data.length) {
				    		let dt = $('#to-driver-result-list').closest('table').DataTable();
				    		dt.destroy();
				    		$('#to-driver-result-list').html(prepareDriverRows(data, false));
				    		$('#to-driver-result-list').closest('table').DataTable(optionHidePageLengthAndSearch);
				    	}
			        }
				});
			});
		}
		
		$(document).on('click', 'input[type="radio"][name="from-driver-radio"]', function() {
			if($(this).data('driver-id')) {
				$('#insurnce-replacement-from-driver-id').val($(this).data('driver-id'));
			}
		});
		
		$(document).on('click', 'input[type="radio"][name="to-driver-radio"]', function() {
			if($(this).data('driver-id')) {
				$('#insurnce-replacement-to-driver-id').val($(this).data('driver-id'));
			}
		});
		
		if($('#btn-replace-insurance').length) {
			$('#btn-replace-insurance').on('click', function() {
				if(!$('#insurnce-replacement-from-driver-id').val()) {
					toastr['error']('Please select From driver.');
					return;
				}
				if(!$('#insurnce-replacement-to-driver-id').val()) {
					toastr['error']('Please select To driver.');
					return;
				}
				if($('#insurnce-replacement-from-driver-id').val() == $('#insurnce-replacement-to-driver-id').val()) {
					toastr['error']('From driver and To driver should not be same.');
					return;
				}
				$('#insurance-replacement-form').submit();
			});
		}
		
	}
	
	// Driver edit (Insurance Replacement)
	if($('form[id="driverDto"]').length && $('select[id="approveStatus"]').length) {
		$('form[id="driverDto"] select[id="approveStatus"]').on('change', function() {
			if($('#insurance-replacement-option').length) {
				if($('form[id="driverDto"] select[id="approveStatus"]').val() && $('form[id="driverDto"] select[id="approveStatus"]').val() == 2) { // Approve
					$('#insurance-replacement-option').show();
				} else {
					$('#insurance-replacement-option').hide();
				}
			}
		});
		
		$('form[id="driverDto"] select[id="approveStatus"]').change();
		
		if($('#chk-insurance-replacement-option').length && $('#chk-insurance-new-option').length) {
			
			$('#chk-insurance-new-option').on('click', function() {
				if($('#chk-insurance-new-option').is(':checked')) {
					$('#chk-insurance-replacement-option').prop('checked', false);
					$('#btn-open-driver-search-popup').hide();
					$('#hidden-insurance-replacement-option-value').val(1);
					$('#insurance-option-hid').val(1); 
				} else {
					$('#hidden-insurance-replacement-option-value').val(null); 
				}
			});
			
			$('#chk-insurance-replacement-option').on('click', function() {
				if($('#chk-insurance-replacement-option').is(':checked')) {
					$('#btn-open-driver-search-popup').show();
					$('#chk-insurance-new-option').prop('checked', false);
					$('#hidden-insurance-replacement-option-value').val(2); 
					$('#insurance-option-hid').val(2); 
				} else {
					$('#btn-open-driver-search-popup').hide();
					$('#hidden-insurance-replacement-option-value').val(null); 
				}
			});
		}
		if($('#chk-insurance-new-option').is(':checked')) {
			console.log("enter new check....");
			$('#hidden-insurance-replacement-option-value').val(1); 
		}
		if($('#chk-insurance-replacement-option').is(':checked')) {
			console.log("enter replace check....");
			$('#hidden-insurance-replacement-option-value').val(2); 
			$('#btn-open-driver-search-popup').show();
		}
		
		if($('#replace-insurance-driver-search-button').length) {
			
			let prepareSearchCriteriaForInsuranceReplacementPopup = function() {
				
				let searchCriteria = {};
				let vehicleCriteria = {};
				
				if($('#replace-insurance-driver-id').length) {
					searchCriteria.driverDisplayId = $('#replace-insurance-driver-id').val();
				}
				if($('#replace-insurance-driver-name').length) {
					searchCriteria.name = $('#replace-insurance-driver-name').val();
				}
				if($('#replace-insurance-driver-phone').length) {
					searchCriteria.phoneNo = $('#replace-insurance-driver-phone').val();
				}
				if($('#replace-insurance-driver-nrc').length) {
					searchCriteria.nrc = $('#replace-insurance-driver-nrc').val();
				}
				if($('#replace-insurance-driver-vehicle-prefix').length) {
					vehicleCriteria.vehicleNoPreFix = $('#replace-insurance-driver-vehicle-prefix').val();
				}
				if($('#replace-insurance-driver-vehicle-number').length) {
					vehicleCriteria.vehicleNo = $('#replace-insurance-driver-vehicle-number').val();
				}
				searchCriteria.vehicle = vehicleCriteria;
				console.log(searchCriteria);
				return searchCriteria;
			};
			
			let prepareDriverRowsForInsuranceReplacementPopup = function (list) {
				console.log("list length..."+list.length);
				if(list && list.length > 0) {
					let row = '';
					let i = 1;
					for(let d of list) {
						
						d.driverDisplayId = d.driverDisplayId ? d.driverDisplayId : '';
						d.name = d.name ? d.name : '';
						d.phoneNo = d.phoneNo ? d.phoneNo : '';
						d.nrc = d.nrc ? d.nrc : '';
						d.registrationTime = d.registrationTime ? d.registrationTime : '';
						d.approveTime = d.approveTime ? d.approveTime : '';
						d.ownerTypeDesc = d.ownerTypeDesc ? d.ownerTypeDesc : '';
						d.shiftTypeDesc = d.shiftTypeDesc ? d.shiftTypeDesc : '';
						d.averageRating = d.averageRating ? d.averageRating : '';
						d.remainingCredit = d.remainingCredit ? d.remainingCredit : 0;
						d.totalRewardPoint = d.totalRewardPoint ? d.totalRewardPoint : 0;
						if(d.vehicle) {
							d.vehicle.vehicleNoPreFix = d.vehicle.vehicleNoPreFix ? d.vehicle.vehicleNoPreFix : '';
							d.vehicle.vehicleNo = d.vehicle.vehicleNo ? d.vehicle.vehicleNo : '';
						} else {
							d.vehicle.vehicleNoPreFix = '';
							d.vehicle.vehicleNo = '';
						}
						
						d.vehicleNo = d.vehicle.vehicleNoPreFix ? d.vehicle.vehicleNoPreFix + '/' + d.vehicle.vehicleNo : d.vehicle.vehicleNo;
						
						row += '<tr>';
						row += '<td><input name="replace-insurance-driver-select-radio" type="radio" data-driver-id='+d.driverId+'></td>';
						row += '<td>'+i+'</td>';
						row += '<td>'+d.driverDisplayId+'</td>';
						row += '<td>'+d.name+'</td>';
						row += '<td>'+d.phoneNo+'</td>';
						row += '<td>'+d.nrc+'</td>';
						row += '<td>'+d.vehicleNo+'</td>';
						row += '<td>'+d.registrationTime+'</td>';
						row += '<td>'+d.approveTime+'</td>';
						row += '<td>'+d.ownerTypeDesc+'</td>';
						row += '<td>'+d.shiftTypeDesc+'</td>';
						row += '<td>'+d.averageRating+'</td>';
						row += '<td>'+d.remainingCredit+'</td>';
						row += '<td>'+d.totalRewardPoint+'</td>';
						row += '</tr>';
						i++;
					}
					return row;
				}
				}
			
			if($('#replace-insurance-driver-search-button').length && $('#replace-insurance-driver-result-list').length) {
				$('#replace-insurance-driver-search-button').on('click', function() {
					
					if($('#insurnce-replacement-from-driver-id').length) { // Reset previous selected From driver, when Search button click
						$('#insurnce-replacement-from-driver-id').val(null);
					}
					
					$('#replace-insurance-driver-result-list').html('');
					$.ajax({
					    type : 'POST',
					    contentType: 'application/json',
					    url : 'search-from-driver-for-insurance-replacement.json',
					    dataType: 'json',
					    data: JSON.stringify(prepareSearchCriteriaForInsuranceReplacementPopup()),
				        async: true,
					    success: function(data){
					    	if(data && data.length) {
					    		let dt = $('#replace-insurance-driver-result-list').closest('table').DataTable();
					    		dt.destroy();
					    		$('#replace-insurance-driver-result-list').html(prepareDriverRowsForInsuranceReplacementPopup(data));
					    		$('#replace-insurance-driver-result-list').closest('table').DataTable(optionHidePageLengthAndSearch);
					    	}else{
					    		console.log("enter no data.....");
								$('#replace-insurance-driver-result-list').html('');
								 $('#replace-insurance-driver-result-list').closest('table').dataTable().fnClearTable();
								$('#replace-insurance-driver-result-list').closest('table').dataTable().fnDestroy();
								$('#replace-insurance-driver-result-list').closest('table').DataTable();
					    		let row2 = '<tr class="odd"><td valign="top" colspan="14" class="dataTables_empty">No data available in table</td></tr>';
					    		$('#replace-insurance-driver-result-list').html(row2);
				        	}
				        }
					});
				});
			}
			
			$(document).on('click', '#btn-select-driver-for-insurance-replacement', function() {
				console.log('Select click works!');
				if($('input[type="radio"][name="replace-insurance-driver-select-radio"]:checked').length) {
					if(!$('input[type="radio"][name="replace-insurance-driver-select-radio"]:checked').data('driver-id')) {
						toastr['error']('Invalid driver id.');
						return;
					}
					if(!$('#hidden-from-driverId-for-insurance-replacement').length || !$('#hidden-insurance-replacement-option-value').length) {
						toastr['error']('Driver selection failed.');
						return;
					}
					$('#hidden-from-driverId-for-insurance-replacement').val($('input[type="radio"][name="replace-insurance-driver-select-radio"]:checked').data('driver-id'));
					$('#hidden-insurance-replacement-option-value').val(2); // set replacement insurance option value
					$('#insurance-replacement-popup').modal('hide');
				} else {
					toastr['error']('Please select one driver to replace insurance.');
				}
				
			});
			
		}
	}
	
	// Validate driver's phone and nrc in driver register from admin portal
	 if($('#validate-driver-phone-input').length) {
		 $('#validate-driver-phone-input').on('blur', function() {
			 $.ajax({
			    type : 'POST',
			    contentType: 'application/json',
			    url : 'check-driver-phone.json',
			    dataType: 'text',
			    data: JSON.stringify({driverId: null, phoneNo: $('#validate-driver-phone-input').val()}),
		        async: true,
			    success: function(errMsg){
			    	if(errMsg) {
			    		toastr['error'](errMsg);
			    	}
		        }
			});
		 });
	 }
	 
	 if($('#validate-driver-nrc-input').length) {
		 $('#validate-driver-nrc-input').on('blur', function() {$.ajax({
			    type : 'POST',
			    contentType: 'application/json',
			    url : 'check-driver-nrc.json',
			    dataType: 'text',
			    data: JSON.stringify({driverId: null, nrc: $('#validate-driver-nrc-input').val()}),
		        async: true,
			    success: function(errMsg){
			    	if(errMsg) {
			    		toastr['error'](errMsg);
			    	}
		        }
			});
		 });
	 }
	
	// CK Editor in notification message
	if($('#ckeditor-noti-message').length && $('textarea[name="notificationMessage"]').length) {
		CKEDITOR.replace('notificationMessage');
	}
	
	 if($("#payment-select").val() == 1){
			$("#prepaid-div").attr("style", "display:block");
			$("#postpaid-div").attr("style", "display:none");
		 } else if($("#payment-select").val() == 2){
			$("#prepaid-div").attr("style", "display:none");
			$("#postpaid-div").attr("style", "display:block");
		}
	 
	 $("#payment-select").on("change", function() {
		 var paymentType=$(this).val();
		 if(paymentType == 1){
				$("#prepaid-div").attr("style", "display:block");
				$("#postpaid-div").attr("style", "display:none");
			 } else if(paymentType == 2){
				$("#prepaid-div").attr("style", "display:none");
				$("#postpaid-div").attr("style", "display:block");
			}
	 });
	 
	 $(".decimal-input").on("keypress keyup blur",function (event) {
		 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
	 
	 $(".number-input").on("keypress keyup blur",function (event) {    
         $(this).val($(this).val().replace(/[^\d].+/, ""));
          if ((event.which < 48 || event.which > 57)) {
              event.preventDefault();
          }
      });
      
     $(".phoneno-input").on("keypress keyup blur",function (event) {    
         $(this).val($(this).val().replace(/[^\+\d].+/, ""));
           if (event.which != 43 && event.which > 31 && (event.which < 48 || event.which > 57)){
              event.preventDefault();
          }
      });
	 
	 if($('#toup-balance-user-select').length) {
		 var changeTopupBalanceUserDropdown = function() {
			 if($('#show-selected-user-balance').length) {
				 if($('#toup-balance-user-select option:selected').data('user-balance')) {
					 $('#show-selected-user-balance').val($('#toup-balance-user-select option:selected').data('user-balance'));
				 } else {
					 $('#show-selected-user-balance').val('0');
				 }
			 }
		 };
		 
		 $('#toup-balance-user-select').on('change', changeTopupBalanceUserDropdown);
		 changeTopupBalanceUserDropdown();
	 }
	 
	 //For Delivery Rate setup
	 $('#btn-save-delivery-rate').on('click', function() {
			if($('#delivery-rate-confirm-popup').length){
				$('#delivery-rate-confirm-popup').modal('show');
			}
		});
		
		$('#btn-confirm-delivery-rate').on('click', function() {
			if($('#frm-delivery-rate').length) {
				$('#frm-delivery-rate').submit();
			}
		});
	 
		if($('#btn-add-delivery-rate').length) {
			$('#btn-add-delivery-rate').on('click', function() {
				console.log("enter add button click...");
				if($('#tbody-delivery-rate-list').length) {
					$('#tbody-delivery-rate-list').append(deliveryRateRow());
					 $(".decimal-input").on("keypress keyup blur",function (event) {
						 $(this).val($(this).val().replace(/[^0-9\.]/g,''));
				         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
				             event.preventDefault();
				         }
				     });
				}
			});
		}
		
		var deliveryRateRow = function() {
			var count = $('#tbody-delivery-rate-list > tr').length;
			return '<tr>'
				+ '<td style="vertical-align: middle; text-align: center;">'+(count + 1)+'</td>'
				+ '<td><input name="deliveryRates['+count+'].fromKm" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
				+ '<td><input name="deliveryRates['+count+'].toKm" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
				+ '<td><input name="deliveryRates['+count+'].rate" class="form-control decimal-input" required autocapitalize="off" autocomplete="off" spellcheck="false" autocorrect="off"></td>'
				+ '<td><a class="btn btn-sm btn-primary btn-delete-delivery-rate"><i class="icon icon-times"></i></a></td>'
				+ '</tr>';
		};
		
		$(document).on('click', '.btn-delete-delivery-rate', function() {
			$(this).closest('tr').remove();
			reorderDeliveryRateTable();
		});
		
		var reorderDeliveryRateTable = function() {
			if($('#tbody-delivery-rate-list').length){
				var i = 1;
				$('#tbody-delivery-rate-list > tr').each(function(i, row) {
					$(row).find("td").each(function(j, td) {
						switch (j) {
						case (0):
							$(td).html(i + 1);
							break;
						case (1): 
							$(td).children().attr("name", "deliveryRates["+i+"].fromKm");
							break;
						case (2):
							$(td).children().attr("name", "deliveryRates["+i+"].toKm");
							break;
						case (3):
							$(td).children().attr("name", "deliveryRates["+i+"].rate");
							break;
						}
					});
				});
			};
		};
		
		$('#btn-confirm-settle').on('click', function() {
			console.log("enter ok button click...")
			if($('#bookingListForm').length) {
				$('#bookingListForm').submit();
			}
		});
		
		$('#promo-btn-delete').on('click', function() {
			if($('#promocode-form').length) {
				$('#promocode-form').submit();
			}
		});
		
		$("#promoCodeImageFile").change(function(e) {
		e.preventDefault();
		$(".promo-code-preview").hide();
		readURL(this, $(".promocode-photo"));
	});
	
	
		$('#vehicle-type-btn-delete').on('click', function() {
			console.log("enter delete vehicle type...");
			if($('#vehicle-type-form').length) {
				$('#vehicle-type-form').submit();
			}
		});
		
		$("#placeTypeIconFile").change(function(e) {
		e.preventDefault();
		$(".place-type-preview").hide();
		readURL(this, $(".placetype-photo"));
	});
	
	$(".select-2").select2();
	
});