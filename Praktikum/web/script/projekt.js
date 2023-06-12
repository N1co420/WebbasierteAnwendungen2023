export default class Projekt {

  constructor(id, name, beschreibung, logoPath, startDatum) {
    this.id = id;
    this.name = name;
    this.beschreibung = beschreibung;
    this.logoPath = logoPath;
    this.startDatum = startDatum;
    // TODO: Ziele ????
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
}
