import GerechtenService from '../service/Gerechten_service.js';
import gerechten_service from "./service/Gerechten_service.js";

function addGerechtList(key, values){
    const template = document.querySelector('#subject-lijstGerechten')
    const myTemplateClone = template.content.cloneNode(true);

    myTemplateClone.querySelector(".gerechtNaam").textContent = values.naam;
    myTemplateClone.querySelector(".beschrijving").textContent = values.beschrijving;
    switch (values.categorie){
        case "vega":
            myTemplateClone.querySelector(".gerechtImg").setAttribute("src", "Resources/icons/vega.png" );
            break;
        case "vlees":
            myTemplateClone.querySelector(".gerechtImg").setAttribute("src", "Resources/icons/vlees.png" );
            break;
        case "vis":
            myTemplateClone.querySelector(".gerechtImg").setAttribute("src", "Resources/icons/vis.png" );
            break;
        default:
            myTemplateClone.querySelector(".gerechtImg").setAttribute("src", "Resources/icons/default.png" );
    }

    const insertPoint = document.querySelector(".gerechtenlijst");
    insertPoint.appendChild(myTemplateClone);
}

function updateGerechtenList() {
    GerechtenService.getGerechten()
        .then(data => {
            const keys = Object.keys(data);
            let i;
            for(i = 0; i < keys.length; i++){
                addGerechtList(keys[i], data[keys[i]])
            }
        })
        .catch( error => {
            console.log("er is een fout opgetreden")
        });

}

updateGerechtenList()