package com.fresh.coding.modeles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeTest {
    private Employe rakoto;
    private Employe rabe;

    @BeforeEach
    void setUp(){
        Categorie gardien = new Categorie("gardien", 56, new Salaire(new Montant(100000.0)),  new Salaire(new Montant(0.0)), Collections.emptyList());
        rakoto = new Employe("Rakoto", "Jean", "123", Instant.now(), Instant.now(), null, 110000, gardien);
        rabe = new Employe("Rabe", "Pierre", "124", Instant.now(), Instant.now(), null, 110000, gardien);
    }

    @Test
    void calculeSalaireNet() {
        assertEquals(88000, rakoto.calculeSalaireNet());
        assertEquals(88000, rabe.calculeSalaireNet());
    }

    @Test
    public void testCalculerHeuresSupplementaires() {
        rakoto.ajouterPointage(new Pointage(rakoto, LocalTime.of(8, 0), LocalTime.of(16, 0), Instant.now()));
        assertEquals(8 - 56 , rakoto.calculerHeuresSupplementaires());
    }

    @Test
    public void testCalculerHeuresSupplementairesZero() {
        assertEquals(-56.0, rakoto.calculerHeuresSupplementaires());
    }
}