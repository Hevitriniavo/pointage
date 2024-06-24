package com.fresh.coding.modeles;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class Pointage {
    private Employe employe;
    private LocalTime debutHeuresTravaillee;
    private LocalTime finHeuresTravaillee;
    private Instant date;

    public double getHeuresTravaillee() {
        Duration hours = Duration.between(debutHeuresTravaillee, finHeuresTravaillee);
        int hoursValue = hours.toHoursPart();
        int minuteValue = hours.toMinutesPart();
        return hoursValue + (minuteValue / 60.0);
    }
}
