const LOCATION_API = "http://localhost:8080";
var caudalam = new Array();
var courseId = $('#courseId').val();
var course = getCourse(courseId);
var courseType = getCourseType(course.typeId);
var totalQuestions = courseType.numberQuestion;

$(document).ready(function () {
    caudalam.push(4, 6);

    //Hiển thị số ô câu hỏi
    showListIconQuestion(totalQuestions);

    //Load dữ liệu câu hỏi
    loadQuestions(totalQuestions);

    //Hiển thị câu hỏi 1 khi bắt đầu
    var current_questionid = 1;
    showQuestion(current_questionid);

    listenClickOtherQuestion(current_questionid);
});

function listenClickOtherQuestion(current_questionid) {
    //Hiển thị nội dung câu hỏi khi chuyển câu hỏi
    $("[data-question-id]").click(function () {
        if (this.getAttribute("data-question-id") != current_questionid) {
            if(da_lam($("[data-question-id=" + current_questionid +"]").val())){
            // console.log($("[data-question-id=" + current_questionid +"]"));
            $("[data-question-id=" + current_questionid +"]").addClass("da-lam");
            }
            hiddenQuestion(current_questionid);
            current_questionid = this.getAttribute("data-question-id");
            showQuestion(current_questionid);
        }
    });
}

function da_lam(current_questionid) {

}

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

function showListIconQuestion(totalQuestions) {
    var questionsPerRow = 5;
    var numberOfRows = Math.ceil(totalQuestions / questionsPerRow);
    var i, j;
    for (i = 0; i < numberOfRows; i++) {
        var row = $('<div class="row"></div>');
        for (j = 0; j < questionsPerRow; j++) {
            var questionId = (i * questionsPerRow + j + 1);
            row.append('<button data-question-id="' + questionId + '">' + questionId + '</button>');
        }
        $('.list-icon-question').append(row);
    }
}

function loadQuestions(totalQuestions) {
    var i;
    for (i = 0; i < totalQuestions; i++) {
        $('.list-question').append('<div id="question_' + (i + 1) + '" class="question" style="display: none;"><p class="question-title">Câu ' + (i + 1) + ':</p></div>')
        $('#question_' + (i + 1)).append("<p class='question-content'> Biển nào sau đây ....</p>")
        $('#question_' + (i + 1)).append("<div class='answer-content'><input type='checkbox' value='test'/> <input type='radio' value='test'/>" +
            "<input type='text' placeholder='text tai day'/></div>")
    }
}

function showQuestion(questionid) {
    $("[data-question-id='" + questionid + "']").focus();
    var selector = '#question_'.concat(questionid);
    $(selector).css("display", "");
}

function hiddenQuestion(questionid) {
    var selector = '#question_'.concat(questionid);
    $(selector).css("display", "none");
}

function getCourseType(id) {
    var courseType = this.getData(LOCATION_API + "/api/course-types/" + id);
    return courseType;
}

function getCourse(id) {
    var course = this.getData(LOCATION_API + "/api/courses/" + id);
    return course;
}