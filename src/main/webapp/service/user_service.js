export default class UserService {
    static logIn(user, password) {
        return fetch("restservices/login", {
            method: "POST",
            body: new URLSearchParams({name: user, password: password})
        })
            .then(response => response.json()).then(user => {
            sessionStorage.setItem("user", JSON.stringify(user));
            return user;
        })
    }

    static logout() {
        sessionStorage.removeItem("user");
        return Promise.resolve();
    }

    static getUser() {
        if (sessionStorage.getItem("user") == null) {
            return Promise.resolve(null);
        }

        return fetch("restservices/login", {
            headers: UserService.setAuthHeader({})
        }).then(response => response.json())
            .then(data => {
                return data;
            })
            .catch(() => {
                return null;
            })
    }

    static requireUser() {
        return this.getUser()
            .then(response => response != null)
            .then(hasData => {
                if (!hasData) {
                    window.location = "/login.html";
                }
            })
    }

    static setAuthHeader(headers) {
        if (sessionStorage.getItem("user") != null) {
            let user = JSON.parse(sessionStorage.getItem("user"));

            headers["Authorization"] = "Bearer " + user.token;
        }

        return headers;
    }
}
