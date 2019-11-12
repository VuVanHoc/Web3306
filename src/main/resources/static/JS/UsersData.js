$(document).ready(function () {
	loadDataToUI();

	//add new user
	$('#btn-register').click(function () {
		var fullName = $('#txt-fullname').val();
		var username = $('#txt-username').val();
		var phone = $('#txt-phone').val();
		var pass = $('#password-add').val();
		var cfpw = $('#cfpw-add').val();
		var roleId = $('#roleName').val();

		console.log("Name"+fullName);
		var object = {
			roleId : roleId,
			fullName: fullName,
			username : username,
			password : pass,
			confirmPassword: cfpw,
			phone: phone
		};
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/api/users",
			data :JSON.stringify(object),
			characterData: "utf-8",
			contentType: "application/json; charset=UTF-8",
			dataType : "json",
			success: function () {
				alert("Đăng ký thành công!");
				closeModel();
				loadDataToUI();

			}
		})
	});

	$('')
});
 function closeModel() {
	$('.modal').removeClass("show");
}
function getDataFormServer() {
	var arrayData = [];
	$.ajax({
		method: "GET",
		url: "/api/users",
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
		rowhtml.append('<td><button class="btn-table update">Sửa</button><button class="btn-table cancel">Xoá</button></td>');
		$('.list-users tbody').append(rowhtml);

	})
}
