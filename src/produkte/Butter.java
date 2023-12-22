package produkte;

import java.util.Calendar;
import java.util.Date;

public class Butter extends Produkt{
    private final Date haltbarkeit;
	public Butter() {
        produktKategorie = ProduktKategorie.LEBENSMITTEL;
        produktName = "Butter";
        einkaufspreis = 0.39;
        verkaufspreis = 1.49;
        produkteigenschaft = Produkteigenschaft.Mindesthaltbarkeitsdatum;
        haltbarkeit = new Date(2024 - 1900, Calendar.JANUARY, 30);
    }

    @Override
    public Object getEigenschaftValue() {
        return haltbarkeit;
    }
}

