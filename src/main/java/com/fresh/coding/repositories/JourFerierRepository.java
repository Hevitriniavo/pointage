package com.fresh.coding.repositories;

import com.fresh.coding.modeles.JourFerier;

import java.time.Instant;
import java.util.Set;

public interface JourFerierRepository {
    Set<JourFerier> getAllClosedDates(Instant debut, Instant fin);
}
