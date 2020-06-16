const loginbutton = document.getElementById("inlog-button")
const header = document.getElementsByName("header")

if(sessionStorage.getItem("myJWT") !== null){
    loginbutton.textContent= "Uitloggen";
    loginbutton.addEventListener('click',function () {
        window.sessionStorage.clear();
        window.location.replace("index.html");
        window.alert("uitgelogd");
    })
}