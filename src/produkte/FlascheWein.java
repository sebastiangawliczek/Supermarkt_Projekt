package produkte;

import java.util.Calendar;
import java.util.Date;

public class FlascheWein extends Produkt{
    private final Date haltbarkeit;
    public FlascheWein() {
        produktKategorie = ProduktKategorie.LEBENSMITTEL;
        produktName = "FlascheWein";
        einkaufspreis = 2.30;
        verkaufspreis = 6.99;
        produkteigenschaft = Produkteigenschaft.Mindesthaltbarkeitsdatum;
        haltbarkeit = new Date(2030 - 1900, Calendar.JANUARY, 30);
    }

    @Override
    public Object getEigenschaftValue() {
        return haltbarkeit;
    }
}
