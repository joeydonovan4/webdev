(function () {
    var $tbody, $user_template;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $tbody = $('tbody');
        $user_template = $('#user-template');

        $('#reset-btn').click(resetNewUser);
        $('#add-btn').click(createUser);
        $(document).on('click', '#delete-btn', deleteUser);
        $(document).on('click', '#edit-btn', editUser);
        $(document).on('click', '#save-btn', updateUser);
        $('#edit-btn').click(editUser);

        findAllUsers();
    }

    function resetNewUser() {
        $('#username-fld').val('');
        $('#password-fld').val('');
        $('#first-name-fld').val('');
        $('#last-name-fld').val('');
        $('#email-fld').val('');
        $('#phone-fld').val('');
        $('#dob-fld').val('');
        $('#role-fld')[0].options.selectedIndex = 0;
    }

    function createUser() {
        var username = $('#username-fld').val();
        var password = $('#password-fld').val();
        var firstName = $('#first-name-fld').val();
        var lastName = $('#last-name-fld').val();
        var email = $('#email-fld').val();
        var phone = $('#phone-fld').val();
        var dateOfBirth = $('#dob-fld').val();
        var role = $('#role-fld').val();

        userService.createUser(
            new User(username, password, firstName, lastName, email, phone, role, dateOfBirth)
        ).then(findAllUsers);

        resetNewUser();
    }

    function findUserById(id) {
        return userService.findUserById(id);
    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn.parent().parent().parent().attr('id');

        userService.deleteUser(userId).then(findAllUsers);
    }

    function editUser(event) {
        var editBtn = $(event.currentTarget);
        var userId = editBtn.parent().parent().parent().attr('id');
        findUserById(userId).then(function(user) {
           var templateClone = $('#new-user-template').clone();
           templateClone.attr('id', userId);
           templateClone.find('#username-fld').val(user.username);
           templateClone.find('#password-fld').val(user.password);
           templateClone.find('#first-name-fld').val(user.firstName);
           templateClone.find('#last-name-fld').val(user.lastName);
           templateClone.find('#email-fld').val(user.email);
           templateClone.find('#phone-fld').val(user.phone);
           if (user.dateOfBirth !== null) {
               templateClone.find('#dob-fld').val(user.dateOfBirth.split('T')[0]);
           }
           var roleFld = templateClone.find('#role-fld')[0];
           if (user.role === 'Student') {
               roleFld.options.selectedIndex = 0;
           } else if (user.role == 'Faculty') {
               roleFld.options.selectedIndex = 1;
           } else {
               roleFld.options.selectedIndex = 2;
           }

           templateClone.find('.btn-group').replaceWith(discardAndSaveButtonsHTML());

           editBtn.parent().parent().parent().replaceWith(templateClone);
        });

        event.preventDefault();
    }

    function updateUser(event) {
        var editBtn = $(event.currentTarget);
        var values = editBtn.parent().parent().parent();
        var userId = values.attr('id');

        var username = values.find('#username-fld').val();
        var password = values.find('#password-fld').val();
        var firstName = values.find('#first-name-fld').val();
        var lastName = values.find('#last-name-fld').val();
        var email = values.find('#email-fld').val();
        var phone = values.find('#phone-fld').val();
        var dateOfBirth = values.find('#dob-fld').val();
        var role = values.find('#role-fld').val();
        var userObj = new User(username, password, firstName, lastName, email, phone, role, dateOfBirth)
        userObj.setId(userId);

        userService.updateUser(userId, userObj).then(function(response) {
            if (response.ok) {
                alert('User updated successfully!');
                findAllUsers();
            } else {
                alert('There was an error updating the user.');
            }
        });

        event.preventDefault();
    }

    function editAndDeleteButtonsHTML() {
        var buttons = '<td><div class="btn-group">' +
                            '<button id="edit-btn" type="button" class="btn btn-primary" title="Edit user"><span class="fa fa-pencil"></span></button>' +
                            '<button id="delete-btn" type="button" class="btn btn-danger" title="Delete user"><span class="fa fa-trash-o"></span></button>' +
                      '</div></td>';
        return buttons;
    }

    function discardAndSaveButtonsHTML() {
        return '<div class="btn-group">' +
                    '<button id="discard-btn" type="button" class="btn btn-secondary" title="Discard changes"><span class="fa fa-repeat"></span></button>' +
                    '<button id="save-btn" type="submit" class="btn btn-success" title="Save changes"><span class="fa fa-check"></span></button>' +
                    '</div>';
    }

    function renderUser(user) {
        var templateClone = $user_template.clone();
        templateClone.attr('id', user.getId());
        templateClone.append('<td>' + user.getUsername() + '</td>');
        templateClone.append('<td>********</td>');
        templateClone.append('<td>' + user.getFirstName() + '</td>');
        templateClone.append('<td>' + user.getLastName() + '</td>');
        templateClone.append('<td>' + user.getEmail() + '</td>');
        templateClone.append('<td>' + user.getPhone() + '</td>');
        if (user.getDateOfBirth() !== null) {
            templateClone.append('<td>' + user.getDateOfBirth().split('T')[0] + '</td>');
        } else {
            templateClone.append('<td></td>');
        }
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
