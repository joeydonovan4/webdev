function SessionServiceClient() {
    this.getLoggedInUser = getLoggedInUser;
    this.login = login;
    this.logout = logout;
    this.sessionURI = '/api/session';
    var self = this;

    function getLoggedInUser() {
        return fetch(self.sessionURI, {
            credentials: 'same-origin'
        }).then(function(response) {
            if (response.ok) {
                return response.json();
            }
            return null;
        });
    }

    function login(username, password) {
        return fetch(self.sessionURI + '/login', {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            },
            credentials: 'same-origin'
        });
    }

    function logout() {
        return fetch(self.sessionURI + '/logout', {
            method: 'post',
            body: JSON.stringify({}),
            credentials: 'same-origin'
        });
    }
}