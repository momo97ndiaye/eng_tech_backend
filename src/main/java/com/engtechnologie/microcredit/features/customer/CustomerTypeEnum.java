package com.engtechnologie.microcredit.features.customer;

public enum CustomerTypeEnum {

    MOBILE("MOBILE"),
    WEB("WEB");

    private String label;

    CustomerTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
