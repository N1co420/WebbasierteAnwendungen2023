import Projekt from "./projekt.js"

const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Projekt",

        "#projectTitle": "Title",
        "#shortDescr": "Short Description",
        "#longDescr": "Long Description",
        "#goals": "Goals",
        "#comments": "Comments",
        "#comment": "Comment",
        "#rating": "Rating",
        "#sendcomment": "Send Comment",

        "#imprint": "Imprint",
        "#priacy": "Privacy",
        "#disclaimer": "Disclaimer",
        "#contact": "Contact",
    },
    "de-DE": {
        "#projects": "Projekte",
        "#newProject": "Neues Projekt",

        "#projectTitle": "Titel",
        "#shortDescr": "Kurzbeschreibung",
        "#longDescr": "Langbeschreibung",
        "#goals": "Ziele",
        "#comments": "Kommentare",
        "#comment": "Kommentar",
        "#rating": "Bewertung",
        "#sendcomment": "Kommentar senden",

        "#imprint": "Impressum",
        "#priacy": "Datenschutz",
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
    document.getElementById('kurzbeschreibung-content').innerHTML = kurzbeschreibung ?? ""
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

const addCommentToPage = (comment) => {
    const template = document.getElementById("comment_template")
    // clone template
    const clone = template.content.cloneNode(true)
    const fields = clone.querySelectorAll("p")
    // set new title
    fields[0].innerHTML = comment.text
    fields[1].innerHTML = comment.rating
    fields[2].innerHTML = comment.date
    // append to page
    document.getElementById("comments").appendChild(clone)
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

// API Call
const getProjektDetails = async (id) => {
    const response = await fetch("http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/projekt?id=" + id)
    if (response.ok) {
        try {
            return await response.json()
        } catch (error) {
            console.error(error)
        }
    }
    if (response.status === 404) {
        window.history.go(-1)
    }
    console.log("Error: " + response.status)
    return null
}

getProjektDetails(id).then(response => {
    const projekt = new Projekt(response.projektID, response.name, response.beschreibung, response.logoPath, response.startDate)
    setProjektDetails(projekt)
}).catch(error => {
    console.error(error)
})

// Get Comments from Local Storage
const getComments = (projectId) => {
    const comments = localStorage.getItem('comments')
    if (comments != null) {
        let ret
        try {
            ret = JSON.parse(comments)[projectId].sort((a, b) => {
                return new Date(a.date) - new Date(b.date)
            })
        } catch (error) {
            console.error(error)
            ret = []
        }
        // sort by date
        return ret
    }
    return []
}

for (const c of getComments(id)) {
    addCommentToPage(c)
}

// Add Comment to Local Storage
const addComment = (text, rating) => {
    if (text == null || text === "")
        return;
    // Date format: dd.mm.yyyy hh:mm:ss
    const comment = {
        text: text,
        rating: rating,
        date: new Date().toLocaleString("de-DE", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit"
        })
    }

    const comments = localStorage.getItem('comments') ?? "{}"
    let parsedComments = {}
    try {
        parsedComments = JSON.parse(comments)
    } catch (error) {
        console.error(error)
    }
    parsedComments[id] = parsedComments[id] ?? []
    parsedComments[id].push(comment)
    localStorage.setItem('comments', JSON.stringify(parsedComments))

    addCommentToPage(comment)
}

document.getElementsByClassName('comment_form')[0].addEventListener('submit', (e) => {
    e.preventDefault()

    const text = document.getElementById('comment').value
    const rating = document.getElementById('rating').value
    addComment(text, rating)
    document.getElementById('comment').value = ""
    document.getElementById('rating').value = "1"
})

export {LANGUAGE}
