function removeVerzoek(datum) {
    fetch('/restservices/contactaanvragen/' + stringWithoutSpaces(datum), {
        method: "DELETE",
        headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
    })
        .then(response => {
            return response.json();
        }).then(
        location.reload())
        .catch(error => { console.log(error)
        });
}

function stringWithoutSpaces(text) {
    //debugger;
    return text.replace(/ /g,'_');
}

function addSingleContactverzoek(naam, mail, nummer, titel, beschrijving, datum) {
    // cloning the template
    //debugger;
    const single_contactverzoek_clone = document.importNode(document.getElementById("single-contactverzoek-template").content, true);
    const datum_zonder_spatie = stringWithoutSpaces(datum); // The ID attribute of an HTML element isn't supposed to contain white spaces.

    // applying the data
    //debugger;
    const contactverzoek_article = single_contactverzoek_clone.querySelector('.single-contactverzoek');
    contactverzoek_article.setAttribute('id', datum_zonder_spatie);

    const contactverzoek_titel = single_contactverzoek_clone.querySelector('.verzoek-title');
    contactverzoek_titel.innerHTML  = titel;

    const contactverzoek_date = single_contactverzoek_clone.querySelector('.verzoek-date');
    contactverzoek_date.innerHTML  = datum;

    const contactverzoek_klantnaam = single_contactverzoek_clone.querySelector('.verzoek-klantnaam');
    contactverzoek_klantnaam.innerHTML  = "<b>Klantnaam:</b>" + naam;

    const contactverzoek_beschrijving = single_contactverzoek_clone.querySelector('.verzoek-beschrijving');
    contactverzoek_beschrijving.innerHTML  = "<b>Beschrijving:</b>" + beschrijving;

    const contactverzoek_klantmail = single_contactverzoek_clone.querySelector('.verzoek-klantmail');
    contactverzoek_klantmail.innerHTML  = "<b>E-mail:</b>" + mail;

    const contactverzoek_klantnummer = single_contactverzoek_clone.querySelector('.verzoek-klantnummer');
    contactverzoek_klantnummer.innerHTML = "<b>Telefoon:</b>" + nummer;

    single_contactverzoek_clone.querySelector('.contactverzoek-remove-button').addEventListener('click', event => removeVerzoek(datum));

    //Maak fragment en voeg het toe aan de website
    const verzoekFragment = document.createDocumentFragment();
    verzoekFragment.append(single_contactverzoek_clone);

    const alleVerzoeken = document.querySelector('.contact-blok');
    alleVerzoeken.append(verzoekFragment);
    //debugger;
}

function initContactverzoeken() {
    fetch('/restservices/contactaanvragen', {
        method: "GET",
        headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
    })
        .then(response => {
            return response.json();
        })
        .then(response => {
            response.forEach(item => {
                //debugger;
                const naam = item.naam
                const mail = item.email
                const nummer = item.telefoon
                const title = item.titel
                const beschrijving = item.beschrijving
                const datum = item.datum
                //debugger;
                addSingleContactverzoek(naam, mail, nummer, title, beschrijving, datum)
            })
        })
        .catch(error => {
            alert("Error getting news" + error)
            window.replace.location("index.html")
        });
}

initContactverzoeken();