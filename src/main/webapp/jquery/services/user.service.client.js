function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.register = register;
    this.userURI = '/api/users';
    this.registerURI = '/api/register';
    var self = this;

    function register(user) {
        return fetch(self.registerURI, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            },
            credentials: 'same-origin'
        });
    }

    function updateUser(userId, user) {
        return fetch(self.userURI + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            },
            credentials: 'same-origin'
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
        return fetch(self.userURI + '/' + userId, {
            credentials: 'same-origin'
        }).then(function(response) {
            return response.json();
        });
    }

    function deleteUser(userId) {
        return fetch(self.userURI + '/' + userId, {
            method: 'delete',
            credentials: 'same-origin'
        });
    }

    function findAllUsers() {
        return fetch(self.userURI + '/all', {
            credentials: 'same-origin'
        }).then(function(response) {
            return response.json();
        });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            },
            credentials: 'same-origin'
        });
    }
}