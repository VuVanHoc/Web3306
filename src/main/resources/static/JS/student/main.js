$(document).ready(function () {
    loadAllData();
});

function getAllData(url)
{
    var fakeData = [];
    $.ajax({
        method: "GET",
        url: url,
        async: false,
        dataType: "json",
        success: function (response) {
            fakeData = response.data; // gán data
            console.log(fakeData);
        },
        error: function () {
            alert('Hệ thống sẽ cố gắng không hiện thông báo này');
        }
    });
    return fakeData;
}

function loadAllData()
{
    var data = this.getAllData($api_url + "/users/4/courses");
    var fields = $('.list-users th[fieldName]');
    $('.list-users tbody').empty();
    $.each(data, function (index, item) {
        // var rowhtml = $('<tr></tr>');
        // $.each(fields, function (fieldindex, fielditem) {
        //     var fieldname = fielditem.getAttribute('fieldName');
        //     var value = item[fieldname];
        //     if (fieldname) {
        //         rowhtml.append('<td class="' + fieldname + '">' + value + '</td>');
        //     }
        // });
        // $('.list-users tbody').append(rowhtml);
        var rowhtml = $('<tr></tr>');
        rowhtml.append('<td class="">' + index+1 + '</td>');
        rowhtml.append('<td class="">' + item.courseName + '</td>');
        rowhtml.append('<td class="">' + item.typeId + '</td>');
        rowhtml.append('<td class=""> Vào thi </td>');
        rowhtml.append('<td class=""> Chưa có điểm </td>');
        $('.list-users tbody').append(rowhtml);
    })

}

function InsertData(url)
{
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

function DeleteData(url)
{
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

function UpdateData()
{
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