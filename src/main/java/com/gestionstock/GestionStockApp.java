package com.gestionstock;

import com.gestionstock.model.Categorie;
import com.gestionstock.model.Depot;
import com.gestionstock.model.Fournisseur;
import com.gestionstock.model.Produit;
import com.gestionstock.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class GestionStockApp {

    private final List<Depot> depots = new ArrayList<>();
    private final List<Produit> produits = new ArrayList<>();
    private final List<Fournisseur> fournisseurs = new ArrayList<>();
    private final List<Categorie> categories = new ArrayList<>();

    public void ajouterDepot(Depot d) { depots.add(d); }
    public void ajouterProduit(Produit p) { produits.add(p); }
    public void ajouterFournisseur(Fournisseur f) { fournisseurs.add(f); }
    public void ajouterCategorie(Categorie c) { categories.add(c); }

    public List<Depot> getDepots() { return depots; }
    public List<Produit> getProduits() { return produits; }
    public List<Fournisseur> getFournisseurs() { return fournisseurs; }
    public List<Categorie> getCategories() { return categories; }

    /**
     * Ajoute une quantité d'un produit dans un dépôt.
     * Crée la ligne de stock si elle n'existe pas encore.
     */
    public void approvisionner(Depot depot, Produit produit, int qte) {
        Stock stock = depot.getStock(produit);
        if (stock == null) {
            new Stock(depot, produit, qte, 5);
        } else {
            stock.ajouter(qte);
        }
    }

    /** Retire une quantité d'un produit dans un dépôt. Retourne false si impossible. */
    public boolean sortir(Depot depot, Produit produit, int qte) {
        Stock stock = depot.getStock(produit);
        return stock != null && stock.retirer(qte);
    }

    public void afficherStocks() {
        System.out.println("=== État des stocks ===");
        for (Depot d : depots) {
            System.out.println("Dépôt : " + d.getNom() + " (" + d.getAdresse() + ")");
            for (Stock s : d.getStocks()) {
                System.out.printf("  - %-20s qté = %3d%s%n",
                        s.getProduit().getNom(),
                        s.getQuantite(),
                        s.estEnAlerte() ? "  [ALERTE STOCK BAS]" : "");
            }
        }
    }

    public static void main(String[] args) {
        GestionStockApp app = new GestionStockApp();

        Categorie informatique = new Categorie(1, "Informatique", "Matériel informatique");
        Categorie bureau = new Categorie(2, "Bureau", "Fournitures de bureau");
        app.ajouterCategorie(informatique);
        app.ajouterCategorie(bureau);

        Fournisseur techPlus = new Fournisseur(1, "TechPlus", "71 000 000", "contact@techplus.tn", "Tunis");
        Fournisseur papeterie = new Fournisseur(2, "Papeterie Centrale", "73 000 000", "info@papeterie.tn", "Monastir");
        app.ajouterFournisseur(techPlus);
        app.ajouterFournisseur(papeterie);

        Produit laptop = new Produit(1, "LAP-001", "Ordinateur portable", 1500, 1800, informatique, techPlus);
        Produit souris = new Produit(2, "SOU-001", "Souris sans fil", 20, 35, informatique, techPlus);
        Produit cahier = new Produit(3, "CAH-001", "Cahier A4", 2, 4, bureau, papeterie);
        app.ajouterProduit(laptop);
        app.ajouterProduit(souris);
        app.ajouterProduit(cahier);

        Depot tunis = new Depot(1, "Dépôt Tunis", "Tunis", 1000);
        Depot monastir = new Depot(2, "Dépôt Monastir", "Monastir", 500);
        app.ajouterDepot(tunis);
        app.ajouterDepot(monastir);

        app.approvisionner(tunis, laptop, 10);
        app.approvisionner(tunis, souris, 50);
        app.approvisionner(monastir, laptop, 4);
        app.approvisionner(monastir, cahier, 200);

        app.afficherStocks();

        System.out.println();
        try {
            System.out.println("Sortie de 7 ordinateurs à Tunis : " + app.sortir(tunis, laptop, 7));
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur (sortie Tunis) : " + e.getMessage());
        }

        try {
            System.out.println("Sortie de -20 ordinateurs à Monastir : " + app.sortir(monastir, laptop, -20));
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur (sortie Monastir) : " + e.getMessage());
        }

        try {
            app.approvisionner(monastir, souris, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur (approvisionnement Monastir) : " + e.getMessage());
        }
        System.out.println();

        app.afficherStocks();
        System.out.println();
        System.out.println("Quantité totale d'ordinateurs (tous dépôts) : " + laptop.getQuantiteTotale());
    }
}
