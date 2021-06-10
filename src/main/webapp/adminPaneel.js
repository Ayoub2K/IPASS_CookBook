import GerechtenService from '../service/Gerechten_service.js';

document.querySelector('#submit').addEventListener("click", async function(){
   const formData = new FormData(document.querySelector("#POSTgerechtForm"));
   const encData = new URLSearchParams(formData.entries());

   console.log("sendbutton!")

   fetch("restservices/gerechten/gerecht", {method: 'POST', body: encData})
       .then(response => response.json())
       .then(function (myJson){
           console.log(myJson);
       })

});