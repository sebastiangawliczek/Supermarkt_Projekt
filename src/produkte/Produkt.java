package produkte;

/**
 * Abstract class representing a general product in a supermarket.
 */
public abstract class Produkt {
    protected ProduktKategorie produktKategorie;
    protected String produktName;
    protected double einkaufspreis, verkaufspreis;
    protected Produkteigenschaft produkteigenschaft;

    public ProduktKategorie getKategorie() {
        return produktKategorie;
    }

    public String getProduktName() {
        return produktName;
    }

    public void setEinkaufspreis(double einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }

    public void setVerkaufspreis(double verkaufspreis) {
        this.verkaufspreis = verkaufspreis;
    }

    public double getEinkaufspreis() {
        return einkaufspreis;
    }

    public double getVerkaufspreis() {
        return verkaufspreis;
    }

    public abstract Object getEigenschaftValue();

    public Produkteigenschaft getProdukteigenschaft() {
        return produkteigenschaft;
    }
}
