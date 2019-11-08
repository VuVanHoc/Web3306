const LOCATION_API = "http://localhost:8080";

$(document).ready(function () {
    loadInfo();
});

function getData(url) {
    var fakeData = [];
    $.ajax({
        method: "GET",
        url: url,
        async: false,
        dataType: "json",
        success: function (res) {
            fakeData = res.data; // gán data
        },
        error: function (xhr) {
            fakeData = JSON.parse(xhr.responseText);
            alert(fakeData.message);
        }
    });
    return fakeData;
}

function loadInfo() {
    var courseId = $('#courseId').val();
    var current_course = getCourse(courseId);
    // var current_coursetype = getCourseType(current_course.typeId);
    var examSchedule = this.getData(LOCATION_API + "/api/courses/" + courseId + "/exam-schedule");
    var start_time = new Date(examSchedule.startTime);
    var end_time = new Date(examSchedule.endTime);

    $('#courseName').text(current_course.courseName);
    $('#startTime').text(start_time.getHours() + " giờ " + start_time.getMinutes() + " phút, ngày " + start_time.getDate() + " tháng " + (start_time.getMonth()+1) + " năm " + start_time.getFullYear());
    $('#endTime').text(end_time.getHours() + " giờ " + end_time.getMinutes() + " phút, ngày " + end_time.getDate() + " tháng " + (end_time.getMonth()+1) + " năm " + end_time.getFullYear());
    $('#examTime').text("20 phút"); //mặc định thời gian thi cho mọi kỳ thi vì chưa có dữ liệu này
    if($('#note').val() != null) $('#note').text(examSchedule.note);
    // $('#examId').val(examSchedule.id);
    // console.log("Exam ID: " + examSchedule.id);
}

function loadExam() {
    var url_exam = LOCATION_API.concat("/student/courses/", $('#courseId').val(), "/exam");
    window.location.href = url_exam;
}

// function getCourseType(id) {
//     var courseType = this.getData(LOCATION_API + "/api/course-types/" + id);
// }

function getCourse(id) {
    var course = this.getData(LOCATION_API + "/api/courses/" + id);
    return course;
}