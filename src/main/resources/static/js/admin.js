const form = document.querySelector("form");
const password = document.querySelector('[name="password"]');
const confirmPassword = document.querySelector("#confirmPassword");

form.addEventListener("submit", function(e){

    if(password.value !== confirmPassword.value){
        e.preventDefault();
        alert("Las contraseñas no coinciden");
    }

});