document.getElementById("login-button").addEventListener("click", (event) => login(event));

function login(event) {
    var formData = new FormData(document.querySelector("#inlogform"));
    var encData = new URLSearchParams(formData.entries());

    fetch("restservices/authentication", { method: 'POST', body: encData})
        .then(function(response){
            if (response.ok){
                location.replace("adminpage.html")
                return response.json();
            }
            else alert("Wrong username/pasword");
        })
        .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
        .catch(error => console.log(error));
}

