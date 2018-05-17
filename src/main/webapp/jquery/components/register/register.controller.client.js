(function () {
    var userService = new UserServiceClient();
    var profileURI = '/jquery/components/profile/profile.template.client.html';
    $(main);

    function main() {
        $('form').submit(register);

        $('#first-name-fld').on('input', validateInput);
        $('#last-name-fld').on('input', validateInput);
        $('#email-fld').focusout(validateEmail);
        $('#username-fld').on('input', validateInput);
        $('#password-fld').on('input', validateInput);
        $('#password-verify-fld').on('input', validateInput);
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

    function validateEmail() {
        var input=$(this);
        var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        var is_email=re.test(input.val());
        if (!is_email){
            input.addClass("invalid");
            input.next('.empty-input-error').show();
        }
        else {
            input.removeClass("invalid");
            input.next('.empty-input-error').hide();
        }
    }

    function isValid(field) {
        return !field.hasClass("invalid");
    }

    function register(event) {
        if (validateNewUser()) {
            userService.register(getNewUser())
                    .then(redirectToProfile);
        }

        event.preventDefault();
    }

    function redirectToProfile() {
        document.location.href = profileURI;
    }

    function validateNewUser() {
        var $firstNameFld = $('#first-name-fld');
        var $lastNameFld = $('#last-name-fld');
        var $emailFld = $('#email-fld');
        var $usernameFld = $('#username-fld');
        var $passwordFld = $('#password-fld');
        var $passwordVerifyFld = $('#password-verify-fld');

        return isValid($firstNameFld) && isValid($lastNameFld) && isValid($emailFld) &&
            isValid($usernameFld) && isValid($passwordFld) && isValid($passwordVerifyFld);
    }

    function getNewUser() {
        var fields = $('form').serializeArray();
        var $firstNameFld = fields[0].value;
        var $lastNameFld = fields[1].value;
        var $emailFld = fields[2].value;
        var $usernameFld = fields[3].value;
        var $passwordFld = fields[4].value;

        return new User($usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $emailFld, null, 'STUDENT', null);
    }
})();