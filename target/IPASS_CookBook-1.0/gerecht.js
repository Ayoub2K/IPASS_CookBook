import GerechtenService from '../service/Gerechten_service.js';

const queryString = location.search.substring(1)

function updatePagina(values) {
    const template = document.querySelector('#gerechtTemplate')
    const myTemplateClone = template.content.cloneNode(true);

    myTemplateClone.querySelector(".gerechtNaam").textContent = values.naam;
    myTemplateClone.querySelector(".categorie").textContent = "categorie: " + values.categorie;
    myTemplateClone.querySelector(".beschrijving").textContent = "beschrijving: " + values.beschrijving;
    myTemplateClone.querySelector(".bereidingstijd").textContent = "bereidingstijd: " + values.bereidingstijd;
    myTemplateClone.querySelector(".bereidingswijze").textContent = "bereidingswijze: " + values.bereidingswijze;
    myTemplateClone.querySelector(".calorieen").textContent = "calorieen: " + values.totaalCalorieen;

    for(let i = 0; i < values.alleIngredienten.length; i++){
        myTemplateClone.querySelector(".ingredienten").innerHTML += values.alleIngredienten[i].naam + " " + values.alleIngredienten[i].hoeveelheid + "gr <br>";
    }


    const insertPoint = document.querySelector(".gerechtBlok");
    insertPoint.appendChild(myTemplateClone);

    const fav = document.querySelector("#favoButton");
    fav.addEventListener("click", e => isFavoriet())
}

function isFavoriet(){
    GerechtenService.isFavoriet(queryString);
    alert("Danku")
}

GerechtenService.getGerecht(queryString)
    .then(data => {
        console.log(data)
        updatePagina(data);
    })
    .catch( error => {
        console.log("er is een fout opgetreden");
    });