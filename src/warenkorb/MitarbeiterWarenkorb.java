package warenkorb;

import produkte.Produkt;

public class MitarbeiterWarenkorb extends Warenkorb {

    public MitarbeiterWarenkorb() {
        typ = WarenkorbKategorie.Mitarbeiter_Warenkorb;
    }

    @Override
    protected boolean checkType(Produkt produkt, int anzahl) {
        return true;
    }

    @Override
    public double getGesamtwert() {
        for (Produkt produkt : produkte.keySet()) {
            gesamtwert += (produkte.get(produkt) * produkt.getEinkaufspreis());
        }
        return gesamtwert;
    }
}
