function SessionServiceClient() {
    this.getLoggedInUser = getLoggedInUser;
    this.login = login;
    this.logout = logout;
    this.sessionURI = '/api/session';
    var self = this;

    function getLoggedInUser() {
        return $.getJSON(self.sessionURI);
    }

    function login(username, password) {
        return fetch(self.sessionURI + '/login', {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function logout() {
        return fetch(self.sessionURI + '/logout', {
            method: 'post',
            body: JSON.stringify({})
        });
    }
}