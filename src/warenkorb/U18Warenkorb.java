package warenkorb;

import produkte.Produkt;
import produkte.Produkteigenschaft;

public class U18Warenkorb extends Warenkorb {

    public U18Warenkorb() {
        typ = WarenkorbKategorie.U18_Warenkorb;
    }


    @Override
    protected boolean checkType(Produkt produkt, int anzahl) {
        return !produkt.getProduktName().equals("FlascheWein") && (!produkt.getProdukteigenschaft().equals(Produkteigenschaft.FSK) || (int) produkt.getEigenschaftValue() != 18);
    }
}
