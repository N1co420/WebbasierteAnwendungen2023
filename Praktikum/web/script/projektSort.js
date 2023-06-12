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

    sortDate() {
        this.projektListe.sort(function(a, b) {
            return new Date(b.startDatum) - new Date(a.startDatum);
        });
    }

    sortLaufzeit() {
        this.projektListe.sort(function(a, b) {
            return b.projektLaufzeit - a.projektLaufzeit;
        });
    }
}
