package warenkorb;

import produkte.Produkt;

public class SparWarenkorb extends Warenkorb{

    public SparWarenkorb(){
        typ = WarenkorbKategorie.Spar_Warenkorb;
    }

    @Override
    protected boolean checkType(Produkt produkt, int anzahl) {
        return !(gesamtwert + produkt.getVerkaufspreis() * anzahl > 50);
    }
}
