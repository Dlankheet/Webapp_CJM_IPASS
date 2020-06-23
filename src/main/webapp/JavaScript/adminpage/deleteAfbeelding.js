function removeItem(titelzonderspatie, event, fetchLocation) {
    event.preventDefault();
    fetch(fetchLocation + "/" + titelzonderspatie, {
        method: "DELETE",
        headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
    })
        .then(response => {
            return response.json();
        }).then(
        location.reload())
        .then(alert("Foto is succesvol verwijdert!"))
        .catch(error => {
            console.log(error)
            alert("Er is iets mis gegaan. Neem contact op met de beheerder.")
        });
}

function stringWithoutSpaces(text) {
    return text.replace(/ /g,'_');
}

function addSingleItem(titel, fetchLocation) {
    // cloning the template
    const single_delete_item = document.importNode(document.getElementById("singleDeleteItem").content, true);
    const titelzonderspatie = stringWithoutSpaces(titel); // The ID attribute of an HTML element isn't supposed to contain white spaces.

    // applying the data-
    const item = single_delete_item.querySelector('.single-item');
    item.setAttribute('id', titelzonderspatie);

    const titelspan = item.querySelector('#titel-span');
    titelspan.innerHTML  = titel;

    item.querySelector('#remove-item').addEventListener('click', event => removeItem(titelzonderspatie, event, fetchLocation));

    //Maak fragment en voeg het toe aan de website
    const titelFragment = document.createDocumentFragment();
    titelFragment.append(item);

    const titelBlok = document.querySelector('.delete-foto-block');
    titelBlok.append(titelFragment);
}

function maakItems(fetchLocation) {
    fetch(fetchLocation, {
        method: "GET",
        headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
    })
        .then(response => {
            return response.json();
        })
        .then(response => {
            response.forEach(item => {
                const titel = item.titel
                addSingleItem(titel, fetchLocation)
            })
        })
        .catch(error => {
            alert("Error getting news" + error)
        });
}

function haalfotoLijst(event){
    event.preventDefault();
    let fetchLocation = "";
    if(document.getElementById("brancheSelector-delete").value === "elektra"){
        fetchLocation = "/restservices/elektraslider"}
    else fetchLocation = "/restservices/legslider";
    maakItems(fetchLocation);
}

document.getElementById("laadFotos").addEventListener("click", (event) => haalfotoLijst(event));