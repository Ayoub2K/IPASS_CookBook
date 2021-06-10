import GerechtenService from '../service/Gerechten_service.js';

document.querySelector('#submit').addEventListener("click", async function(){
    const formData = new FormData(document.querySelector("#POSTgerechtForm"));
    const encData = new URLSearchParams(formData.entries());

    fetch("restservices/gerechten/gerecht", {
       method: 'POST',
       body: encData,
   })
       .then((response) => {
           if (!response.ok) {
               throw new Error(response.status);
           }
           return response;
       })
       .then(function (myjson){
           console.log(myjson)
       });
});