package com.fresh.coding.services;

import com.fresh.coding.modeles.Categorie;
import com.fresh.coding.modeles.JourFerier;
import com.fresh.coding.repositories.CategorieRepository;
import com.fresh.coding.repositories.JourFerierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CalendrierDeTravailTest {
    private CalendrierDateHelper calendrierDateHelper;
    private CategorieRepository categorieRepository;
    private JourFerierRepository jourFerierRepository;

    @BeforeEach
    void setUp() {
        calendrierDateHelper = new CalendrierDateHelper() {
            @Override
            public Instant getDebut() {
                return LocalDateTime.of(2024, 6, 1, 8, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant();
            }

            @Override
            public Instant getFin() {
                return LocalDateTime.of(2024, 6, 30, 16, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant();
            }
        };
        jourFerierRepository = new JourFerierRepository() {
            @Override
            public Set<JourFerier> getAllClosedDates(Instant debut, Instant fin) {
                Set<JourFerier> closedDates = new HashSet<>();
                closedDates.add(new JourFerier(LocalDate.of(2024, 6, 17)));
                closedDates.add(new JourFerier(LocalDate.of(2024, 6, 25)));
                closedDates.add(new JourFerier(LocalDate.of(2024, 6, 26)));
                return closedDates;
            }
        };

        categorieRepository = new CategorieRepository() {
            @Override
            public Integer getMaxWorkedHours() {
                return 48;
            }

            @Override
            public Integer getMaxWorkedHours(Categorie categorie) {
                return 0;
            }
        };
    }

    @Test
    void testGetCalendrier() {
        // Categorie 1 avec 40H par semaine
        // date 1 = 17-06-2024
        // date 2 = 27-06-2024
        // date 3 = 26-06-2024
        var calendrieTravail = new CalendrierDeTravail(calendrierDateHelper, jourFerierRepository, categorieRepository);
        var result = calendrieTravail.getCalendrier();
        // assertions
        // list - 17
        // 20
        assertEquals(25, result.size());
        assertTrue(result.get(LocalDateTime.of(2024, 6, 3, 8, 0)));
        assertFalse(result.get(LocalDateTime.of(2024, 6, 17, 8, 0)));
        assertFalse(result.get(LocalDateTime.of(2024, 6, 25, 8, 0)));
        assertFalse(result.get(LocalDateTime.of(2024, 6, 26, 8, 0)));
        assertTrue(result.get(LocalDateTime.of(2024, 6, 28, 8, 0)));

        result.forEach((date, isOpen) -> {
            System.out.println(date + ": " + (isOpen ? "WORKED" : "CLOSED"));
        });
    }
}