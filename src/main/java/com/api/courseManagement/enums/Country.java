package com.api.courseManagement.enums;

public enum Country {
    BRAZIL("Brasil"),
    USA("Estados Unidos"),
    GERMANY("Alemanha"),
    ENGLAND("Inglaterra"),
    ITALY("Itália");

    private String name;

    Country (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
