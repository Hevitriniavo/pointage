package com.fresh.coding.modeles;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Employe {
    private String nom;
    private String prenom;
    private String numeroMatricule;
    private Instant dateNaissance;
    private Instant dateEmbauche;
    private Instant dateFinContrat;
    private double salaireBrut;
    private Categorie categorie;
    private List<Pointage> pointages;

    public Employe(String nom, String prenom, String numeroMatricule, Instant dateNaissance, Instant dateEmbauche, Instant dateFinContrat, double salaireBrut, Categorie categorie) {
        this.pointages = new ArrayList<>();
        this.nom = nom;
        this.prenom = prenom;
        this.numeroMatricule = numeroMatricule;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.dateFinContrat = dateFinContrat;
        this.salaireBrut = salaireBrut;
        this.categorie = categorie;
    }

    public double calculeSalaireNet() {
        return this.salaireBrut - (this.salaireBrut * 0.20);
    }


    public double calculerHeuresSupplementaires() {
        var totaleHeurePointage = pointages.stream().mapToDouble(Pointage::getHeuresTravaillee).sum();
        var res = totaleHeurePointage - categorie.getHeuresNormalesParSemaine();
        if (res > 20) {
            return 20;
        }
        return res;
    }


    public void ajouterPointage(Pointage pointage) {
        pointages.add(pointage);
    }
}
