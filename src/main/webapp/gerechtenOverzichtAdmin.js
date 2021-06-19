import GerechtenService from '../service/Gerechten_service.js';

function addIngredientDialog(data) {
    const dialogNode = document.querySelector('#addIngredientDialog');
    const dialogContent = dialogNode.querySelector('.content');

    //formNode
    const FormNode = document.createElement('form');
    const formid = document.createAttribute("id");
    formid.value = "addIngredientForm";
    FormNode.innerHTML = "<h2> Voeg ingredient toe </h2>"
    FormNode.setAttributeNode(formid)

    const br = document.createElement("br")

    //naaminput
    const naamLabel = document.createElement('label');
    const naamfor = document.createAttribute("for");
    naamfor.value = "naam";
    naamLabel.innerHTML = "</br> naam ingredient: "
    naamLabel.setAttributeNode(naamfor)

    const naamInput = document.createElement('input');
    const naamInputType = document.createAttribute("type");
    naamInputType.value = "text";
    const naamInputid = document.createAttribute("id");
    naamInputid.value = "naam";
    const naamInputname = document.createAttribute("name");
    naamInputname.value = "naam";

    naamInput.setAttributeNode(naamInputType)
    naamInput.setAttributeNode(naamInputid)
    naamInput.setAttributeNode(naamInputname)
    naamLabel.append(naamInput)
    FormNode.append(naamLabel)

    //hoeveelheidlabel
    const hoeveelheidlabel = document.createElement('label');
    const hoevelheidFor = document.createAttribute("for");
    hoevelheidFor.value = "hoeveelheid";
    hoeveelheidlabel.innerHTML = "</br> hoeveelheid: "
    hoeveelheidlabel.setAttributeNode(hoevelheidFor)

    const hoeveelheidInput = document.createElement('input');
    const hoeveelheidInputType = document.createAttribute("type");
    hoeveelheidInputType.value = "number";
    const hoeveelheidInputid = document.createAttribute("id");
    hoeveelheidInputid.value = "hoeveelheid";
    const hoeveelheidInputname = document.createAttribute("name");
    hoeveelheidInputname.value = "hoeveelheid";

    hoeveelheidInput.setAttributeNode(hoeveelheidInputType)
    hoeveelheidInput.setAttributeNode(hoeveelheidInputid)
    hoeveelheidInput.setAttributeNode(hoeveelheidInputname)
    hoeveelheidlabel.append(hoeveelheidInput)
    FormNode.append(hoeveelheidlabel)

    //calperlabel
    const calperlabel = document.createElement('label');
    calperlabel.innerHTML = "</br>";
    const calperFor = document.createAttribute("for");
    calperFor.value = "calper";
    calperlabel.innerHTML = "</br> cals per 100: "
    calperlabel.setAttributeNode(calperFor)

    const calperInput = document.createElement('input');
    const calperInputType = document.createAttribute("type");
    calperInputType.value = "number";
    const calperInputid = document.createAttribute("id");
    calperInputid.value = "calper";
    const calperInputname = document.createAttribute("name");
    calperInputname.value = "calper";

    calperInput.setAttributeNode(calperInputType)
    calperInput.setAttributeNode(calperInputid)
    calperInput.setAttributeNode(calperInputname)
    calperlabel.append(calperInput)
    FormNode.append(calperlabel)
    FormNode.append(br)


    //button
    const submitButton = document.createElement('button');
    submitButton.innerHTML = "verzend";
    const buttonID = document.createAttribute("id");
    buttonID.value= "submitIngredient";

    FormNode.append(submitButton)
    dialogContent.append(FormNode);

    submitButton.addEventListener("click", e => {
        e.preventDefault();
        const gerechtNaam = data.naam;
        const formData = new FormData(document.querySelector("#addIngredientForm"));
        const encData = new URLSearchParams(formData.entries());

        fetch(`restservices/gerechten/${gerechtNaam}/ingredient`,{method: 'POST', body: encData,})
            .then((response) =>{
                if (!response.ok) {
                    alert("Er ging iets mis.");
                    return response;
                }else{
                    window.location = "/gerechtenOverzichtAdmin.html";
                    return response;
                }})
                .catch(error => console.log(error.message));
    })



    const closeButton = document.querySelector('#addIngredientDialog .close');
    closeButton.addEventListener('click', _event=>{
        dialogNode.close();
        dialogContent.textContent = "";
    });

    dialogNode.showModal();
}

function addGerechtList(key, values){
    const template = document.querySelector('#lijstGerechten')
    const myTemplateClone = template.content.cloneNode(true);

    myTemplateClone.querySelector(".gerechtNaam").textContent = values.naam;
    myTemplateClone.querySelector(".gerechtBeschrijving").textContent = values.beschrijving;
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

    //delete
    const deleteButton = myTemplateClone.querySelector('.deleteButton');
    deleteButton.addEventListener("click", () => {
    GerechtenService.deleteGerecht(values.naam)
        .then(updateGerechtenList)
    });

    //addIngredientModal
    const showIngredientModal = myTemplateClone.querySelector('.addIngredient');
    showIngredientModal.addEventListener('click', _event => addIngredientDialog(values));

    //bewerktIngredientModal
    const bewerktGerecht = myTemplateClone.querySelector('.bewerkGerecht');
    bewerktGerecht.addEventListener('click', (event) => window.location = "/GerechtBewerken.html?" + values.naam);

    const insertPoint = document.querySelector(".gerechtenlijst");
    insertPoint.appendChild(myTemplateClone);
}

function updateGerechtenList() {
    const theList = document.querySelector(".gerechtenlijst");
    while (theList.firstChild) {
        theList.removeChild(theList.firstChild)
    }

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