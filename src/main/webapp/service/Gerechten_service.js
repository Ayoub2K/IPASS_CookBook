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

}