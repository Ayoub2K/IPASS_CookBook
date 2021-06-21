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
        myTemplateClone.querySelector(".ingredienten").innerHTML += values.alleIngredienten[i].naam + " <span class='hoeveelheid'>" + values.alleIngredienten[i].hoeveelheid + "</span> gr <br>";
    }

    const insertPoint = document.querySelector(".gerechtBlok");
    insertPoint.appendChild(myTemplateClone);

    const fav = document.querySelector("#favoButton");
    fav.addEventListener("click", e => isFavoriet())

    const prnt = document.querySelector("#printbutton");
    prnt.addEventListener("click", e => Print())
}

function Print(){
    window.print();
}

function isFavoriet(){
    GerechtenService.isFavoriet(queryString);
    alert("Danku!")
}

function berekenPortie(data){
    const hvl = document.querySelectorAll(".hoeveelheid");
    const portieGroote = data.portie;
    const opgevraageWaarde = document.getElementById("aantalPorties").value
    for(let i = 0; i < hvl.length; i++){
        const hoeveelheidIngredient = data.alleIngredienten[i].hoeveelheid;
        const waarde = (hoeveelheidIngredient / portieGroote) * opgevraageWaarde;
        hvl[i].innerHTML = waarde
    }
}

GerechtenService.getGerecht(queryString)
    .then(data => {
        updatePagina(data);
        document.querySelector("#aantalPorties").value = data.portie ;
        const portieRefresh = document.querySelector("#verversPorties")
        portieRefresh.addEventListener("click", e => berekenPortie(data))
    })
    .catch( error => {
        console.log("fout:" + error.message);
    });
