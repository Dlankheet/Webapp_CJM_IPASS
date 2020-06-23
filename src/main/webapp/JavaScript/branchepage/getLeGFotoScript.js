function addFoto(fotoBase64, titel, beschrijving) {
    // cloning the template
    const fotoTemplate = document.importNode(document.getElementById("newSlide").content, true);

    // applying the data-
    const fotoDiv = fotoTemplate.querySelector('.slide');
    fotoDiv.setAttribute('style', "background: url("+ fotoBase64 +") no-repeat center top/cover;");

    const foto_titel = fotoTemplate.querySelector('.fotoTitel');
    foto_titel.innerHTML  = titel;

    const foto_beschrijving = fotoTemplate.querySelector('.fotoBeschrijving');
    foto_beschrijving.innerHTML  = beschrijving;

    //Maak fragment en voeg het toe aan de website
    const fotoFragment = document.createDocumentFragment();
    fotoFragment.append(fotoTemplate);

    const slider = document.querySelector('.slider');
    slider.append(fotoFragment);
}

function initFotos() {
    fetch('/restservices/legslider', {method: "GET"})
        .then(response => {
            return response.json();
        })
        .then(response => {
            response.forEach(item => {
                const fotoBase64 = item.fotoBase64
                const titel = item.titel
                const beschrijving = item.beschrijving
                addFoto(fotoBase64, titel, beschrijving)
            })
        })
        .catch(error => {
            alert("Error getting news" + error)
        });
}

initFotos()
