package produkte;

import java.util.Calendar;
import java.util.Date;

public class Mineralwasser extends Produkt{
    private final Date haltbarkeit;
    public Mineralwasser() {
        produktKategorie = ProduktKategorie.LEBENSMITTEL;
        produktName = "Mineralwasser";
        einkaufspreis = 0.40;
        verkaufspreis = 0.89;
        produkteigenschaft = Produkteigenschaft.Mindesthaltbarkeitsdatum;
        haltbarkeit = new Date(2029 - 1900, Calendar.JANUARY, 10);
    }
    @Override
    public Object getEigenschaftValue() {
        return haltbarkeit;
    }
}
