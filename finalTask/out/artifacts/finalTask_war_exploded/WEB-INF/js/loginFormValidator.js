
$(document).ready(function () {
    $('#loginForm').submit(function (e) {
        e.preventDefault();
        var login = $('#login').val();
        var password = $('#password').val();
        var valid = true;
        $(".error").remove();

        if (login.length < 1) {
            $('#login').after('<span class="error">This field is required</span>');
            valid = false;
        }
        if (password.length < 3) {
            $('#password').after('<span class="error">Password must be at least 3 characters long</span>');
            valid = false;
        }
        if (valid) this.submit();
    });

});
