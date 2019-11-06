$(document).ready(function () {
	loadDataToUI();
});

function getDataFormServer() {
	var arrayData = [];
	$.ajax({
		method: "GET",
		url: "/api/user",
		async: false,
		dataType: "json",
		success: function (res) {
			arrayData = res.data;
		},
		error: function () {
			alert("Error")
		}
	});
	return arrayData;
}

function loadDataToUI() {
	let data = this.getDataFormServer();
	console.log(data);

	var fields = $('.list-users th[fieldName]');
	$('.list-users tbody').empty();
	$.each(data, function (index, item) {
		var rowhtml = $('<tr></tr>');
		$.each(fields, function (fieldindex, fielditem) {
		    var fieldname = fielditem.getAttribute('fieldName');
		    var value = item[fieldname];
		    if (fieldname) {
		        rowhtml.append('<td class="' + fieldname + '">' + value + '</td>');
		    }
		});
		$('.list-users tbody').append(rowhtml);

	})
}
