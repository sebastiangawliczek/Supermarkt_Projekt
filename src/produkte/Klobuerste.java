package produkte;

public class Klobuerste extends Produkt{
    private final double recyclingAnteil;
    public Klobuerste() {
        produktKategorie = ProduktKategorie.HAUSHALTSARTIKEL;
        produktName = "Klobuerste";
        einkaufspreis = 0.99;
        verkaufspreis = 4.99;
        produkteigenschaft = Produkteigenschaft.Recycling_Anteil;
        recyclingAnteil = 12.5;
    }

    @Override
    public Object getEigenschaftValue() {
        return recyclingAnteil;
    }
}
