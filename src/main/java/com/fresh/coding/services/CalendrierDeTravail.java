package com.fresh.coding.services;

import com.fresh.coding.modeles.JourFerier;
import com.fresh.coding.repositories.CategorieRepository;
import com.fresh.coding.repositories.JourFerierRepository;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CalendrierDeTravail {
    private final CalendrierDateHelper calendrierDateHelper;
    private final JourFerierRepository jourFerierRepository;
    private final CategorieRepository categorieRepository;

    public CalendrierDeTravail(CalendrierDateHelper calendrierDateHelper, JourFerierRepository jourFerierRepository, CategorieRepository categorieRepository) {
        this.calendrierDateHelper = calendrierDateHelper;
        this.jourFerierRepository = jourFerierRepository;
        this.categorieRepository = categorieRepository;
    }

    public Map<LocalDateTime, Boolean> getCalendrier() {
        Map<LocalDateTime, Boolean> result = new TreeMap<>();

        int maxWorkedHours = getMaxWorkedHours();
        Instant debutCalendier = getDebutCalendrier();
        Instant finCalendier = getFinCalendrier();

        Set<JourFerier> closedDates = getClosedDates(debutCalendier, finCalendier);

        boolean isOpenSunday = maxWorkedHours > 48;
        boolean isOpenSaturday = maxWorkedHours > 40;

        for (; finCalendier.compareTo(debutCalendier) >= 0; debutCalendier = debutCalendier.plus(1, ChronoUnit.DAYS)) {
            LocalDate debutDate = debutCalendier.atZone(ZoneId.systemDefault()).toLocalDate();
            boolean isClosed = closedDates.contains(new JourFerier(debutDate));
            if (!isClosed) {
                if (!isOpenSunday && debutDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    continue;
                }
                if (!isOpenSaturday && debutDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    continue;
                }
            }
            result.put(debutCalendier.atZone(ZoneId.systemDefault()).toLocalDateTime(), !isClosed);
        }

        return result;
    }

    protected Set<JourFerier> getClosedDates(Instant debut, Instant fin) {
        return jourFerierRepository.getAllClosedDates(debut, fin);
    }

    protected Instant getDebutCalendrier() {
        return calendrierDateHelper.getDebut();
    }

    protected Instant getFinCalendrier() {
        return calendrierDateHelper.getFin();
    }

    protected int getMaxWorkedHours() {
        return categorieRepository.getMaxWorkedHours();
    }
}
