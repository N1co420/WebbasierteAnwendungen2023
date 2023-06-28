import Projekt from "./projekt.js"
const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Projekt",
    
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
        "#priacy": "Privacy",
        "#disclaimer": "Disclaimer",
        "#contact": "Contact",
    },
    "de-DE": {
        "#projects": "Projekte",
        "#newProject": "Neues Projekt",
    
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

document.getElementById('logo-input').addEventListener('change', function (evt) {
    console.log(evt.target.files[0])
    const file = evt.target.files[0]
    const reader = new FileReader()
    reader.onload = function (e) {
        const img = document.getElementById('logo_showcase')
        img.src = e.target.result
        img.hidden = false
    }
    reader.readAsDataURL(file) // convert to base64 string
})


function generateString(length) {
    const characters ='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    const charactersLength = characters.length;
    for ( let i = 0; i < length; i++ ) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    
    return result+".png";
}

// API Call

function prepareProject() {
    let projectDate = new Date(document.getElementById('start-date-input').value);
    if (isNaN(projectDate.getTime())) {
        alert("Please enter a valid date!")
        return;
    }
    // to format "2022-06-05T11:52:14Z[UTC]"
    projectDate = projectDate.toISOString().substring(0, 10) + "T" + projectDate.toISOString().substring(11, 19) + "Z" + "[UTC]";
    const project = new Projekt(
        undefined,
        document.getElementById('projekttitel').value,
        document.getElementById('kurzbeschreibung').value,
        "/images/projects/" + (document.getElementById('logo-input').files[0]?.name ?? generateString(6)),
        projectDate)
    console.log(project)
    project.pushToDB().then(response => {
        console.log(response)
        if (response != null) {
            window.location.href = "/projektdetails.html?id=" + response.projektID
        } else {
            alert("Something went wrong!")
            console.error(response)
        }
    }).catch(error => {
        console.log(error)
        alert("Error: " + error.message)
    })
}

// get form
document.getElementsByClassName("main__content")[0].addEventListener('submit', function (evt) {
    evt.preventDefault()
    prepareProject()
})

export {LANGUAGE}
