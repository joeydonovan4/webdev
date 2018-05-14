(function () {
    var $tbody, $user-template;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $tbody = $('tbody');
        $user-template = $('#user-template');
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var email = $('#emailFld').val();
        var phone = $('#phoneFld').val();
        var dateOfBirth = $('#dobFld').val();
        var role = $('#roleFld').val();

        userService.createUser(
            new User(username, password, firstName, lastName, email, phone, role, dateOfBirth)
        ).then(findAllUsers);
    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function findUserById() { … }
    function deleteUser() { … }
    function selectUser() { … }
    function updateUser() { … }

    function editAndDeleteButtonsHTML() {
        var buttons = '<td><div class="btn-group">' +
                            '<button type="button" class="btn btn-primary"><span class="fa fa-pencil"></span></button>' +
                            '<button type="button" class="btn btn-danger"><span class="fa fa-trash-o"></span></button>' +
                      '</div></td>';
        return buttons;
    }

    function renderUser(user) {
        var templateClone = $user-template.clone();
        templateClone.attr('id', user.getId());
        templateClone.append('<td>' + user.getUsername() + '</td>');
        templateClone.append('<td>********</td>');
        templateClone.append('<td>' + user.getFirstName() + '</td>');
        templateClone.append('<td>' + user.getLastName() + '</td>');
        templateClone.append('<td>' + user.getEmail() + '</td>');
        templateClone.append('<td>' + user.getPhone() + '</td>');
        templateClone.append('<td>' + user.getDateOfBirth() + '</td>');
        templateClone.append('<td>' + user.getRole() + '</td>');
        templateClone.append(editAndDeleteButtonsHTML());

        $tbody.append(templateClone);
    }

    function renderUsers(users) {
        $tbody.find('tr:gt(0)').remove();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var userObj = new User(
                              user.username,
                              user.password,
                              user.firstName,
                              user.lastName,
                              user.email,
                              user.phone,
                              user.role,
                              user.dateOfBirth);
            userObj.setId(user.id);
            renderUser(userObj);
        }
    }
})();
