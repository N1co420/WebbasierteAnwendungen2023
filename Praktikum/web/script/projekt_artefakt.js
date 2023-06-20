export default class Projekt_Artefakt {

    constructor(id, projektId, artefaktId, arbeitszeit) {
        this.id = id;
        this.projektId = projektId;
        this.artefaktId = artefaktId;
        this.arbeitszeit = arbeitszeit;
    }

    async pushToDB() {
        
            const response = await fetch('http://localhost:8080/WBA-Projekt/api/projekt/' + this.projektId + '/artefact', {
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
