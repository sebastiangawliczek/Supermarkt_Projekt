package warenkorb;

import produkte.Produkt;

public class GeschenkWarenkorb extends Warenkorb{

    public GeschenkWarenkorb(){
        typ = WarenkorbKategorie.Geschenk_Warenkorb;
    }

    @Override
    protected boolean checkType(Produkt produkt, int anzahl) {
        return !(gesamtwert + produkt.getVerkaufspreis() * anzahl > 50);
    }
}
