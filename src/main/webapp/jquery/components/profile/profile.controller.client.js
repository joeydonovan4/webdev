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
        profileService.getLoggedInUser().then(showUserData);
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
        enableInputs(true);
    }

    function enableInputs(shouldEnable) {
        $('form').find(':input').each(function() {
            $(this).prop('disabled', shouldEnable);
        });
    }

    function updateProfile() {
        profileService.updateProfile().then(function(response) {
            if (response.ok) {
                $('#edit-btn').show();
                $('#save-changes-btn').hide();
                enableInputs(false);
            } else {
                console.log('Error updating profile');
                console.log(response);
            }
        });
    }
})();