const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Project",
        "#login": "Login",
        "#register": "Register",
        "#username": "Username",
        "#password": "Password",
    
        "#projectTitle": "Project title",
        "#startDate": "Start date",
        "#endDate": "End date",
        "#shortDescr": "Short Description",
        "#longDescr": "Long Description",
        "#goal1": "1. project goal",
        "#goal2": "2. project goal",
        "#chooseLogo": "Choose logo...",
        "#save": "Save",
    
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
    
        "#projectTitle": "Projekttitel",
        "#startDate": "Startdatum",
        "#endDate": "Enddatum",
        "#shortDescr": "Kurzbeschreibung",
        "#longDescr": "Langbeschreibung",
        "#goal1": "1. Projektziel",
        "#goal2": "2. Projektziel",
        "#chooseLogo": "Logo auswählen...",
        "#save": "Speichern",
    
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
