export default class Projekt {

  constructor(id, name, beschreibung, logoPath, startDatum) {
    this.id = id;
    this.name = name;
    this.beschreibung = beschreibung;
    this.logoPath = logoPath;
    this.startDatum = startDatum;
    
  }

  get projektLaufzeit() {
    return this.berechneProjektLaufzeit();
  }

  berechneProjektLaufzeit(artefaktListe) {
    let laufzeit = 0;
    for (const artefakt of artefaktListe) {
      laufzeit += artefakt.geplanteZeit
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
    projectToSend.startDatum = date.toISOString().substring(0, 10) + "T" + date.toISOString().substring(11, 19) + "Z" + "[UTC]";

    const response = await fetch('http://localhost:8080/WBA-Projekt/api/projekt', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(this)
    });
    return response.ok;
  }
}
