package supermarkt;

import produkte.*;
import warenkorb.*;

import java.util.*;

/**
 * Represents a supermarket with products, shelves, and shopping carts.
 */
public class Supermarkt {
    // Maps products to their available quantities on the shelves
    private final HashMap<Produkt, Integer> verfuegbareProdukte = new HashMap<>();
    // List of products available for sale
    private final ArrayList<Produkt> angebot = new ArrayList<>();
    // List of available shopping carts
    private final ArrayList<WarenkorbKategorie> verfuegbareWarenkoerbe = new ArrayList<>();

    private final ArrayList<Warenkorb> genutzteWarenkoerbe = new ArrayList<>();
    // The currently active shopping cart
    private Warenkorb aktiverWarenkorb;
    // Total daily earnings of the supermarket
    private double tageseinnahme = 0;

    private final ConsoleScanner consoleScanner;
    private boolean exit;

    /**
     * Constructor for the Supermarkt class.
     * Initializes the available products and fills the shelves.
     */
    public Supermarkt() {
        loadWarenkoerbe();
        createProdukte();
        fillShelves();
        consoleScanner = new ConsoleScanner();
        runLoop();
    }

    /**
     * Run the supermarkt simulation.
     */
    public void runLoop() {
        consoleScanner.println("Willkommen zum Schnurrer Super Store!");
        while (!exit) {
            int option = consoleScanner.chooseOption();
            switch (option) {
                case 1 -> addNewWarenkorb();
                case 2 -> presentBasket();
                case 3 -> chooseWarenkorb();
                case 4 -> chooseProduct();
                case 5 -> removeProduct();
                case 6 -> printWarenkorb();
                case 7 -> printEigenschaften(aktiverWarenkorb.getProdukteigenschaften());
                case 8 -> printSortedEigenschaften();
                case 9 -> pay();
                case 10 -> showRevenue();
                case 11 -> exit = true;
            }
        }
        System.out.println("Verlasse Supermarkt...");
    }

    /**
     * Load the list of available shopping cart categories.
     */
    public void loadWarenkoerbe() {
        verfuegbareWarenkoerbe.addAll(Arrays.asList(WarenkorbKategorie.values()));
    }

    /**
     * Creates various products and adds them to the available products list.
     */
    public void createProdukte() {
        Mineralwasser mineralwasser = new Mineralwasser();
        Toastbrot toastbrot = new Toastbrot();
        Butter butter = new Butter();
        Wurst wurst = new Wurst();
        Kaese kaese = new Kaese();
        FlascheWein flascheWein = new FlascheWein();
        Klobuerste klobuerste = new Klobuerste();
        Plastikbesteck plastikbesteck = new Plastikbesteck();
        Putzlappen putzlappen = new Putzlappen();
        Zahncreme zahncreme = new Zahncreme();
        DVDActionfilm dvdActionfilm = new DVDActionfilm();
        DVDFamilienfilm dvdFamilienfilm = new DVDFamilienfilm();

        angebot.add(mineralwasser);
        angebot.add(toastbrot);
        angebot.add(butter);
        angebot.add(wurst);
        angebot.add(kaese);
        angebot.add(flascheWein);
        angebot.add(klobuerste);
        angebot.add(plastikbesteck);
        angebot.add(putzlappen);
        angebot.add(zahncreme);
        angebot.add(dvdActionfilm);
        angebot.add(dvdFamilienfilm);
    }

    /**
     * Fills the shelves with a default quantity of each product.
     */
    public void fillShelves() {
        int count = 50;
        for (Produkt produkt : angebot) {
            verfuegbareProdukte.put(produkt, count);
        }
    }

    /**
     * Adds a shopping cart to the list of available shopping carts.
     *
     * @param warenkorb The shopping cart to be added.
     */
    public void addWarenkorb(Warenkorb warenkorb) {
        genutzteWarenkoerbe.add(warenkorb);
    }

    /**
     * Add a new shopping cart to the active used list.
     */
    public void addNewWarenkorb() {
        Warenkorb warenkorb = null;
        WarenkorbKategorie chosen = consoleScanner.chooseNewWarenkorb(verfuegbareWarenkoerbe);
        switch (chosen) {
            case Spar_Warenkorb -> warenkorb = new SparWarenkorb();
            case Mitarbeiter_Warenkorb -> warenkorb = new MitarbeiterWarenkorb();
            case Oeko_Prinzip_Warenkorb -> warenkorb = new OekoWarenkorb();
            case U18_Warenkorb -> warenkorb = new U18Warenkorb();
		case Geschenk_Warenkorb -> throw new UnsupportedOperationException("Bitte die Geschenkoption über das Hauptmenü wählen");
		default -> throw new IllegalArgumentException("Bitte die Geschenkoption über das Hauptmenü wählen");
        }
        addWarenkorb(warenkorb);
        setActiveWarenkorb(warenkorb);
    }

    /**
     * Switch between active shopping carts.
     */
    public void chooseWarenkorb() {
        if (genutzteWarenkoerbe.isEmpty()) {
            System.out.println("Keine Aktiven Warenkörbe verfügbar.");
        } else {
            Warenkorb warenkorb = consoleScanner.chooseWarenkorb(genutzteWarenkoerbe);
            setActiveWarenkorb(warenkorb);
        }
    }

