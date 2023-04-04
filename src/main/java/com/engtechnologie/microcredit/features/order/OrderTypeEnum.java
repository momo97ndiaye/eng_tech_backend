package com.engtechnologie.microcredit.features.order;

public enum OrderTypeEnum {

    CONSOMMATION("CONSOMMATION"),
    CREDIT("CREDIT"),
    EQUIPEMENT("EQUIPEMENT"),
    AUTRE("AUTRE");

    private String label;

    OrderTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
