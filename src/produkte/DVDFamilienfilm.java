package produkte;

public class DVDFamilienfilm extends Produkt {
    private final int fsk;
    public DVDFamilienfilm() {
        produktKategorie = ProduktKategorie.SONSTIGE;
        produktName = "DVDFamilienfilm";
        einkaufspreis = 0.89;
        verkaufspreis = 7.99;
        produkteigenschaft = Produkteigenschaft.FSK;
        fsk = 6;
    }
    @Override
    public Object getEigenschaftValue() {
        return fsk;
    }
}
