package org.airretailer.constant.enums;

public enum CommonStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETE("Delete");

    public final String label;

    CommonStatus(String label){
        this.label = label;
    }

    }