package com.fresh.coding.services;

import com.fresh.coding.modeles.Categorie;
import com.fresh.coding.repositories.CategorieRepository;
import com.fresh.coding.repositories.JourFerierRepository;

public class CalendrierDeTravailForCategorie extends CalendrierDeTravail {
    private Categorie categorie;

    private final CategorieRepository categorieRepository;

    public CalendrierDeTravailForCategorie(CalendrierDateHelper calendrierDateHelper, JourFerierRepository jourFerierRepository, CategorieRepository categorieRepository) {
        super(calendrierDateHelper, jourFerierRepository, categorieRepository);
        this.categorieRepository = categorieRepository;
    }

    @Override
    protected int getMaxWorkedHours() {
        return categorieRepository.getMaxWorkedHours(categorie);
    }
}
