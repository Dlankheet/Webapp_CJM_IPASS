function stringWithoutSpaces(text) {
    //debugger;
    return text.replace(/ /g, '_');
}

function addReview(rating, beschrijving, datum) {
    // cloning the template
    const template_clone = document.importNode(document.getElementById("onpage-review-template").content, true);
    const datum_zonder_spatie = stringWithoutSpaces(datum); // The ID attribute of an HTML element isn't supposed to contain white spaces.

    // applying the data-
    const review_class = template_clone.querySelector('.onpage-review');
    review_class.setAttribute('id', datum_zonder_spatie);

    const review_titel = template_clone.querySelector('.review-aantal-sterren');
    if (rating === 5) {
        review_titel.innerHTML = "<img src='/images/five-stars.png' alt='Five Stars' width='209' height='70'>"
    } else if (rating === 4) {
        review_titel.innerHTML = "<img src='/images/four-stars.png' alt='Four Stars' width='209' height='70'>"
    } else if (rating === 3) {
        review_titel.innerHTML = "<img src='/images/three-stars.png' alt='Three Stars' width='209' height='70'>"
    } else if (rating === 2) {
        review_titel.innerHTML = "<img src='/images/two-stars.png' alt='Two Stars' width='209' height='70' >"
    } else {
        review_titel.innerHTML = "<img src='/images/one-star.png' alt='One Star' width='209' height='70' >";
    }


    const review_date = template_clone.querySelector('.review-date');
    review_date.innerHTML = datum;

    const review_beschrijving = template_clone.querySelector('.review-toelichting');
    review_beschrijving.innerHTML = "<b>Beschrijving:</b>" + beschrijving;


    //Maak fragment en voeg het toe aan de website
    const reviewFragment = document.createDocumentFragment();
    reviewFragment.append(template_clone);

    const alleReviews = document.querySelector('#reviews');
    alleReviews.append(reviewFragment);
}

function initreviews() {
    fetch('/restservices/acceptedReviews', {
        method: "GET",
        headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
    })
        .then(response => {
            return response.json();
        })
        .then(response => {
            response.forEach(item => {
                const aantalSterren = item.aantalSterren
                const beschrijving = item.onderbouwing
                const datum = item.datum
                addReview(aantalSterren, beschrijving, datum)
            })
        })
        .catch(error => {
            alert("Fout opgetreden: " + error)
        });
}

initreviews();