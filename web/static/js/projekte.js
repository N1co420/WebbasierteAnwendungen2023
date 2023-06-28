import Projekt from "./projekt.js"
import ProjektSort from "./projektSort.js";
import Projekt_Artefakt from "./projekt_artefakt.js";

const LANGUAGE = {
    "en-US": {
        "#projects": "Projects",
        "#newProject": "New Projekt",
    
        "#filter": "Filter:",
        "#allProjects": "All projects",
        "#startDate": "Start Date",
        "#runTime": "Runtime",
    
        "#imprint": "Imprint",
        "#priacy": "Privacy",
        "#disclaimer": "Disclaimer",
        "#contact": "Contact",
    },
    "de-DE": {
        "#projects": "Projekte",
        "#newProject": "Neues Projekt",
    
        "#filter": "Filter:",
        "#allProjects": "Alle Projekte",
        "#startDate": "Startdatum",
        "#runTime": "Laufzeit",
    
        "#imprint": "Impressum",
        "#priacy": "Datenschutz",
        "#disclaimer": "Haftungsausschluss",
        "#contact": "Kontakt",
    }
}

let userLang = navigator.language || navigator.userLanguage
let langMap = LANGUAGE[userLang] || LANGUAGE["en-US"]
let allProjects = []
console.log("The language is: " + userLang)

for (const key in langMap) {
    document.body.innerHTML = document.body.innerHTML.replaceAll(key, langMap[key])
}

// API Call
const getAllProjects = async () => {
    const response = await fetch("http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/projekt")
    if (response.ok || response.status === 404) {
        try {
            return await response.json()
        } catch (error) {
            console.error(error)
        }
    }
    console.log("Error: " + response.status)
    return null
}

const addProjectToList = (newProject) => {
    // get template id="project-template"
    const template = document.getElementById("project-template")
    // clone template
    const clone = template.content.cloneNode(true)
    // set new title
    clone.querySelector("h2").innerHTML = newProject.name
    // title href
    clone.querySelector("a").href = "/projektdetails.html?id=" + newProject.id
    // set new description
    clone.querySelector("p").innerHTML = newProject.beschreibung
    // side bar
    const sideBar = document.getElementById("project_overview").firstElementChild
    // add li 
    const newLi = document.createElement("li")
    newLi.className = "overview_project_name"
    const newA = document.createElement("a")
    newA.href = "/projektdetails.html?id=" + newProject.id
    newA.innerHTML = newProject.name
    newLi.appendChild(newA)
    sideBar.appendChild(newLi)
    return clone
}

const clearProjectList = () => {
    const projectList = document.getElementById("project_grid")
    // dont delete the template
    while (projectList.lastChild != null) {
        const child = projectList.lastChild
        if (child.id !== "project-template") {
            projectList.removeChild(child)
        }
        // if length is 1, the template is still there -> break
        if (projectList.childElementCount === 1) {
            break
        }
    }

    // clear side bar
    const sideBar = document.getElementById("project_overview").firstElementChild
    sideBar.innerHTML = ""
}

clearProjectList()

const getAllProject_Artefacts = async () => {
    const response = await fetch("http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/projekt_artefakt")
    if (response.ok || response.status == 404) {
        try {
            return await response.json()
        } catch (error) {
            console.error(response)
            console.error(error)
        }
    }
    console.log("Error: " + response.status)
    return null
}

const getAllArtefacts = async () => {
    const response = await fetch("http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/artefakt")
    if (response.ok || response.status == 404) {
        try {
            return await response.json()
        } catch (error) {
            console.error(response)
            console.error(error)
        }
    }
    console.log("Error: " + response.status)
    return null
}

(async () => {
    const projects = await getAllProjects()
    if (projects == null) return
    for (const project of projects) {
        allProjects.push(new Projekt(project.projektID, project.name, project.beschreibung, project.logoPath, project.startDate))
    }
    for (const project of allProjects) {
        const newProject = addProjectToList(project)
        const projectList = document.getElementById("project_grid")
        projectList.appendChild(newProject)
    }

    const project_artefacts = await getAllProject_Artefacts()
    const artefacts = await getAllArtefacts()

    for (const project_artefact of project_artefacts) {
        const project = allProjects.find(p => p.id === project_artefact.projektId)
        const artefact = artefacts.find(a => a.artefaktID === project_artefact.artefaktId)
        if (project === undefined || artefact === undefined) continue
        project.addProjektArtefaktToList(new Projekt_Artefakt(project_artefact.id, project_artefact.projektId, project_artefact.artefaktId, project_artefact.arbeitszeit))
    }
})()

// Sort-filter
document.getElementById("project-filter").addEventListener('change', (e) => {
    if (allProjects == null) return
    const project_sort = new ProjektSort(allProjects)

    switch (e.target.value) {
        case "all":
            project_sort.sortID()
            break
        case "date":
            project_sort.sortDate()
            break
        case "time":
            project_sort.sortLaufzeit()
            break
    }
    clearProjectList()
    for (const project of project_sort.projektListe) {
        const newProject = addProjectToList(project)
        const projectList = document.getElementById("project_grid")
        projectList.appendChild(newProject)
    }

})

export {LANGUAGE}
