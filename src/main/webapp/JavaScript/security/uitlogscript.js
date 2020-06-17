const logout_button = document.getElementById("logout-button")

logout_button.addEventListener('click',function () {
    location.replace("/index.html");
    window.sessionStorage.clear();
    window.alert("uitgelogd");
})