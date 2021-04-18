package com.vscarmena.space.service;

public enum Country {

    /* Source: https://countrycode.org/ */

    USA("United States"),
    ESP("Spain"),
    ITA("Italy"),
    RUS("Russia"),
    CHN("China");

    private String value;

    Country(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
