package com.fresh.coding.modeles;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Categorie {
    private String nom;
    private int heuresNormalesParSemaine;
    private Salaire salaireParSemaine;
    private Salaire indemnite;

    private List<HeureMajoree> heureMajorees;
}
