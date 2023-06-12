const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Project",
        "#login": "Login",
        "#register": "Register",
        "#username": "Username",
        "#password": "Password",

        "#projectTitle": "Title",
        "#shortDescr": "Short Description",
        "#longDescr": "Long Description",
        "#goals": "Goals",
        "#comment": "Comment",
        "#rating": "Rating",
        "#sendcomment": "Send Comment",
        "projectManager": "Project Manager: Max Mustermann",

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

        "#projectTitle": "Titel",
        "#shortDescr": "Kurzbeschreibung",
        "#longDescr": "Langbeschreibung",
        "#goals": "Ziele",
        "#comment": "Kommentar",
        "#rating": "Bewertung",
        "#sendcomment": "Kommentar senden",
        "projectManager": "Projektleiter: Max Mustermann",

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

const id = new URL(window.location.href).searchParams.get("id")
// Check if query has id, if not return to previous page
if (id == null || id === "" || Number.isNaN(id)) {
    window.history.go(-1)
}

const setTitle = (title) => {
    if (title == null) return;
    document.getElementById('titel').innerHTML = title
}

const setKurzbeschreibung = (kurzbeschreibung) => {
    if (kurzbeschreibung == null) return;
    document.getElementById('kurzbeschreibung-content').innerHTML = kurzbeschreibung
}

const setBeschreibung = (beschreibung) => {
    if (beschreibung == null) return;
    document.getElementById('beschreibung-content').innerHTML = beschreibung
}

const addZiel = (ziel) => {
    if (ziel == null)
        return;
    const zieleListe = document.getElementById('ziele-content')
    const li = document.createElement('li')
    li.innerHTML = ziel
    zieleListe.appendChild(li)
}

const setLogo = (logoPath) => {
    const logo = document.getElementById('project-logo')
    if (logoPath != null && logoPath !== "") {
        logo.src = logoPath
    } else {
        logo.src = "images/projects/1.png"
    }
}


const clearZiele = () => {
    const zieleListe = document.getElementById('ziele-content')
    zieleListe.innerHTML = ''
}

const setProjektDetails = (projekt) => {
    setTitle(projekt?.name)
    setKurzbeschreibung(projekt?.kurzbeschreibung)
    setBeschreibung(projekt?.beschreibung)
    setLogo(projekt?.logoPath)
    if (projekt != null)
        clearZiele()
    for (const ziel of projekt?.ziele ?? []) {
        addZiel(ziel)
    }
}

export {LANGUAGE}
