$(document).ready(function () {
	let layout = new Layout();


});

class Layout {
	constructor() {
		this.InitEvents();
	}

	InitEvents() {
		$(document).on('click', '.notification', this.showNotifi);
		$(document).on('click', '.account', this.showOptionAcc);
		$('.list-users-main').on('click', 'tbody tr', this.openModalViewDetailUser);
		$('.btn-wrap').on('click', '.cancel', this.closeModel);
		$(document).on('click', '.btn-add-user', this.openModalAddUser);
	}

	openModalViewDetailUser() {
		$('.modal-detail-profile').addClass("show");
	}

	openModalAddUser() {
		$('.modal-add-user').addClass("show");
	}

	closeModel() {
		$('.modal').removeClass("show");
	}

	showNotifi() {
		$('.list-notifi').toggleClass("show");
		$('.notifi').remove();
		$(document).click(function () {
			$('.notifi').remove();
		});
	}

	showOptionAcc() {
		$('.dropdownd-user').toggleClass("show");
	}
}

