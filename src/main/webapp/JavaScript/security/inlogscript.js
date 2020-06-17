document.getElementById("login-button").addEventListener("click", (event) => login(event));

function login(event) {
    event.preventDefault();
    var formData = new FormData(document.querySelector("#inlogform"));
    var encData = new URLSearchParams(formData.entries());

    fetch("restservices/authentication", { method: 'POST', body: encData})
        .then(function(response){
            if (response.ok){
                return response.json();
            }
            else alert("Wrong username/pasword");
        })
        .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
        .then(()=>location.replace("adminpage.html"))
        .catch(error => console.log(error));
}

