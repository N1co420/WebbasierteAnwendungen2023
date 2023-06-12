const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Project",
        "#login": "Login",
        "#register": "Register",
        "#username": "Username",
        "#password": "Password",
    
        "#filter": "Filter:",
        "#allProjects": "All projects",
        "#myProjects": "My projects",
        '#projectName': "Projectname",
    
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
    
        "#filter": "Filter:",
        "#allProjects": "Alle Projekte",
        "#myProjects": "Meine Projekte",
        '#projectName': "Projektname",
    
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
