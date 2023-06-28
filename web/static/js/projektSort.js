export default class ProjektSort {
    constructor(projektListe) {
        this.projektListe = projektListe;
    }

    get sortedDate() {
        return this.sortDate();
    }

    get sortedLaufzeit() {
        return this.sortLaufzeit;
    }

    get sortedID() {
        return this.sortID();
    }

    sortDate() {
        this.projektListe = this.projektListe.sort(function(a, b) {
            return new Date(b.startDatum) - new Date(a.startDatum);
        });
        return this.projektListe
    }

    sortLaufzeit() {
        this.projektListe = this.projektListe.sort(function(a, b) {
            return b.projektLaufzeit - a.projektLaufzeit;
        });
        return this.projektListe
    }

    sortID() {
        this.projektListe = this.projektListe.sort(function (a, b) {
            return a.id - b.id;
        });
        return this.projektListe
    }
}
