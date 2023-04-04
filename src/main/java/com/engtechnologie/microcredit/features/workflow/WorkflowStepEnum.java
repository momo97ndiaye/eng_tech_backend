package com.engtechnologie.microcredit.features.workflow;

public enum WorkflowStepEnum {

    CREATED("Demande de crédit Créée"),
    OFFER_SENT("Offre envoyée"),
    ACCEPTED("Offre acceptée"),
    REFUSED("Offre refusée"),
    CANCELED("Demande de crédit annulée"),
    VALIDATED("Demande de crédit validée");

    private String label;

    WorkflowStepEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
