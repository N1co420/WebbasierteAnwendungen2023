
const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Project",
        "#login": "Login",
        "#register": "Register",
        "#username": "Username",
        "#password": "Password",

        "#welcome1": "Welcome to ",
        "#welcome2": "Taskhelper",
        "#shortDescr": "Short Description",
        "#intro": "What is TaskHelper and how does it work? Take a look at our tutorial!",
        "#newestProjects": "Newest projects",

        "#imprint": "Imprint",
        "#privacy": "Privacy",
        "#disclaimer": "Disclaimer",
        "#contact": "Contact",
    },
    "de-DE": {
        "#projects": "Projekte",
        "#newProject": "Neues Projekt",
        "#login": "Einloggen",
        "#register": "Registrieren",
        "#username": "Benutzername",
        "#password": "Passwort",

        "#welcome1": "Willkommen bei ",
        "#welcome2": "StudBoard",
        "#shortDescr": "Kurzbeschreibung",
        "#intro": "Was ist TaskHelper und wie funktioniert es? Schau dir unser Tutorial an!",
        "#newestProjects": "Neueste Projekte",

        "#imprint": "Impressum",
        "#privacy": "Datenschutz",
        "#disclaimer": "Haftungsausschluss",
        "#contact": "Kontakt",
    }
}

let userLang = navigator.language || navigator.userLanguage
let langMap = LANGUAGE[userLang] || LANGUAGE["en-US"]
console.log("The language is: " + userLang)

for (const key in langMap) {
    document.body.innerHTML = document.body.innerHTML.replaceAll(key, langMap[key])
}

export {LANGUAGE}
