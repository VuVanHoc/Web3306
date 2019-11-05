$(document).ready(function () {
	function loadListUser() {
		$.ajax({
			method: "GET",
			url: "/api/"
		})
	}
});