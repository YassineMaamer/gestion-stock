package com.gestionstock.model;

public class Stock {

    private final Depot depot;
    private final Produit produit;
    private int quantite;
    private int seuilAlerte;

    public Stock(Depot depot, Produit produit, int quantite, int seuilAlerte) {
        if (depot == null || produit == null) {
            throw new IllegalArgumentException("Le dépôt et le produit sont obligatoires");
        }
        if (quantite < 0 || seuilAlerte < 0) {
            throw new IllegalArgumentException("Quantité et seuil doivent être positifs");
        }
        this.depot = depot;
        this.produit = produit;
        this.quantite = quantite;
        this.seuilAlerte = seuilAlerte;
        depot.ajouterStock(this);
        produit.ajouterStock(this);
    }

    public Depot getDepot() { return depot; }
    public Produit getProduit() { return produit; }
    public int getQuantite() { return quantite; }
    public int getSeuilAlerte() { return seuilAlerte; }
    public void setSeuilAlerte(int seuilAlerte) { this.seuilAlerte = seuilAlerte; }

    public void ajouter(int qte) {
        if (qte <= 0) {
            throw new IllegalArgumentException("La quantité à ajouter doit être > 0");
        }
        quantite += qte;
    }

    /** Retire la quantité demandée. Retourne false si le stock est insuffisant. */
    public boolean retirer(int qte) {
        if (qte <= 0) {
            throw new IllegalArgumentException("La quantité à retirer doit être > 0");
        }
        if (qte > quantite) {
            return false;
        }
        quantite -= qte;
        return true;
    }

    public boolean estEnAlerte() {
        return quantite <= seuilAlerte;
    }

    @Override
    public String toString() {
        return "Stock{depot='" + depot.getNom() + "', produit='" + produit.getNom()
                + "', quantite=" + quantite + ", seuilAlerte=" + seuilAlerte + "}";
    }
}
