function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login();
    this.userURI = '/api/users';
    this.loginURI = '/api/session/login';
    var self = this;

    function login(username, password) {
        return fetch(self.loginURI, {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function updateUser(userId, user) {
        return fetch(self.userURI + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            if(response.bodyUsed) {
                return response.json();
            } else {
                return null;
            }
        });
    }

    function findUserById(userId) {
        return $.getJSON(self.userURI + '/' + userId);
    }

    function deleteUser(userId) {
        return fetch(self.userURI + '/' + userId, {
            method: 'delete'
        });
    }

    function findAllUsers() {
        return $.getJSON(self.userURI);
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}