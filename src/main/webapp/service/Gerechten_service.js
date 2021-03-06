export default class gerechten_service{

    static getGerechten() {
        return fetch('/restservices/gerechten')
            .then((response) => response.json());
    }

    static getGerecht(naam){
        return fetch('/restservices/gerechten/' + naam)
            .then((response) => response.json())
            .catch(error => {
                console.log("gerecht niet gevonden")
            })
    }

    static getFavorieten() {
        return fetch('/restservices/gerechten/favorieten')
            .then((response) => response.json());
    }

    static isFavoriet(naam){
        return fetch(`/restservices/gerechten/favoriet/${naam}`)
            .then(function (response){
                if( response.ok ) {
                    console.log("Favoriet +1")
                }
                else{ alert("Er is iets mis gegaan")}
            })
            .catch(error => {
                alert("Er is iets mis gegaan 2")
            })
    }

    static deleteGerecht(naam){
        return fetch(`/restservices/gerechten/${naam}`,{ method : "DELETE"})
            .then(function (response){
                if( response.ok ) {
                    console.log("Gerecht is verwijderd")
                }
                else{ alert("Gerecht niet gevonden 1")}
            })
            .catch(error => {
                alert("Gerecht niet gevonden 2")
            })
    }

    static deleteIngredient(gerechtNaam, ingredientNaam){
        return fetch(`/restservices/gerechten/${gerechtNaam}/${ingredientNaam}`,{ method : "DELETE"})
            .then(function (response){
                if( response.ok ) {
                    console.log("Ingredient is verwijderd")
                }
                else{ alert("Ingredient niet gevonden 1")}
            })
            .catch(error => {
                alert("Ingredient niet gevonden 2")
            })
    }

}