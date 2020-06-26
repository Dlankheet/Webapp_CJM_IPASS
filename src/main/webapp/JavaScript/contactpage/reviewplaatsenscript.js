const submitReviewButton = document.getElementById("submitReview");
submitReviewButton.addEventListener("click",(event) => reviewMaken(event))

function validateEmail(email) {
    var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return regex.test(email);
}

function validatePhone(phone) {
    var vast_nummer = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$/;
    var mobiel_nummer = /^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/i;
    return (vast_nummer.test(phone) || mobiel_nummer.test(phone));
}

function reviewMaken(event){
    event.preventDefault();
    let formData = new FormData(document.querySelector("#reviewblokform"));
    let encData = new URLSearchParams(formData.entries());

    if(formData.get("fname1") !== "" && formData.get("fmail1") !== "" && formData.get("fnummer1") !== "" && formData.get("frating1") !== "" && formData.get("fnote1") !== ""){
        if(validateEmail(formData.get("fmail1")) === false ){
            alert("Voer geldig e-mail adres in!")
        }else if(validatePhone(formData.get("fnummer1")) === false){
            alert("Voer geldig telefoon nummer in!")
        }else if(formData.get("frating1") < 1 || formData.get("frating1" > 5)){
            alert("Voer een geldige rating in!")}
        else{
        fetch("/restservices/pendingReviews", {method: "POST", body: encData})
            .then(function (response) {
                if (response.ok) {
                    alert("Contactverzoek is verzonden!")
                    document.querySelector("#reviewblokform").reset();
                    return response.json();
                }else{
                    alert("Voer geldige informatie in.")
                }

            })
            .then(data => console.log(data))
    }}else{
        alert("Niet alle velden zijn ingevuld!")
    }
}
