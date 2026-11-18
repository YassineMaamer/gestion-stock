package com.gestionstock.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fournisseur {

    private final int id;
    private String nom;
    private String telephone;
    private String email;
    private String adresse;
    private final List<Produit> produits = new ArrayList<>();

    public Fournisseur(int id, String nom, String telephone, String email, String adresse) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

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
        return "Fournisseur{id=" + id + ", nom='" + nom + "'}";
    }
}
