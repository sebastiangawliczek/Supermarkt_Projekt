package supermarkt;

import produkte.Produkt;
import produkte.Produkteigenschaft;
import warenkorb.Warenkorb;
import warenkorb.WarenkorbKategorie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleScanner {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<String> options = new ArrayList<>();

    public ConsoleScanner() {
        options.add("Neuen Warenkorb hinzufügen.");
        options.add("Geschenk-Warenkorb hinzufügen.");
        options.add("Aktiven Warenkorb wechseln.");
        options.add("Produkt in den Warenkorb hinzufügen.");
        options.add("Produkt aus dem Warenkorb entfernen.");
        options.add("Produkte im Warenkorb anzeigen lassen.");
        options.add("Produkteigenschaften anzeigen.");
        options.add("Produkteigenschaften sortieren.");
        options.add("Aktiven Warenkorb bezahlen.");
        options.add("Tageseinnahme anzeigen.");
        options.add("Supermarkt verlassen.");
    }

    /**
     * Displays a menu of options and prompts for the user to choose.
     *
     * @return The number of the chosen option.
     */
    public int chooseOption() {
        println("Menü:");

        int index = 1;
        for (String option : options) {
            println(index + ". " + option);
            index++;
        }

        print("Trage die Nummer der Option ein, die du wählen möchtest: ");
        int chosenOption = readInt();

        // Validate the input
        while (chosenOption < 1 || chosenOption > options.size()) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein.");
            print("Trage die Nummer der Option ein, die du wählen möchtest: ");
            chosenOption = readInt();
        }

        return chosenOption;
    }

    /**
     * Displays a list of present prices for the user to choose.
     * @return the price for the present.
     */
    public int choosePresentPrice() {
        println("Trage die Nummer der Option ein, die du wählen möchtest: ");
        println("1. 10€");
        println("2. 20€");
        println("3. 50€");
        int chosenOption = readInt();

        // Validate the input
        while (chosenOption < 1 || chosenOption > 3) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein.");
            print("Trage die Nummer der Option ein, die du wählen möchtest: ");
            chosenOption = readInt();
        }
        int price = 0;
        switch (chosenOption) {
            case 1 -> price = 10;
            case 2 -> price = 20;
            case 3 -> price = 50;
        }
        return price;
    }

    /**
     * Displays a list of Produkteigenschaft from the current shopping cart for the user to choose.
     * @param warenkorb the active shopping cart
     * @return the chosen Produkteigenschaft
     */
    public Produkteigenschaft chooseProdukteigenschaft(Warenkorb warenkorb) {
        ArrayList<String> produkteigenschaften = warenkorb.getProdukteigenschaften();
        println("Wähle eine Eigenschaft: ");
        int index = 1;
        for (String produkteigenschaft : produkteigenschaften) {
            println(index + ". " + produkteigenschaft);
            index++;
        }
        int chosenOption = readInt()-1;

        // Validate the input
        while (chosenOption < 1 || chosenOption > produkteigenschaften.size()) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein.");
            print("Wähle eine Eigenschaft: ");
            chosenOption = readInt()-1;
        }
        String chosen = produkteigenschaften.get(chosenOption);
        if (chosen.contains(Produkteigenschaft.FSK.name())) {
            return Produkteigenschaft.FSK;
        } else if (chosen.contains(Produkteigenschaft.Mindesthaltbarkeitsdatum.name())) {
            return Produkteigenschaft.Mindesthaltbarkeitsdatum;
        } else {
            return Produkteigenschaft.Recycling_Anteil;
        }
    }

    /**
     * Displays the option of sorting order from highest to lowest for the user to choose.
     * @return the sorting order as a boolean. true = highest to lowest.
     */
    public boolean getSortingOrder() {
        println("Wähle eine Option zur Sortierung: ");
        println("1. Absteigend");
        println("2. Aufsteigend");

        int chosen = readInt();
        return chosen != 1;
    }

    /**
     * Displays a list of available shopping cart categories for the user to choose.
     *
     * @param availableCarts The list of available shopping cart categories.
     * @return The chosen shopping cart category.
     */
    public WarenkorbKategorie chooseNewWarenkorb(ArrayList<WarenkorbKategorie> availableCarts) {
        println("Verfügbare Warenkorbkategorien:");

        int index = 1;
        for (WarenkorbKategorie cart : availableCarts) {
            println(index + ". " + cart);
            index++;
        }

        print("Trage die Nummer der Warenkorbkategorie ein, die du wählen möchtest: ");
        int cartNumber = readInt();

        // Validate the input
        while (cartNumber < 1 || cartNumber > availableCarts.size()) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein.");
            print("Trage die Nummer der Warenkorbkategorie ein, die du wählen möchtest: ");
            cartNumber = readInt();
        }

        // Find the chosen shopping cart based on the input number
        return availableCarts.get(cartNumber - 1);
    }

    /**
     * Displays a list of available shopping carts with their types and prompts the user to choose.
     *
     * @param availableCarts The list of available shopping carts.
     * @return The chosen shopping cart.
     */
    public Warenkorb chooseWarenkorb(ArrayList<Warenkorb> availableCarts) {
        println("Verfügbare Warenkörbe:");

        int index = 1;
        for (Warenkorb cart : availableCarts) {
            println(index + ". " + cart.getTyp());
            index++;
        }

        print("Trage die Nummer des Warenkorbes ein, den du wählen möchtest: ");
        int cartNumber = readInt();

        // Validate the input
        while (cartNumber < 1 || cartNumber > availableCarts.size()) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein.");
            print("Trage die Nummer des Warenkorbes ein, den du wählen möchtest: ");
            cartNumber = readInt();
        }

        // Find the chosen shopping cart based on the input number
        return availableCarts.get(cartNumber - 1);
    }

    /**
     * Displays a list of available products with their quantities and prompts the user to choose.
     *
     * @param availableProducts The map of available products and their quantities.
     * @return The chosen product and its quantity.
     */
    public Map.Entry<Produkt, Integer> chooseProduct(HashMap<Produkt, Integer> availableProducts) {
        println("Verfügbare Produkte:");

        int index = 1;
        for (Map.Entry<Produkt, Integer> entry : availableProducts.entrySet()) {
            println(index + ". " + entry.getKey().getProduktName() + " : " + entry.getValue());
            index++;
        }

        print("Trage die Nummer des Produktes ein, das du wählen möchtest: ");
        int productNumber = readInt();

        // Validate the input
        while (productNumber != 0 && (productNumber < 1 || productNumber > availableProducts.size())) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein. 0 eingeben, um Auswahl zu beenden.");
            print("Trage die Nummer des Produktes ein, das du wählen möchtest: ");
            productNumber = readInt();
        }

        Produkt chosenProduct = null;
        int count = 0;

        // Find the chosen product based on the input number
        for (Map.Entry<Produkt, Integer> entry : availableProducts.entrySet()) {
            if (++count == productNumber) {
                chosenProduct = entry.getKey();
                break;
            }
        }

        // Ask for the quantity
        print("Anzahl für " + chosenProduct.getProduktName() + ": ");
        int quantity = readInt();

        while (availableProducts.get(chosenProduct) < quantity || availableProducts.get(chosenProduct) + quantity < 0) {
            print("Nicht genug Produkte vorhanden. 0 eingeben, um Auswahl zu beenden.");
            print("Anzahl für " + chosenProduct.getProduktName() + ": ");
            quantity = readInt();
        }

        return Map.entry(chosenProduct, quantity);
    }

    /**
     * Reads an integer input from the console.
     *
     * @return The integer input.
     */
    public int readInt() {
        while (!scanner.hasNextInt()) {
            println("Falsche Eingabe. Bitte trage einen richtigen Wert ein.");
            scanner.next(); // consume the invalid input
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return result;
    }

    /**
     * Writes a message to the console.
     *
     * @param message The message to be displayed.
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Writes a message to the console with a newline character.
     *
     * @param message The message to be displayed.
     */
    public void println(String message) {
        System.out.println(message);
    }
}
