package com.gestionstock.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Produit {

    private final int id;
    private String reference;
    private String nom;
    private double prixAchat;
    private double prixVente;
    private final Categorie categorie;
    private final Fournisseur fournisseur;
    private final List<Stock> stocks = new ArrayList<>();

    public Produit(int id, String reference, String nom, double prixAchat, double prixVente,
                   Categorie categorie, Fournisseur fournisseur) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
        if (categorie != null) {
            categorie.ajouterProduit(this);
        }
        if (fournisseur != null) {
            fournisseur.ajouterProduit(this);
        }
    }

    public int getId() { return id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public double getPrixAchat() { return prixAchat; }
    public void setPrixAchat(double prixAchat) { this.prixAchat = prixAchat; }
    public double getPrixVente() { return prixVente; }
    public void setPrixVente(double prixVente) { this.prixVente = prixVente; }
    public Categorie getCategorie() { return categorie; }
    public Fournisseur getFournisseur() { return fournisseur; }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(stocks);
    }

    void ajouterStock(Stock s) {
        if (!stocks.contains(s)) {
            stocks.add(s);
        }
    }

    /** Quantité totale de ce produit, tous dépôts confondus. */
    public int getQuantiteTotale() {
        return stocks.stream().mapToInt(Stock::getQuantite).sum();
    }

    @Override
    public String toString() {
        return "Produit{id=" + id + ", reference='" + reference + "', nom='" + nom + "'}";
    }
}
