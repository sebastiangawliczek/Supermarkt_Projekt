package produkte;

import java.util.Calendar;
import java.util.Date;

public class Wurst extends Produkt {
    private final Date haltbarkeit;
    public Wurst() {
        produktKategorie = ProduktKategorie.LEBENSMITTEL;
        produktName = "Wurst";
        einkaufspreis = 0.69;
        verkaufspreis = 1.99;
        produkteigenschaft = Produkteigenschaft.Mindesthaltbarkeitsdatum;
        haltbarkeit = new Date(2024 - 1900, Calendar.JANUARY, 10);
    }

    @Override
    public Object getEigenschaftValue() {
        return haltbarkeit;
    }
}