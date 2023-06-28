export default class Projekt {

  constructor(id, name, beschreibung, logoPath, startDatum) {
    this.id = id;
    this.name = name;
    this.beschreibung = beschreibung;
    this.logoPath = logoPath;
    // "2022-06-05T11:52:14Z[UTC]" to Date
    this.startDatum = new Date().setUTCFullYear(startDatum.substring(0, 4), startDatum.substring(5, 7) - 1, startDatum.substring(8, 10));
    this.startDatum = new Date(this.startDatum).setHours(startDatum.substring(11, 13), startDatum.substring(14, 16), startDatum.substring(17, 19));
    this.projektArtefaktListe = []
    // TODO: Ziele ????
  }

  addProjektArtefaktToList(artefakt){
    if (artefakt == null || this.projektArtefaktListe.includes(artefakt)) return
    this.projektArtefaktListe.push(artefakt)
  }

  get projektLaufzeit() {
    return this.berechneProjektLaufzeit(this.projektArtefaktListe);
  }

  berechneProjektLaufzeit(projektArtefaktListe) {
    let laufzeit = 0;
    for (const projekt_artefakt of projektArtefaktListe) {
      laufzeit += projekt_artefakt.arbeitszeit
    }
    return laufzeit
  }

  async pushToDB() {
    let projectToSend = this;
    // rename id
    projectToSend.projektID = this.id;
    projectToSend.id = undefined;

    // date
    let date = new Date(this.startDatum);
    projectToSend.startDate = date.toISOString().substring(0, 10) + "T" + date.toISOString().substring(11, 19) + "Z" + "[UTC]";
    projectToSend.startDatum = undefined;

    const response = await fetch('http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/projekt', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(this)
    });
    return response.status == 200 ? response.json() : null;
  }
}
