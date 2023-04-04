package com.engtechnologie.microcredit.features.operation;

public enum OperationTypeEnum {

    DEPOSIT("DEPOT"),
    PAIEMENT("PAIEMENT"),
    FEES("FRAIS"),
    PENALITY("PENALITES");

    private String label;

    OperationTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
