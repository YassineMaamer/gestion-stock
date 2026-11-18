package com.gestionstock;

import com.gestionstock.model.Categorie;
import com.gestionstock.model.Depot;
import com.gestionstock.model.Fournisseur;
import com.gestionstock.model.Produit;
import com.gestionstock.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionStockAppTest {

    private GestionStockApp app;
    private Depot depot1;
    private Depot depot2;
    private Produit laptop;
    private Categorie categorie;
    private Fournisseur fournisseur;

    @BeforeEach
    void init() {
        app = new GestionStockApp();
        categorie = new Categorie(1, "Informatique", "Matériel");
        fournisseur = new Fournisseur(1, "TechPlus", "71 000 000", "contact@techplus.tn", "Tunis");
        laptop = new Produit(1, "LAP-001", "Ordinateur portable", 1500, 1800, categorie, fournisseur);
        depot1 = new Depot(1, "Dépôt Tunis", "Tunis", 1000);
        depot2 = new Depot(2, "Dépôt Monastir", "Monastir", 500);
        app.ajouterDepot(depot1);
        app.ajouterDepot(depot2);
        app.ajouterProduit(laptop);
    }

    @Test
    void approvisionnerCreeUneLigneDeStock() {
        app.approvisionner(depot1, laptop, 10);

        Stock s = depot1.getStock(laptop);
        assertNotNull(s);
        assertEquals(10, s.getQuantite());
    }

    @Test
    void approvisionnerDeuxFoisCumuleLesQuantites() {
        app.approvisionner(depot1, laptop, 10);
        app.approvisionner(depot1, laptop, 5);

        assertEquals(15, depot1.getStock(laptop).getQuantite());
        assertEquals(1, depot1.getStocks().size());
    }

    @Test
    void sortieReussieDiminueLeStock() {
        app.approvisionner(depot1, laptop, 10);

        assertTrue(app.sortir(depot1, laptop, 4));
        assertEquals(6, depot1.getStock(laptop).getQuantite());
    }

    @Test
    void sortieImpossibleSiStockInsuffisant() {
        app.approvisionner(depot1, laptop, 3);

        assertFalse(app.sortir(depot1, laptop, 10));
        assertEquals(3, depot1.getStock(laptop).getQuantite());
    }

    @Test
    void sortieImpossibleSiProduitAbsentDuDepot() {
        assertFalse(app.sortir(depot2, laptop, 1));
    }

    @Test
    void quantiteTotaleDuProduitSommeTousLesDepots() {
        app.approvisionner(depot1, laptop, 10);
        app.approvisionner(depot2, laptop, 4);

        assertEquals(14, laptop.getQuantiteTotale());
    }

    @Test
    void alerteQuandLeSeuilEstAtteint() {
        Stock s = new Stock(depot1, laptop, 10, 5);
        assertFalse(s.estEnAlerte());

        s.retirer(6);
        assertTrue(s.estEnAlerte());
    }

    @Test
    void relationsCategorieEtFournisseurSontMaintenues() {
        assertTrue(categorie.getProduits().contains(laptop));
        assertTrue(fournisseur.getProduits().contains(laptop));
        assertSame(categorie, laptop.getCategorie());
        assertSame(fournisseur, laptop.getFournisseur());
    }

    @Test
    void quantiteNegativeEstRefusee() {
        assertThrows(IllegalArgumentException.class, () -> new Stock(depot1, laptop, -1, 0));
    }
}
