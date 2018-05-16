function ProfileServiceClient() {
    this.updateProfile = updateProfile;
    this.profileURI = '/api/profile';
    var self = this;

    function updateProfile(user) {
        return fetch(self.profileURI, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}