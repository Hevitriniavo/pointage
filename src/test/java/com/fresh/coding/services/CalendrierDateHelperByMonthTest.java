package com.fresh.coding.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class CalendrierDateHelperByMonthTest {
    private CalendrierDateHelper calendrierDateHelper;

    @BeforeEach
    void setUp() {
        calendrierDateHelper = new CalendrierDateHelperByMonth(Month.JUNE);
    }

    @Test
    void getDebut() {
        assertEquals(LocalDateTime.of(2024, 6, 1, 8, 0).atZone(ZoneId.systemDefault()).toInstant(), calendrierDateHelper.getDebut());
    }

    @Test
    void getFin() {
        assertEquals(LocalDateTime.of(2024, 6, 30, 16, 0).atZone(ZoneId.systemDefault()).toInstant(), calendrierDateHelper.getFin());
    }
}