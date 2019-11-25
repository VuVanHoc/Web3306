$(document).ready(function () {
    $(document).on('change', '.question-type', function () {
        var questionsType = $('.question-type').val();
        switch (questionsType) {
            case "multiple-choice":
                $('.answer').css("display", "none");
                $('.answer-choice').css("display", "block");
                var answers = $('.one-answer');
                $.each(answers, function (index, answer) {
                    if (index != 0) {
                        $(answer).remove();
                    }
                });
                break;
            case "one-choice":
                $('.answer').css("display", "none");
                $('.answer-choice').css("display", "block");
                var answers = $('.one-answer');
                $.each(answers, function (index, answer) {
                    if (index != 0) {
                        $(answer).remove();
                    }
                });
                break;
            case "text-answer":
                $('.answer').css("display", "none");
                $('.answer-text').css("display", "block");
                break;
            case "yes-no":
                $('.answer').css("display", "none");
                $('.answer-yes-no').css("display", "block");
                break;
            default:
                break;
        }
    });
    $(document).on('click', '.addNewQuestion', function () {
        var oneAnswer = $('<div class="one-answer"></div>');
        var textarea = $('<input type="checkbox" class="checkbox" title="Đáp án đúng">   <textarea placeholder="Nhập đáp án..."></textarea>');
        var buttonDelete = ('<button class="deleteQuestion btn btn-danger">Xóa</button>');
        oneAnswer.append(textarea).append(buttonDelete);
        $(this).parent().children('.answer-wrap').append(oneAnswer);

    });
    $(document).on('click', '.btn-danger', function () {
        $(this).parent().remove();
    });

    $('.btn-cancel').click( function () {
	    window.location.href = "http://localhost:8080/admin/questions"
    })
});