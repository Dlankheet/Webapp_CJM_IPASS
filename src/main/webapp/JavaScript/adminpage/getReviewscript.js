function removeReview(datum, event) {
    event.preventDefault();
    fetch('/restservices/pendingReviews/' + stringWithoutSpaces(datum), {
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
function toevoegenReview(datum, event){
    event.preventDefault();
    fetch('/restservices/acceptedReviews/' + stringWithoutSpaces(datum), {
        method: "PUT",
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

function addReview(naam, mail, nummer, rating, beschrijving, datum) {
    // cloning the template
    const single_review_clone = document.importNode(document.getElementById("single-review-template").content, true);
    const datum_zonder_spatie = stringWithoutSpaces(datum); // The ID attribute of an HTML element isn't supposed to contain white spaces.

    // applying the data-
    const review_article = single_review_clone.querySelector('.single-review');
    review_article.setAttribute('id', datum_zonder_spatie);

    const review_titel = single_review_clone.querySelector('.verzoek-title');
    review_titel.innerHTML  = "Aantal ontvangen sterren:" + rating;

    const review_date = single_review_clone.querySelector('.verzoek-date');
    review_date.innerHTML  = datum;

    const review_klantnaam = single_review_clone.querySelector('.verzoek-klantnaam');
    review_klantnaam.innerHTML  = "<b>Klantnaam:</b>" + naam;

    const review_beschrijving = single_review_clone.querySelector('.verzoek-beschrijving');
    review_beschrijving.innerHTML  = "<b>Beschrijving:</b>" + beschrijving;

    const review_klantmail = single_review_clone.querySelector('.verzoek-klantmail');
    review_klantmail.innerHTML  = "<b>E-mail:</b>" + mail;

    const review_klantnummer = single_review_clone.querySelector('.verzoek-klantnummer');
    review_klantnummer.innerHTML = "<b>Telefoon:</b>" + nummer;

    single_review_clone.querySelector('.review-remove-button').addEventListener('click', event => removeReview(datum, event));
    single_review_clone.querySelector('.review-toevoegen-button').addEventListener('click', event => toevoegenReview(datum, event));

    //Maak fragment en voeg het toe aan de website
    const verzoekFragment = document.createDocumentFragment();
    verzoekFragment.append(single_review_clone);

    const alleVerzoeken = document.querySelector('.review-blok');
    alleVerzoeken.append(verzoekFragment);
}

function initreviews() {
    fetch('/restservices/pendingReviews', {
        method: "GET",
        headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
    })
        .then(response => {
            return response.json();
        })
        .then(response => {
            response.forEach(item => {
                const naam = item.naam
                const mail = item.email
                const nummer = item.telefoon
                const aantalSterren = item.aantalSterren
                const beschrijving = item.onderbouwing
                const datum = item.datum
                addReview(naam, mail, nummer, aantalSterren, beschrijving, datum)
            })
        })
        .catch(error => {
            location.replace("/index.html")
            alert("Error getting news" + error)
        });
}

initreviews();