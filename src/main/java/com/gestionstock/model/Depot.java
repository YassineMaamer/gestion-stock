package com.gestionstock.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Depot {

    private final int id;
    private String nom;
    private String adresse;
    private int capacite;
    private final List<Stock> stocks = new ArrayList<>();

    public Depot(int id, String nom, String adresse, int capacite) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(stocks);
    }

    void ajouterStock(Stock s) {
        if (!stocks.contains(s)) {
            stocks.add(s);
        }
    }

    /** Retourne la ligne de stock du produit dans ce dépôt, ou null si absente. */
    public Stock getStock(Produit p) {
        return stocks.stream()
                .filter(s -> s.getProduit().equals(p))
                .findFirst()
                .orElse(null);
    }

    public int getQuantiteTotale() {
        return stocks.stream().mapToInt(Stock::getQuantite).sum();
    }

    @Override
    public String toString() {
        return "Depot{id=" + id + ", nom='" + nom + "'}";
    }
}
