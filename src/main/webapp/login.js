import UserService from "./service/user_service.js";

document.querySelector("#loginKnop").addEventListener("click", e => {
    e.preventDefault();
    UserService.logIn(document.forms["login"].username.value, document.forms["login"].password.value)
        .then(u => {
            if (u != null) {
                window.location = "/adminPaneel.html";
            }
        })
        .catch(error => console.log(error.message));
});

document.querySelector("#terug").addEventListener("click", e => {
    window.location = "/";
});

