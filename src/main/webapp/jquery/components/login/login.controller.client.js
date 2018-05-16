(function () {
    var $username, $password;
    var sessionService = new SessionServiceClient();
    var profileURI = '/jquery/components/profile/profile.template.client.html';
    $(main);

    function main() {
        $('form').submit(login);
        $username = $('#username-fld');
        $password = $('#password-fld');

        $username.on('input', validateInput);
        $password.on('input', validateInput);
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
            sessionService.login($username.val(), $password.val())
                .then(function(response) {
                    if (response.ok) {
                        redirectToProfile();
                    } else {
                        console.log('ERROR LOGGING IN');
                        console.log(response);
                    }
                });
        }

        event.preventDefault();
    }

    function redirectToProfile() {
        document.location.href = profileURI;
    }

    function validateUser() {
        var $usernameFld = $('#username-fld');
        var $passwordFld = $('#password-fld');

        return isValid($usernameFld) && isValid($passwordFld);
    }
})();