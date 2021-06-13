import UserService from "./service/user_service.js";

const loguitKnop = document.querySelector("#loguit");
const loginStatus = document.querySelector(".loginStatus");
const loginBanner = document.querySelector(".loginBanner");

function updateBanner(status){
    console.log(status)
    if (status == null){
        loginBanner.style.display = "none";
    }else {
        loginStatus.textContent = "ingelogd als: " + status.user;
    }
}

async function loguitClick(){
    UserService.logout();
    UserService.getUser()
        .then(data => {updateBanner(data)})
}

UserService.getUser()
    .then(data => {updateBanner(data)})

loguitKnop.addEventListener("click",  loguitClick);