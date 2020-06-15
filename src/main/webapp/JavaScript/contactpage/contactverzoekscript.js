const submitContactButton = document.getElementById("submitContact");
submitContactButton.addEventListener("click",(event) => contactverzoekMaken(event))

function contactverzoekMaken(event){
    event.preventDefault();
    let formData = new FormData(document.querySelector("#contactenblokform"));
    let encData = new URLSearchParams(formData.entries());
    if(formData.get("name") !== "" && formData.get("fmail") !== "" && formData.get("fnummer") !== "" && formData.get("ftitle") !== "" && formData.get("fnote") !== ""){
        fetch("/restservices/contactaanvragen", {method: "POST", body: encData})
            .then(function (response) {
                if (response.ok) {
                    alert("Contactverzoek is verzonden!")
                    return response.json();
                }else{
                    alert("Voer geldige informatie in.")
                }

            })
            .then(data => console.log(data))
    }else{
        alert("Niet alle velden zijn ingevuld!")
    }
    }
