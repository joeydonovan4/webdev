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

        getLoggedInUser();
    }

    function getLoggedInUser() {
        sessionService.getLoggedInUser().then(function(response) {
            if (response !== null) {
                console.log('User already logged in. Redirecting to profile page.');
                redirectToProfile();
            }
        });
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
                    var errorResponse;
                    if (response.status == 200) {
                        redirectToProfile();
                        return;
                    } else if (response.status == 404) {
                        errorResponse = 'Username not found!';
                    } else if (response.status == 400) {
                        errorResponse = 'Invalid password!';
                    } else {
                        errorResponse = 'Something went wrong. Please try again.';
                    }
                    response.json().then(function(error) {
                        alert(errorResponse);
                    });
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