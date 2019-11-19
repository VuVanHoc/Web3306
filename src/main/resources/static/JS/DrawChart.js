$(document).ready(function () {
    a = getData("/api/exam-result/totalRecords"); //Tổng ĐỖ+TRƯỢT
    b = getData("/api/exam-result/totalPass"); //Tổng ĐỖ
	var drawChartPie = new DrawChartPie(b,a-b); // tuyền ti le thi sinh đỗ hay trượi
	var drawChartLine = new DrawChartLine(); // truyên vào số lượng tí sinh có điểm 10 20 30...100
})

function getData(url) {
    var fakeData = [];
    $.ajax({
        method: "GET",
        url: url,
        async: false,
        dataType: "json",
        success: function (response) {
            fakeData = response.data; // gán data
            // console.log(fakeData);
        },
        error: function (xhr) {
            fakeData = JSON.parse(xhr.responseText);
            // alert(fakeData.message);
        }
    });
    return fakeData;
}

class DrawChartPie {
	constructor(a,b) { //cho thêm tham số vào đây
		var ctx = document.getElementById('chart-pass-fail').getContext('2d');
		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: 'pie',

			// The data for our dataset
			data: {
				labels: ["Pass", "Fail"],
				datasets: [{
					label: "My First dataset",
					backgroundColor: ['#19B133', 'red'],
					borderColor: '#4852cc',
					data: [a, b], //đây la sô % đỗ vá trượt
				}]
			},

			// Configuration options go here
			options: {}
		});
	}
}
class DrawChartLine {
	constructor() { // thêm tham số ở đây
		var ctx = document.getElementById('chart-point-exam').getContext('2d');
		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: 'line',

			// The data for our dataset
			data: {
				labels: ["10", "20", "30", "40", "50", "60", "70", "80", "90", "100"],
				datasets: [{
					label: "Point",
					backgroundColor: "#0285bb",
					borderColor: '#4852cc',
					// data: [0, 10, 20]
					data: [0, 10, 5, 36, 20, 30, 45, 225, 55, 66], // đây là số thi sinh từ 0 10 20 ..100
				}]
			},

			// Configuration options go here
			options: {}
		});
	}
}