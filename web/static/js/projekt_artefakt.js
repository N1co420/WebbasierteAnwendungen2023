export default class Projekt_Artefakt {

    constructor(id, projektId, artefaktId, arbeitszeit) {
        this.id = id;
        this.projektId = projektId;
        this.artefaktId = artefaktId;
        this.arbeitszeit = arbeitszeit;
    }

    async pushToDB() {
        let projekt_artefaktToSend = this;

        const response = await fetch('http://localhost:8080/WBA-Projekt-1.0-SNAPSHOT/api/projekt/' + this.projektId + '/artefact', {
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
