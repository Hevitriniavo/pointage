package com.fresh.coding.modeles;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClosedDateTest {
    @Test
    void testIsClosed() {
        Set<JourFerier> closedDates = new HashSet<>();
        closedDates.add(new JourFerier(LocalDate.of(2024, 6, 17)));
        closedDates.add(new JourFerier(LocalDate.of(2024, 6, 25)));
        closedDates.add(new JourFerier(LocalDate.of(2024, 6, 26)));
        assertTrue(closedDates.contains(new JourFerier(LocalDate.of(2024, 6, 17))));
        assertFalse(closedDates.contains(new JourFerier(LocalDate.of(2024, 6, 3))));
    }
}