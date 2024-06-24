package com.fresh.coding.modeles;

public class HeureMajoree {
    private String code;
    private Double percentage;
    private TimeEvent timeEvent;

    public enum TimeEvent {
        NIGHT,
        WEEKEND,
        CLOSED_DATE
    }
}
