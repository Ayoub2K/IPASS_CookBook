import UserService from "./service/user_service.js";
const form = document.querySelector("#POSTgerechtObjectForm")
UserService.requireUser()

document.querySelector('#submitObject').addEventListener("click", e =>{
    e.preventDefault()

    const data = new FormData(document.querySelector("#POSTgerechtObjectForm"));

    const jsonObject = {
        "naam": data.get("naam"),
        "beschrijving": data.get("beschrijving"),
        "bereidingstijd": data.get("bereidingstijd"),
        "bereidingswijze": data.get("bereidingswijze"),
        "categorie": data.get("categorie"),
        "portie": data.get("portie"),
        "alleIngredienten" : [
            { "naam":data.get("naamIngr1"), "hoeveelheid":data.get("hoeveelheid1"), "calper100":data.get("calper1")},
            { "naam":data.get("naamIngr2"), "hoeveelheid":data.get("hoeveelheid2"), "calper100":data.get("calper2")},
            { "naam":data.get("naamIngr3"), "hoeveelheid":data.get("hoeveelheid3"), "calper100":data.get("calper3")}]
    }

    const stringify = JSON.stringify(jsonObject)
    console.log(jsonObject)


    fetch("restservices/gerechten/object", {
        method: 'POST',
        headers: UserService.setAuthHeader({}),
        body: stringify,
    })
        .then((response) => {
            if (!response.ok) {
                alert("Er bestaat al een gerecht met die naam! \nkies aub een andere naam voor dit gerecht.");
                form.naam.value = " ";
                return response;
            }else {
                alert("gerecht is toegevoegd.");
                window.location = "addGerechtObject.html";
                return response;
            }
        })
        .then(function (myjson){
            console.log(myjson)
        })
        .catch(error => console.log(error.message));
});