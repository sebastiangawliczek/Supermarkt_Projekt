package produkte;

public class Zahncreme extends Produkt {
    private final double recyclingAnteil;

    public Zahncreme() {
        produktKategorie = ProduktKategorie.HAUSHALTSARTIKEL;
        produktName = "Zahncreme";
        einkaufspreis = 0.50;
        verkaufspreis = 1.99;
        produkteigenschaft = Produkteigenschaft.Recycling_Anteil;
        recyclingAnteil = 4.5;
    }

    @Override
    public Object getEigenschaftValue() {
        return recyclingAnteil;
    }
}
