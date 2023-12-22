package produkte;

public class DVDActionfilm extends Produkt {
    private final int fsk;
    public DVDActionfilm() {
        produktKategorie = ProduktKategorie.SONSTIGE;
        produktName = "DVDActionfilm";
        einkaufspreis = 0.99;
        verkaufspreis = 8.99;
        produkteigenschaft = Produkteigenschaft.FSK;
        fsk = 18;
    }

    @Override
    public Object getEigenschaftValue() {
        return fsk;
    }
}
