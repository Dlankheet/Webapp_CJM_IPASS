const submitReviewButton = document.getElementById("submitReview");
submitReviewButton.addEventListener("click",(event) => reviewMaken(event))

function reviewMaken(event){
    event.preventDefault();
    let formData = new FormData(document.querySelector("#reviewblokform"));
    let encData = new URLSearchParams(formData.entries());
    console.log(formData.get("fname1"))
    console.log(formData.get("fmail1"))
    console.log(formData.get("fnummer1"))
    console.log(formData.get("frating1"))
    console.log(formData.get("fnote1"))

    if(formData.get("fname1") !== "" && formData.get("fmail1") !== "" && formData.get("fnummer1") !== "" && formData.get("frating1") !== "" && formData.get("fnote1") !== ""){
        fetch("/restservices/pendingReviews", {method: "POST", body: encData})
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
