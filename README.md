# Gestion de stock

Application Java de gestion de stock multi-dépôts : produits, catégories, fournisseurs et quantités par dépôt, avec alerte de stock bas.

## Fonctionnalités

- Gestion des **catégories**, **fournisseurs**, **produits** et **dépôts**
- **Approvisionnement** d'un produit dans un dépôt (la ligne de stock est créée si elle n'existe pas)
- **Sortie** de stock, refusée si la quantité disponible est insuffisante
- **Alerte** lorsque la quantité atteint ou passe sous le seuil
- **Quantité totale** d'un produit, tous dépôts confondus
- **Validation** des quantités : une quantité négative ou nulle lève une `IllegalArgumentException`, gérée dans `main` pour que le programme continue

## Modèle

```
Categorie 1 ──── * Produit * ──── 1 Fournisseur
                     1
                     │
                     *
Depot 1 ──────── * Stock (quantite, seuilAlerte)
```

| Classe        | Rôle                                                        |
|---------------|-------------------------------------------------------------|
| `Categorie`   | Regroupe des produits (ex. Informatique, Bureau)            |
| `Fournisseur` | Fournit des produits (coordonnées, adresse)                 |
| `Produit`     | Référence, nom, prix d'achat / de vente                     |
| `Depot`       | Lieu de stockage (nom, adresse, capacité)                   |
| `Stock`       | Quantité d'un produit dans un dépôt et seuil d'alerte       |

## Prérequis

- Java 17+
- Maven 3.x

## Utilisation

Lancer l'application de démonstration :

```bash
mvn compile exec:java
```

Lancer les tests :

```bash
mvn test
```

## Structure

```
src/
├── main/java/com/gestionstock/
│   ├── GestionStockApp.java      # Service + démo (main)
│   └── model/
│       ├── Categorie.java
│       ├── Depot.java
│       ├── Fournisseur.java
│       ├── Produit.java
│       └── Stock.java
└── test/java/com/gestionstock/
    └── GestionStockAppTest.java  # Tests JUnit 5
```
