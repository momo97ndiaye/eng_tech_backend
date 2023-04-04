package com.engtechnologie.microcredit.features.user;


public enum Role {

    AGENT("AGENT"),
    ADMIN("ADMIN"),

    MOBILE("MOBILE");


    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
