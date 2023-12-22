package warenkorb;

import produkte.Produkt;
import produkte.Produkteigenschaft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Abstract class representing a shopping cart in a supermarket.
 */
public abstract class Warenkorb {
    protected WarenkorbKategorie typ;
    protected HashMap<Produkt, Integer> produkte = new HashMap<>();
    protected double gesamtwert;
    protected boolean typCheck;

    /**
     * Removes a specified quantity of a product from the shopping cart.
     *
     * @param produkt The product to be removed.
     * @param anzahl  The quantity of the product to be removed.
     */
    public void removeProdukt(Produkt produkt, int anzahl) {
        produkte.put(produkt, produkte.get(produkt) - anzahl);
    }

    /**
     * Adds a specified quantity of a product to the shopping cart if the type check passes.
     *
     * @param produkt The product to be added.
     * @param count   The quantity of the product to be added.
     */
    public void addProdukt(Produkt produkt, int count) {
        if (checkType(produkt, count)) {
            produkte.merge(produkt, count, Integer::sum);
        }
    }

    /**
     * Abstract method to be implemented by subclasses to check the type of product being added.
     *
     * @param produkt The product to be checked.
     * @param anzahl  The quantity of the product to be added.
     * @return True if the type check passes, otherwise false.
     */
    protected abstract boolean checkType(Produkt produkt, int anzahl);

    /**
     * Gets the products and their quantities in the shopping cart.
     *
     * @return A map of products and their quantities.
     */
    public HashMap<Produkt, Integer> getProdukte() {
        return produkte;
    }

    /**
     * Calculates and returns the total value of products in the shopping cart.
     *
     * @return The total value of products in the shopping cart.
     */
    public double getGesamtwert() {
    	double result = 0;
        for (Produkt produkt : produkte.keySet()) {
            result = result + (produkte.get(produkt) * produkt.getVerkaufspreis());
        }
        gesamtwert = result;
        return gesamtwert;
    }

    /**
     * Sorts products based on a specified product property.
     *
     * @param produkteigenschaft The property by which products should be sorted.
     * @param highestValue       If true, sort in ascending order; otherwise, sort in descending order.
     * @return A string describing the product with the highest or lowest value for the specified property.
     */
    public String sortEigenschaften(Produkteigenschaft produkteigenschaft, boolean highestValue) {
        String value = null;
        switch (produkteigenschaft) {
            case FSK -> value = sortFSK(highestValue);
            case Recycling_Anteil -> value = sortRecyclingPercentage(highestValue);
            case Mindesthaltbarkeitsdatum -> value = sortDate(highestValue);
        }
        return value;
    }

    /**
     * Sorts products based on their FSK values.
     *
     * @param highestValue If true, sorts in ascending order; otherwise, sorts in descending order.
     * @return A string describing the product with the highest or lowest FSK value.
     */
    private String sortFSK(boolean highestValue) {
        ArrayList<Integer> fskValues = new ArrayList<>();
        for (Produkt produkt : produkte.keySet()) {
            if (produkt.getProdukteigenschaft().equals(Produkteigenschaft.FSK)) {
                fskValues.add((Integer) produkt.getEigenschaftValue());
            }
        }
        if (highestValue) {
            Collections.sort(fskValues);
        } else {
            fskValues.sort(Collections.reverseOrder());
        }
        Produkt produkt = null;
        for (Produkt produkt1 : produkte.keySet()) {
            if (produkt1.getEigenschaftValue().equals(fskValues.get(0))) {
                produkt = produkt1;
            }
        }
        if (highestValue) {
        	return "Das Produkt mit dem höchsten FSK Wert ist " + produkt.getProduktName() + " mit dem Wert " + fskValues.get(0);
        } else {
        	return "Das Produkt mit dem niedrigsten FSK Wert ist " + produkt.getProduktName() + " mit dem Wert " + fskValues.get(0);
        }
    }

    /**
     * Sorts products based on their expiration dates.
     *
     * @param highestValue If true, sorts in ascending order; otherwise, sorts in descending order.
     * @return A string describing the product with the highest or lowest expiration date.
     */
    private String sortDate(boolean highestValue) {
        ArrayList<Date> dates = new ArrayList<>();
        for (Produkt produkt : produkte.keySet()) {
            if (produkt.getProdukteigenschaft().equals(Produkteigenschaft.Mindesthaltbarkeitsdatum)) {
                dates.add((Date) produkt.getEigenschaftValue());
            }
        }
        if (highestValue) {
            Collections.sort(dates);
        } else {
            dates.sort(Collections.reverseOrder());
        }
        Produkt produkt = null;
        for (Produkt produkt1 : produkte.keySet()) {
            if (produkt1.getEigenschaftValue().equals(dates.get(0))) {
                produkt = produkt1;
            }
        }
        if (highestValue) {
            return "Das Produkt mit dem längsten Haltbarkeitsdatum ist " + produkt.getProduktName() + " mit dem Datum " + dates.get(0);
        } else {
            return "Das Produkt mit dem kürzesten Haltbarkeitsdatum ist " + produkt.getProduktName() + " mit dem Datum " + dates.get(0);
        }

    }

    /**
     * Sorts products based on their recycling percentages.
     *
     * @param highestValue If true, sorts in ascending order; otherwise, sorts in descending order.
     * @return A string describing the product with the highest or lowest recycling percentage.
     */
    private String sortRecyclingPercentage(boolean highestValue) {
        ArrayList<Double> percentages = new ArrayList<>();
        for (Produkt produkt : produkte.keySet()) {
            if (produkt.getProdukteigenschaft().equals(Produkteigenschaft.Recycling_Anteil)) {
                percentages.add((Double) produkt.getEigenschaftValue());
            }
        }
        if (highestValue) {
            Collections.sort(percentages);
        } else {
            percentages.sort(Collections.reverseOrder());
        }
        Produkt produkt = null;
        for (Produkt produkt1 : produkte.keySet()) {
            if (produkt1.getEigenschaftValue().equals(percentages.get(0))) {
                produkt = produkt1;
            }
        }
        if (highestValue) {
            return "Das Produkt mit dem höchsten Recycling-Anteil ist " + produkt.getProduktName() + " mit dem Wert " + percentages.get(0) + "%";
        } else {
            return "Das Produkt mit dem niedrigsten Recycling-Anteil ist " + produkt.getProduktName() + " mit dem Wert " + percentages.get(0) + "%";
        }
    }

    /**
     * Gets a list of product properties for all products in the shopping cart.
     *
     * @return A list of strings containing product properties.
     */
    public ArrayList<String> getProdukteigenschaften() {
        ArrayList<String> eigenschaften = new ArrayList<>();
        for (Produkt produkt : produkte.keySet()) {
            eigenschaften.add(produkt.getProduktName() + "\t" + produkt.getProdukteigenschaft() + " " + produkt.getEigenschaftValue());
        }
        return eigenschaften;
    }

    public WarenkorbKategorie getTyp() {
        return typ;
    }
}
