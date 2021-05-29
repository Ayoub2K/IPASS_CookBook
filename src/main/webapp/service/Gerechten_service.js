const CONFLICT_ERROR = 409;
const BASE_URL = '/restservices/gerechten';

export default class gerechten_service{

    static getGerechten() {
        return fetch(BASE_URL)
            .then((response) => response.json());
    }

}