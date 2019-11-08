const LOCATION_API = "http://localhost:8080";

$(document).ready(function () {
    loadCourses();
});

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
        error: function () {
            alert('Hệ thống sẽ cố gắng không hiện thông báo này');
        }
    });
    return fakeData;
}

function loadCourses() {
    var data = this.getData(LOCATION_API + "/api/users/4/courses");
    $('#endRow').text(data.length);
    $('#totalRows').text(data.length);
    // var fields = $('.list-courses th[fieldName]');
    $('.list-courses tbody').empty();
    var path1 = "/student/courses/";
    var path2 = "/examDetail";
    $.each(data, function (index, item) {
        var rowhtml = $('<tr></tr>');
        rowhtml.append('<td class="">' + (index+1) + '</td>');
        rowhtml.append('<td class="">' + item.courseName + '</td>');
        rowhtml.append('<td class="">' + getNameCourseType(item.typeId) + '</td>');
        var exam_url = path1.concat(item.id, path2);
        rowhtml.append('<td class=""><a href="'+ exam_url +'">Xem chi tiết</a></td>');
        $('.list-courses tbody').append(rowhtml);
    })

}

function getNameCourseType(typeId) {
    var name = this.getData(LOCATION_API + "/api/course-types/" + typeId).name;
    return name;
}