    /**
     * Sets the active shopping cart.
     *
     * @param aktiverWarenkorb The shopping cart to be set as active.
     */
    public void setActiveWarenkorb(Warenkorb aktiverWarenkorb) {
        if (genutzteWarenkoerbe.contains(aktiverWarenkorb)) {
            this.aktiverWarenkorb = aktiverWarenkorb;
        }
    }
    
    /**
     * Show the active shopping cart.
     */
    public void printWarenkorb() {
        if (aktiverWarenkorb == null || aktiverWarenkorb.getProdukte().isEmpty()) {
            System.out.println("Der aktive Warenkorb ist leer.");
        } else {
            System.out.println("Produkte im aktiven Warenkorb:");
            for (Map.Entry<Produkt, Integer> entry : aktiverWarenkorb.getProdukte().entrySet()) {
                Produkt produkt = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(produkt.getProduktName() + " - Menge: " + quantity);
            }
            System.out.println("Gesamtwert des aktiven Warenkorbs:" + " " + aktiverWarenkorb.getGesamtwert() + "€");
        }
    }

    /**
     * Chose one of the available products.
     */
    public void chooseProduct() {
        if (aktiverWarenkorb == null) {
            System.out.println("Bitte Warenkorb auswählen.");
        } else {
            Map.Entry<Produkt, Integer> chosenProductEntry = consoleScanner.chooseProduct(verfuegbareProdukte);
            Produkt chosenProduct = chosenProductEntry.getKey();
            int quantity = chosenProductEntry.getValue();
            if(chosenProduct != null && quantity != 0){
                takeProduct(chosenProduct, quantity);
            }
        }
    }

    /**
     * Adds a specified quantity of a product to the active shopping cart
     * and updates the quantity on the shelves.
     *
     * @param produkt The product to be added to the cart.
     * @param count   The quantity of the product to be added.
     */
    public void takeProduct(Produkt produkt, int count) {
        aktiverWarenkorb.addProdukt(produkt, count);
        verfuegbareProdukte.put(produkt, verfuegbareProdukte.get(produkt) - count);
    }

    /**
     * Print the list of all Produkteigenschaften of the current shopping cart.
     * @param eigenschaften the list of all Produkteigenschaften.
     */
    public void printEigenschaften(ArrayList<String> eigenschaften) {
        for (String eigenschaft : eigenschaften) {
            System.out.println(eigenschaft);
        }
    }

    /**
     * Print the sorted Produkteigenschaften based on the chosen options.
     */
    public void printSortedEigenschaften() {
        Produkteigenschaft produkteigenschaft = consoleScanner.chooseProdukteigenschaft(aktiverWarenkorb);
        boolean highest = consoleScanner.getSortingOrder();
        System.out.println(aktiverWarenkorb.sortEigenschaften(produkteigenschaft, highest));
    }

    /**
     * Removes a specified quantity of a product from the active shopping cart
     * and updates the quantity on the shelves.
     *
     */
    public void removeProduct() {
        Map.Entry<Produkt, Integer> chosenProductEntry = consoleScanner.chooseProduct(aktiverWarenkorb.getProdukte());
        Produkt chosenProduct = chosenProductEntry.getKey();
        int quantity = chosenProductEntry.getValue();
        if(chosenProduct != null && quantity != 0){
            aktiverWarenkorb.removeProdukt(chosenProduct, quantity);
            verfuegbareProdukte.put(chosenProduct, verfuegbareProdukte.get(chosenProduct) + quantity);
        }
    }

    /**
     * Processes the payment for the active shopping cart, updating the
     * total daily earnings and removing the cart from the available carts.
     */
    public void pay() {
        if (aktiverWarenkorb == null) {
            System.out.println("Bitte wähle einen Warenkorb aus.");
        } else {
            System.out.println("Warenkorb Gesamtwert: " + aktiverWarenkorb.getGesamtwert());
            System.out.println("Bezahle Warenkorb...");
            tageseinnahme = tageseinnahme + aktiverWarenkorb.getGesamtwert();
            genutzteWarenkoerbe.remove(aktiverWarenkorb);
        }
    }

    /**
     * Print out the current revenue.
     */
    public void showRevenue() {
        System.out.println("Tageseinnahme: " + tageseinnahme);
    }

    /**
     * Creates a shopping cart with random products until the specified price
     * is reached, and sets it as the active shopping cart.
     *
     */
    public void presentBasket() {
        double budget = consoleScanner.choosePresentPrice();
        Warenkorb warenkorb = new GeschenkWarenkorb();
        addWarenkorb(warenkorb);
        setActiveWarenkorb(warenkorb);
        Random random = new Random();
        while (budget > 0.69) {
            int value = random.nextInt(verfuegbareProdukte.size());
            int count = 0;            
            for (Produkt produkt : verfuegbareProdukte.keySet()) {
                if (count == value) {
                    double produktPreis = produkt.getVerkaufspreis();
                    if (budget - produktPreis >= 0) {
                        takeProduct(produkt, 1);
                        budget = budget - produktPreis;
                    }
                    break;
                }
                count++;
            }
        }
    }
}
