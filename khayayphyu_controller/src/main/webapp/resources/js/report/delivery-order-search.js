$(document)
	.ready(
		function() {

		

			// [START] Stock Datable
			if ($('#delivery-order-search-result').length) {
				var deliveryOrderTable = $('#delivery-order-search-result')
					.DataTable(
						{
							searching: false,
							ordering: false,
							bLengthChange: false,
							proccessing: true,
							serverSide: true,
							ajax: {
								'url':$('#contextPath').val() + '/report/delivery-order-report.json',
								'contentType': 'application/json; charset=utf-8',
								'type': 'POST',
								'dataType': 'json',
								'data': function(d) {
									d.startDate = $('#startDate').val();
									d.endDate = $('#endDate').val();
									d.vesselName = $('#vesselName').val();
									d.voyNo = $('#voyNo').val();
									d.customerName = $('#customerName').val();
									d.orderNo = $('#orderNo').val();
									d.deliveryOrderNo = $('#deliveryOrderNo').val();
									return JSON.stringify(d);
								},
								
							},
							columns: [
							
								{
									'data': 'orderDate',
									className: "text-left"
								},
								
								{
									'data': 'vesselName',
									className: "text-left"
								},
								{
									'data': 'customerName',
									className: "text-left"
								},
								{
									'data': 'truckNo',
									className: "text-left"
								},
								{
									'data': 'bags',
									className: "text-left"
								},
								{
									'data': 'orderNo',
									className: "text-left"
								},
								{
									'data': 'deliveryOrderNo',
									className: "text-left"
								},
								{
									'data': 'points',
									className: "text-left"
								},
								{
									'data': 'township',
									className: "text-left"
								},


							]
						});

				if ($('#btn-search-delivery-order').length) {
					$('#btn-search-delivery-order').on('click', function() {
						deliveryOrderTable.ajax.reload()
					})
				}

				

			}
			// [END] Stock Datable

		});