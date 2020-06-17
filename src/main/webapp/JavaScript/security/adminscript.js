const loginbutton = document.getElementById("inlog-button")

if(sessionStorage.getItem("myJWT") !== null){
    loginbutton.textContent= "Admin";
    loginbutton.addEventListener('click',function (event) {
        event.preventDefault();
        window.location.replace("adminpage.html");
    })
}

