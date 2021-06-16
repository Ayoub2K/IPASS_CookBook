import UserService from "./service/user_service.js";
const form = document.querySelector("#POSTgerechtForm")
UserService.requireUser()

document.querySelector('#submit').addEventListener("click", async function(){
    const formData = new FormData(document.querySelector("#POSTgerechtForm"));
    const encData = new URLSearchParams(formData.entries());

    fetch("restservices/gerechten/gerecht", {
        method: 'POST',
        headers: UserService.setAuthHeader({}),
        body: encData,
   })
       .then((response) => {
           if (!response.ok) {
               alert("Er bestaat al een gerecht met die naam! \nkies aub een andere naam voor dit gerecht.");
               form.naam.value = " ";
               return response;
           }else {
               alert("gerecht is toegevoegd.");
               window.location = "adminPaneel.html";
               return response;
           }
       })
       .then(function (myjson){
           console.log(myjson)
       })
        .catch(error => console.log(error.message));
});

