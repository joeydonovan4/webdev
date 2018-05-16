function SessionServiceClient() {
    this.getLoggedInUser = getLoggedInUser;
    this.login = login;
    this.logout = logout;
    this.sessionURI = '/api/session';
    var self = this;

    function getLoggedInUser() {
        return $.getJSON(self.sessionURI);
    }

    function login() {

    }

    function logout() {

    }
}