package org.airretailer.constant.enums;

public enum CabinType {

    FIRST("First"),
    BUSINESS("Business"),
    ECONOMY("Economy");

    public final String label;

    CabinType(String label) {
        this.label = label;
    }

}