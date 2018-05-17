(function () {
    var $firstName, $lastName;
    var $email, $username;
    var $password, $phone;
    var $dateOfBirth, $role;
    var profileService = new ProfileServiceClient();
    var sessionService = new SessionServiceClient();
    var loginPage = '/jquery/components/login/login.template.client.html';
    $(main);

    function main() {
        $firstName = $('#first-name-fld');
        $lastName = $('#last-name-fld');
        $email = $('#email-fld');
        $username = $('#username-fld');
        $password = $('#password-fld');
        $phone = $('#phone-fld');
        $dateOfBirth = $('#dob-fld');
        $role = $('#role-fld');

        $('#edit-btn').click(editProfile);
        $('form').submit(updateProfile);
        $('#logout-btn').click(logout);
        getLoggedInUser();
    }

    function getLoggedInUser() {
        sessionService.getLoggedInUser().then(showUserData);
    }

    function showUserData(user) {
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $email.val(user.email);
        $username.val(user.username);
        $password.val(user.password);
        $phone.val(user.phone);
        $dateOfBirth.val(user.dateOfBirth);
    }

    function logout() {
        sessionService.logout().then(redirectToLogin);
    }

    function redirectToLogin() {
        document.location.href = loginPage;
    }

    function editProfile() {
        $('#edit-btn').hide();
        $('#save-changes-btn').show();
        disableInputs(false);
    }

    function disableInputs(shouldDisable) {
        $('form').find('input:disabled').each(function() {
            $(this).prop('disabled', shouldDisable);
        });
    }

    function updateProfile(event) {
        profileService.updateProfile(generateUser()).then(function(response) {
            if (response.ok) {
                $('#edit-btn').show();
                $('#save-changes-btn').hide();
                disableInputs(true);
            } else {
                console.log('Error updating profile');
                console.log(response);
            }
        });

        event.preventDefault();
    }

    function generateUser() {
        return new User($username.val(),
                            $password.val(),
                            $firstName.val(),
                            $lastName.val(),
                            $email.val(),
                            $phone.val(),
                            $role.val(),
                            $dateOfBirth.val());
    }
})();