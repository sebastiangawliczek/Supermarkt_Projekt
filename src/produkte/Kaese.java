package produkte;

import java.util.Calendar;
import java.util.Date;

public class Kaese extends Produkt{
    private final Date haltbarkeit;
    public Kaese() {
        produktKategorie = ProduktKategorie.LEBENSMITTEL;
        produktName = "Kaese";
        einkaufspreis = 0.49;
        verkaufspreis = 1.29;
        produkteigenschaft = Produkteigenschaft.Mindesthaltbarkeitsdatum;
        haltbarkeit = new Date(2024 - 1900, Calendar.JANUARY, 15);
    }
    @Override
    public Object getEigenschaftValue() {
        return haltbarkeit;
    }
}
