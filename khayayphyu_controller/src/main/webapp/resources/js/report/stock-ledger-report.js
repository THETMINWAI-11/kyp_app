$(document)
	.ready(
		function() {

		

			if ($('#stock-ledger-table').length) {
				var stockLedgerTable = $('#stock-ledger-table')
					.DataTable(
						{
							searching: false,
							ordering: false,
							bLengthChange: false,
							proccessing: true,
							serverSide: true,
							ajax: {
								'url':$('#contextPath').val() +'/report/stock-ledger-report.json',
								'contentType': 'application/json; charset=utf-8',
								'type': 'POST',
								'dataType': 'json',
								'data': function(d) {
									d.fromDate = $('#startDate').val();
									d.toDate = $('#endDate').val();
									return JSON.stringify(d);
								},
								
							},
							columns: [
							
								{
									'data': 'transactionDate',
									className: "text-left"
								},
								
								{
									'data': 'transactionNo',
									className: "text-left"
								},
								
								{
									'data': 'stockID',
									className: "text-left"
								},
								
								{
									'data': 'stockDescription',
									className: "text-left"
								},
								
								{
									'data': 'stockInQty',
									className: "text-left"
								},
								
								{
									'data': 'stockOutQty',
									className: "text-left"
								},
								{
									'data': 'balance',
									className: "text-left"
								},
								{
									'data': 'stockOutPriceText',
									className: "text-left"
								},
								
								{
									'data': 'stockInPriceText',
									className: "text-left"
								},
								
								{
									'data': 'currency',
									className: "text-left"
								},


							]
						});

				if ($('#btn-search-stock-ledger').length) {
					$('#btn-search-stock-ledger').on('click', function() {
						stockLedgerTable.ajax.reload()
					})
				}

				

			}
			// [END] Stock Datable

		});