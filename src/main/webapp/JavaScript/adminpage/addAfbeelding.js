const toBase64 = file => new Promise((resolve, reject) => {
    if (file === undefined) resolve("");

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.addEventListener("load", () => resolve(reader.result));
    reader.addEventListener("error", error => reject(error));
});

async function sendFoto(event) {
    event.preventDefault();
    let fetchLocation = "";
    let file = document.querySelector("#fileupload").files[0];
    let formData = new FormData(document.querySelector("#fotoslide-add-form"));
    formData.append("fotoBase64", (await toBase64(file)));

    let encData = new URLSearchParams(formData.entries());
    if (formData.get("titel") !== "" && formData.get("beschrijving") !== "") {
        if(formData.get("brancheSelector") === "elektra"){
            fetchLocation = "/restservices/elektraslider"}
        else fetchLocation = "/restservices/legslider";

        fetch(fetchLocation , {
            method: "POST",
            body: encData,
            headers: {"Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")}
        })
            .then(function (response) {
                if (response.ok) {
                    alert("Foto is geplaatst!")
                    document.querySelector("#fotoslide-add-form").reset();
                    return response.json();
                } else {
                    alert("Voer geldige informatie in.")
                }

            })
            .then(data => console.log(data))
    } else {
        alert("Voer alle velden in!")
    }
}

const submitFotoButton = document.getElementById("submitFoto");
submitFotoButton.addEventListener("click", (event) => sendFoto(event))
