document.getElementById("submitContact").addEventListener("click", contactverzoekMaken)

function contactverzoekMaken(){
    let formData = new FormData(document.querySelector("#contactenblok"));
    let encData = new URLSearchParams(formData.entries());


    fetch("/restservices/contactaanvragen", {method: "POST", body: encData})
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }
        })
        .then(data => console.log(data))
        .then(alert("Contactverzoek verzonden!"))
        .catch(console.error)

}