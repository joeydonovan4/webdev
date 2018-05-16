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