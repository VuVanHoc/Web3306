const API_URL = "http://localhost:8080";
var caudalam = new Array(); //id của các câu đã làm
var courseId = $('#courseId').val();
var course = getCourse(courseId);
var courseType = getCourseType(course.typeId);
var totalQuestions = courseType.numberQuestion;
var examId = getExam(courseId).id;
var questionTypeCode = ["MC", "SA", "SO"]; //MC: checkbox, SA: text, SO: radio

$(document).ready(function () {
    // caudalam.push(4, 6);

    //Hiển thị số ô câu hỏi
    showListIconQuestion(totalQuestions);

    //Load dữ liệu câu hỏi
    loadQuestions(totalQuestions);

    //Hiển thị câu hỏi 1 khi bắt đầu
    var current_question = 1;
    $("[data-question=1]").addClass("dang-lam");
    showQuestion(current_question);

    listenClickOtherQuestion(current_question);
});

function listenClickOtherQuestion(current_question) {
    //Hiển thị nội dung câu hỏi khi chuyển câu hỏi
    $("[data-question]").click(function () {
        if (this.getAttribute("data-question") != current_question) {
            $("[data-question=" + current_question +"]").removeClass("dang-lam");
            if(da_lam(current_question)){
                $("[data-question=" + current_question +"]").addClass("da-lam");
            }
            hiddenQuestion(current_question);
            current_question = this.getAttribute("data-question");
            $("[data-question=" + current_question +"]").addClass("dang-lam");
            showQuestion(current_question);
        }
    });
}

function kiem_tra_da_lam() {

}

function da_lam(current_question) {
    var index = caudalam.indexOf(Number(current_question));
    return index >= 0 ? true : false;
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
            // alert(fakeData.message);
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
            var question = (i * questionsPerRow + j + 1);
            row.append('<a href="#" data-question="' + question + '">' + question + '</a>');
        }
        $('.list-icon-question').append(row);
    }

}

function loadQuestions(totalQuestions) {
    var array_question_id = getData(API_URL + "/api/exams/" + examId + "/questions");
    array_question_id.sort();
    console.log(array_question_id);
    var i;
    for (i = 0; i < totalQuestions; i++) {
        $('.list-question').append('<div id="question_' + (i + 1) + '" class="question" style="display: none;"><p class="question-title">Câu ' + (i + 1) + ':</p></div>')

        var data_question = getData(API_URL + "/api/questions/".concat(array_question_id[i]));
        console.log(data_question);

        //Nội dung câu hỏi
        $('#question_' + (i + 1)).append("<p class='question-content'>" + data_question.content + "</p>")

        //Phần câu trả lời
        switch(data_question.questionTypeCode) {
            case questionTypeCode[0]: //MC
                var j;
                for(j = 0; j < data_question.answers.length; j++){
                    $('#question_' + (i + 1)).append("<div class='answer-content'><input type='checkbox' value=''/>" + data_question.answers[j] +"</div>");
                }
                break;
            case questionTypeCode[1]: //SA
                for(j = 0; j < data_question.answers.length; j++){
                    $('#question_' + (i + 1)).append("<div class='answer-content'><input type='text' placeholder='Nhập câu trả lời'/></div>");
                }
                break;
            case questionTypeCode[2]: //SO
                for(j = 0; j < data_question.answers.length; j++){
                    $('#question_' + (i + 1)).append("<div class='answer-content'><input type='radio' name='answerSelectOne' value=''/>" + data_question.answers[j] +"</div>");
                }
                break;
            default:
                $('#question_' + (i + 1)).append("<div class='answer-content'><p>Không thể load dạng câu trả lời</p></div>");
        }
    }
}

function showQuestion(questionid) {
    var selector = '#question_'.concat(questionid);
    $(selector).css("display", "");
}

function hiddenQuestion(questionid) {
    var selector = '#question_'.concat(questionid);
    $(selector).css("display", "none");
}

function getCourseType(id) {
    var courseType = this.getData(API_URL + "/api/course-types/" + id);
    return courseType;
}

function getCourse(id) {
    var course = this.getData(API_URL + "/api/courses/" + id);
    return course;
}

function getExam(courseId) {
    var exam = getData(API_URL + "/api/courses/" + courseId + "/exam-schedule");
    return exam;
}