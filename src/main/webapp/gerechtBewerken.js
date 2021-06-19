import GerechtenService from '../service/Gerechten_service.js';
import UserService from "./service/user_service.js";
UserService.requireUser()

const gerechtNaam = location.search.substring(1)

function updatePagina(values) {
    document.getElementById("naam").value = values.naam;
    document.getElementById("beschrijving").value = values.beschrijving;
    document.getElementById("bereidingstijd").value = values.bereidingstijd;
    document.getElementById("bereidingswijze").value = values.bereidingswijze;
    document.getElementById("categorie").value = values.categorie;
    document.getElementById("portie").value = values.portie;
}

function addIngredientList(data){
    const template = document.querySelector('#lijstIngredienten')
    const myTemplateClone = template.content.cloneNode(true);
    console.log(data)

    myTemplateClone.querySelector(".Ingredientnaam").textContent = "Naam: " + data.naam + ", ";
    myTemplateClone.querySelector(".hoeveelheid").textContent = "   Hoeveelheid: " + data.hoeveelheid + ", ";
    myTemplateClone.querySelector(".calsper").textContent = "   calorieen per 100: " + data.calper100;

    //delete
    const deleteButton = document.createElement("BUTTON");
    deleteButton.appendChild(document.createTextNode("Verwijder Ingredient"));
    deleteButton.addEventListener("click", (event) =>{
        GerechtenService.deleteIngredient(gerechtNaam, data.naam)
            .then(updateIngredientList)
    })
    myTemplateClone.querySelector(".delete").appendChild(deleteButton);

    const insertPoint = document.querySelector(".ingredientenlijst");
    insertPoint.appendChild(myTemplateClone);
}


function updateIngredientList(){
    const theList = document.querySelector(".ingredientenlijst");
    while (theList.firstChild) {
        theList.removeChild(theList.firstChild)
    }

    GerechtenService.getGerecht(gerechtNaam)
        .then(data => {
            const ingredienten = data.alleIngredienten;
            let i;
            for(i = 0; i < ingredienten.length; i++){
                addIngredientList(data.alleIngredienten[i])
            }
        })
        .catch( error => {
            console.log("er is een fout opgetreden")
        });
}


updateIngredientList()
GerechtenService.getGerecht(gerechtNaam)
    .then(data => {
        updatePagina(data);
    })
    .catch( error => {
        console.log("er is een fout opgetreden");
    });

document.querySelector('#submitPUT').addEventListener("click", async function(){
    const formData = new FormData(document.querySelector("#PUTgerechtForm"));
    const encData = new URLSearchParams(formData.entries());

    fetch(`restservices/gerechten/${gerechtNaam}`, {
        method: 'PUT',
        headers: UserService.setAuthHeader({}),
        body: encData,
    })
        .then((response) => {
            if (!response.ok) {
                alert("Er is iets fout gegaan");
                return response;
            }else {
                alert("gerecht is aangepast.");
                window.location = "gerechtenOverzichtAdmin.html";
                return response;
            }
        })
        .catch(error => console.log(error.message));
});

