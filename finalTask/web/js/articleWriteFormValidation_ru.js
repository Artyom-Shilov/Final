$(document).ready(function () {
    $('#writeArticleForm').submit(function (e) {
        e.preventDefault();
        var title = $('#titleTextarea').val();
        var text = $('#textTextarea').val();
        var category = $('#categorySelect').val();
        var valid = true;
        $(".error").remove();

        if (title.length < 1) {
            $('#titleTextarea').after('<span class="error">Введите заголовок</span>');
            valid = false;
        }
        if (text.length < 100) {
            $('#textTextarea').after('<span class="error">Текст статьи слишком короткий</span>');
            valid = false;
        }
        if (text.length > 5000) {
            $('#titleTextarea').after('<span class="error">Текст статьи слишком длинный</span>');
            valid = false;
        }
        if (category.length < 1) {
            $('#categorySelect').after('<span class="error">Выберете категорию</span>');
            valid = false;
        }
        if (valid) this.submit();
    });
});
