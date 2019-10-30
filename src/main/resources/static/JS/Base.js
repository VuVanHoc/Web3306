class Base {
	constructor() {
		// this.loadAllData();
	}

	getAllData(url, methodApi) {
		let data = [];
		$.ajax({
			method: methodApi,
			url: url,
			async: false,
			dataType: "json",
			success: function (res) {
				data = res.data; // gán data
				// console.log(data);
			},
			error: function () {
				alert('Hệ thống sẽ cố gắng không hiện thông báo này');
			}
		});
		return data;
	}

	loadAllData(urlApi, methodApi) {
		let data = this.getAllData(urlApi, methodApi);
		let fields = $('.list-users th[fieldName]');
		$('.list-users tbody').empty();
		$.each(data, function (index, item) {
			let rowhtml = $('<tr></tr>');
			$.each(fields, function (fieldindex, fielditem) {
				let fieldname = fielditem.getAttribute('fieldName');
				let value = item[fieldname];
				if (fieldname) {
					rowhtml.append('<td class="' + fieldname + '">' + value + '</td>');
				}
			});
			rowhtml.append('<td>' +
				'<div>' +
				'<button class="btn-update">Sửa</button>' +
				'<button class="btn-remove">Xoá</button>' +
				'</div></td>')
			$('.list-users tbody').append(rowhtml);
		})
	}

	InsertData(url) {
		$.ajax({
			method: "POST",
			url: url,
			async: false,
			dataType: "json",
			success: function (data) {
				fakeData = data["Customers"]; // gán data
			},
			error: function () {

			}
		});
	}

	DeleteData(url) {
		$.ajax({
			method: "DELETE",
			url: url,
			async: false,
			dataType: "json",
			success: function (data) {

			},
			error: function () {
				alert('Hệ thống sẽ cố gắng không hiện thông báo này');
			}
		});
	}

	UpdateData() {
		$.ajax({
			method: "PUT",
			url: url,
			async: false,
			dataType: "json",
			success: function (data) {

			},
			error: function () {
				alert('Hệ thống sẽ cố gắng không hiện thông báo này');
			}
		});
	}
}