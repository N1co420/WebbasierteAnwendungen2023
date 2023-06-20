export default class Aufgabenbereiche {

    constructor(id, name, beschreibung) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    async pushToDB() {
        let aufgabenbereichToSend = this;
        // rename id
        aufgabenbereichToSend.aufgabenbereichID = this.id;
        aufgabenbereichToSend.id = undefined;

        const response = await fetch('http://localhost:8080/WBA-Projekt/api/aufgabenbereich', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            body: JSON.stringify(this)
        });
        return response.ok;
    }
}
