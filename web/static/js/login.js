const LANGUAGE = {
    "en-US": {
        "#login": "Login",
        "#logout": "Logout",
        "#register": "Register",
        "#username": "Username",
        "#password": "Password",
    },
    "de-DE": {
        "#login": "Einloggen",
        "#logout": "Abmelden",
        "#register": "Registrieren",
        "#username": "Benutzername",
        "#password": "Passwort",
    }
}

let userLang = navigator.language || navigator.userLanguage
let langMap = LANGUAGE[userLang] || LANGUAGE["en-US"]

for (const key in langMap) {
    document.body.innerHTML = document.body.innerHTML.replaceAll(key, langMap[key])
}

const onLogin = () => {
    document.getElementById('user-name-input').disabled = !document.getElementById('user-name-input').disabled;
    document.getElementById('password-input').disabled = !document.getElementById('password-input').disabled;
    document.getElementById('login-button').textContent = langMap["#logout"] === document.getElementById('login-button').textContent ? langMap["#login"] : langMap["#logout"];
    document.getElementById('register-button').hidden = !document.getElementById('register-button').hidden;
    let cookieName = "login"
    let cookieValue = langMap["#logout"] === document.getElementById('login-button').textContent
    let cookieExpire = new Date(Date.now() + (1000 * 60 * 60 * 24)); // 24 hours
    let cookiePath = "path=/"
    document.cookie = cookieName + "=" + cookieValue + "; expires=" + cookieExpire.toUTCString() + ";" + cookiePath
    console.log("Cookie set: ", document.cookie)
    console.log(cookieName + "=" + cookieValue + "; expires=" + cookieExpire.toUTCString() + ";" + cookiePath)
    // set cookie login to true that expires in 24 hours
}

// if login cookie is set, then login
if (document.cookie.includes("login=true")) {
    onLogin()
}


onRegister = () => {
    const userName = document.getElementById('user-name-input').value;
    const password = document.getElementById('password-input').value;
    const xhr = new XMLHttpRequest();
    if (userName.trim() === '' || password.trim() === '') {
        alert('Please enter your user name and password');
        return;
    }
    xhr.open('POST', 'api/register');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        userName: userName,
        password: password
    }));

    xhr.onload = function () {
        if (xhr.status === 200) {
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
                // set session
                // check if sessionStorage is allowed
                if (typeof (Storage) !== 'undefined') {
                    sessionStorage.setItem('userName', userName);
                    sessionStorage.setItem('userId', response.userId);
                    sessionStorage.setItem('token', response.token);
                } else {
                    alert('Sorry, your browser does not support Web Storage...');
                }
            } else {
                alert(response.message);
            }
        } else {
            alert('Request failed.  Returned status of ' + xhr.status);
        }
    };
}

// If pressed on login button
document.getElementById('login-button').addEventListener('click', function () {
    onLogin();
});

// If pressed on register button
document.getElementById('register-button').addEventListener('click', function () {
    onRegister();
});