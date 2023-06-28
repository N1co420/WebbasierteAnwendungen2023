export default class Artefakt {

    constructor(id, name, beschreibung, geplanteZeit) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.geplanteZeit = geplanteZeit;
    }

    async pushToDB() {
        let artefaktToSend = this;
        // rename id
        artefaktToSend.artefaktID = this.id;
        artefaktToSend.id = undefined;

        const response = await fetch('http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/artefakt', {
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
