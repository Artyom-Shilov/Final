$(document).ready(function () {
    $('#writeArticleForm').submit(function (e) {
        e.preventDefault();
        var title = $('#titleTextarea').val();
        var text = $('#textTextarea').val();
        var category = $('#categorySelect').val();
        var valid = true;
        $(".error").remove();

        if (title.length < 1) {
            $('#titleTextarea').after('<span class="error">Enter Title</span>');
            valid = false;
        }
        if (text.length < 100) {
            $('#textTextarea').after('<span class="error">Text is too short</span>');
            valid = false;
        }
        if (text.length > 5000) {
            $('#textTextarea').after('<span class="error">Text is too long</span>');
            valid = false;
        }
        if (category.length < 1) {
            $('#textTextarea5').after('<span class="error">Choose category</span>');
            valid = false;
        }
        if (valid) this.submit();
    });
});
