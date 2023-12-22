package produkte;

public class Putzlappen extends Produkt {
    private final double recyclingAnteil;
    public Putzlappen() {
        produktKategorie = ProduktKategorie.HAUSHALTSARTIKEL;
        produktName = "Putzlappen";
        einkaufspreis = 0.15;
        verkaufspreis = 1.19;
        produkteigenschaft = Produkteigenschaft.Recycling_Anteil;
        recyclingAnteil = 25;
    }
    @Override
    public Object getEigenschaftValue() {
        return recyclingAnteil;
    }
}
