import GerechtenService from '../service/Gerechten_service.js';
import UserService from "./service/user_service.js";
UserService.requireUser()

const gerechtNaam = location.search.substring(1)

function wijzigIngredientModal(data){
    console.log(data)
    const dialogNode = document.querySelector('#wijzigIngredient');
    const dialogContent = dialogNode.querySelector('.contentIngr');


    //formNode
    const FormNode = document.createElement('form');
    const formid = document.createAttribute("id");
    formid.value = "PutIngredientForm";
    FormNode.innerHTML = "<h2> wijzig hoeveelheid van gekozen ingredient </h2>"
    FormNode.setAttributeNode(formid)

    //hoeveelheidlabel
    const hoeveelheidlabel = document.createElement('label');
    const hoevelheidFor = document.createAttribute("for");
    hoevelheidFor.value = "hoeveelheidIngr";
    hoeveelheidlabel.innerHTML = "</br> hoeveelheid: "
    hoeveelheidlabel.setAttributeNode(hoevelheidFor)

    const hoeveelheidInput = document.createElement('input');
    const hoeveelheidInputType = document.createAttribute("type");
    hoeveelheidInputType.value = "number";
    const hoeveelheidInputid = document.createAttribute("id");
    hoeveelheidInputid.value = "hoeveelheidIngr";
    const hoeveelheidInputname = document.createAttribute("name");
    hoeveelheidInputname.value = "hoeveelheidIngr";

    hoeveelheidInput.setAttributeNode(hoeveelheidInputType)
    hoeveelheidInput.setAttributeNode(hoeveelheidInputid)
    hoeveelheidInput.setAttributeNode(hoeveelheidInputname)
    hoeveelheidlabel.append(hoeveelheidInput)
    FormNode.append(hoeveelheidlabel)



    dialogContent.append(FormNode);
    document.getElementById("hoeveelheidIngr").value = data.hoeveelheid;

    //button
    const submitButton = document.createElement('button');
    submitButton.innerHTML = "Wijzig";
    const buttonID = document.createAttribute("id");
    buttonID.value= "PUTIngredient";

    FormNode.append(submitButton)
    dialogContent.append(FormNode);

    submitButton.addEventListener("click", e => {
        e.preventDefault();
        const formData = new FormData(document.querySelector("#PutIngredientForm"));
        const encData = new URLSearchParams(formData.entries());

        fetch(`restservices/gerechten/${gerechtNaam}/${data.naam}`,{method: 'PUT', body: encData,})
            .then((response) =>{
                if (!response.ok) {
                    alert("Er ging iets mis.");
                    return response;
                }else{
                    window.location = "/GerechtBewerken.html?" + gerechtNaam;
                    return response;
                }})
            .catch(error => console.log(error.message));
    })

    //close
    const closeButton = document.querySelector('#wijzigIngredient .close');
    closeButton.addEventListener('click', _event=>{
        dialogNode.close();
        dialogContent.textContent = "";
    });

    dialogNode.showModal();

}

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
    // console.log(data)

    myTemplateClone.querySelector(".Ingredientnaam").textContent =  data.naam + ", ";
    myTemplateClone.querySelector(".hoeveelheid").textContent = " " + data.hoeveelheid + " gr/ml ";
    // myTemplateClone.querySelector(".hoeveelheid").textContent = "   Hoeveelheid: " + data.hoeveelheid + ", ";
    //myTemplateClone.querySelector(".calsper").textContent = "   calorieen per 100 gr/ml: " + data.calper100;

    //wijigHoeveelheid
    const IngredientModal = myTemplateClone.querySelector('#bewerkHoeveelheid');
    IngredientModal.addEventListener('click', () =>  wijzigIngredientModal(data));

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

