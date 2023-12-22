package produkte;

import java.util.Calendar;
import java.util.Date;

public class Toastbrot extends Produkt{
    private final Date haltbarkeit;
    public Toastbrot() {
        produktKategorie = ProduktKategorie.LEBENSMITTEL;
        produktName = "Toastbrot";
        einkaufspreis = 0.50;
        verkaufspreis = 1.99;
        produkteigenschaft = Produkteigenschaft.Mindesthaltbarkeitsdatum;
        haltbarkeit = new Date(2024 - 1900, Calendar.JANUARY, 12);
    }
    @Override
    public Object getEigenschaftValue() {
        return haltbarkeit;
    }
}
