package com.fresh.coding.repositories;

import com.fresh.coding.modeles.Categorie;

public interface CategorieRepository {
    Integer getMaxWorkedHours();

    Integer getMaxWorkedHours(Categorie categorie);
}
