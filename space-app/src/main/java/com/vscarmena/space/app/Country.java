package com.vscarmena.space.app;

public enum Country {
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
