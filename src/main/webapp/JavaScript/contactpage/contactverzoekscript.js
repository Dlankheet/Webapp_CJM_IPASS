const submitContactButton = document.getElementById("submitContact");
submitContactButton.addEventListener("click", (event) => contactverzoekMaken(event))


function validateEmail(email) {
    var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return regex.test(email);
}

function validatePhone(phone) {
    var vast_nummer = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$/;
    var mobiel_nummer = /^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/i;
    return (vast_nummer.test(phone) || mobiel_nummer.test(phone));
}

function contactverzoekMaken(event) {
    event.preventDefault();
    let formData = new FormData(document.querySelector("#contactenblokform"));
    let encData = new URLSearchParams(formData.entries());
    if (formData.get("name") !== "" && formData.get("fmail") !== "" && formData.get("fnummer") !== "" && formData.get("ftitle") !== "" && formData.get("fnote") !== "") {
        if(validateEmail(formData.get("fmail")) === false ){
            alert("Voer geldig e-mail adres in!")
        }else if(validatePhone(formData.get("fnummer")) === false){
            alert("Voer geldig telefoon nummer in!")
        }else{
        fetch("/restservices/contactaanvragen", {method: "POST", body: encData})
            .then(function (response) {
                if (response.ok) {
                    alert("Contactverzoek is verzonden!")
                    document.querySelector("#contactenblokform").reset();
                    return response.json();
                } else {
                    alert("Voer geldige informatie in.")
                }

            })
            .then(data => console.log(data))
    }} else {
        alert("Niet alle velden zijn ingevuld!")
    }
}
