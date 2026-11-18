package com.gestionstock.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categorie {

    private final int id;
    private String nom;
    private String description;
    private final List<Produit> produits = new ArrayList<>();

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Produit> getProduits() {
        return Collections.unmodifiableList(produits);
    }

    void ajouterProduit(Produit p) {
        if (!produits.contains(p)) {
            produits.add(p);
        }
    }

    @Override
    public String toString() {
        return "Categorie{id=" + id + ", nom='" + nom + "'}";
    }
}
