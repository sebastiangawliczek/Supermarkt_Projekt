package produkte;

public class Plastikbesteck extends Produkt{
    private final double recyclingAnteil;
    public Plastikbesteck() {
        produktKategorie = ProduktKategorie.HAUSHALTSARTIKEL;
        produktName = "Plastikbesteck";
        einkaufspreis = 0.05;
        verkaufspreis = 0.69;
        produkteigenschaft = Produkteigenschaft.Recycling_Anteil;
        recyclingAnteil = 50;
    }
    @Override
    public Object getEigenschaftValue() {
        return recyclingAnteil;
    }
}
