(function () {
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('form').submit(login);

        $('#username-fld').on('input', validateInput);
        $('#password-fld').on('input', validateInput);
    }

    function validateInput() {
        var input=$(this);
        var is_name=input.val();
        if(!is_name){
            input.addClass('invalid');
            input.next('.empty-input-error').show();
        } else {
            input.removeClass('invalid');
            input.next('.empty-input-error').hide();
        }
    }

    function isValid(field) {
        return !field.hasClass("invalid");
    }

    function login(event) {
        if (validateUser()) {
            userService.login($('#username-fld'), $('#password-fld'));
        }

        event.preventDefault();
    }

    function validateUser() {
        var $usernameFld = $('#username-fld');
        var $passwordFld = $('#password-fld');

        return isValid($usernameFld) && isValid($passwordFld);
    }
})();