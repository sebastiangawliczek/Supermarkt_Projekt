package warenkorb;

import produkte.Produkt;

public class OekoWarenkorb extends Warenkorb {
    public OekoWarenkorb() {
        this.typ = WarenkorbKategorie.Oeko_Prinzip_Warenkorb;
    }

    @Override
    protected boolean checkType(Produkt produkt, int anzahl) {
        return !produkt.getProduktName().equals("Plastikbesteck") && !produkt.getProduktName().equals("Wurst");
    }
}
