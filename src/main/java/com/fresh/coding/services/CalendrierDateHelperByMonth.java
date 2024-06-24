package com.fresh.coding.services;

import java.time.*;

public class CalendrierDateHelperByMonth implements CalendrierDateHelper {
    private final Month month;

    public CalendrierDateHelperByMonth(Month month) {
        this.month = month;
    }

    @Override
    public Instant getDebut() {
        LocalDate today = LocalDate.now();
        LocalDateTime result = LocalDateTime.of(today.getYear(), month.getValue(), 1, 8, 0);
        return result.atZone(ZoneId.systemDefault()).toInstant();
    }

    @Override
    public Instant getFin() {
        LocalDate today = LocalDate.now();
        LocalDateTime result = LocalDateTime.of(today.getYear(), month.getValue(), month.minLength(), 16, 0);
        return result.atZone(ZoneId.systemDefault()).toInstant();
    }
}
