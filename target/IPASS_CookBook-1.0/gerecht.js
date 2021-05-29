import GerechtenService from '../service/Gerechten_service.js';

const queryString = location.search.substring(1)

function updatePagina(values) {
    const template = document.querySelector('#gerechtTemplate')
    const myTemplateClone = template.content.cloneNode(true);

    myTemplateClone.querySelector(".gerechtNaam").textContent = values.naam;
    myTemplateClone.querySelector(".categorie").textContent = values.categorie;
    myTemplateClone.querySelector(".beschrijving").textContent = values.beschrijving;
    myTemplateClone.querySelector(".bereidingstijd").textContent = values.bereidingstijd;
    myTemplateClone.querySelector(".bereidingswijze").textContent = values.bereidingswijze;
    myTemplateClone.querySelector(".calorieen").textContent = values.totaalCalorieen;

    for(let i = 0; i < values.alleIngredienten.length; i++){
        console.log(values.alleIngredienten[i])
        myTemplateClone.querySelector(".ingredienten").innerHTML += values.alleIngredienten[i].naam + " " + values.alleIngredienten[i].hoeveelheid + "gr <br>";
    }


    const insertPoint = document.querySelector(".gerechtBlok");
    insertPoint.appendChild(myTemplateClone);
}

GerechtenService.getGerecht(queryString)
    .then(data => {
        updatePagina(data);
    })
    .catch( error => {
        console.log("er is een fout opgetreden");
    });