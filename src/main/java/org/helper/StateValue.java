package org.helper;

//Todo: Пока не используется (не знаю, как применить)
public enum StateValue {
    NCR("NCR"),
    UTTAR_PRADESH("Uttar Pradesh"),
    HARYANA("Haryana"),
    RAJASTHAN("Rajasthan");

    private final String description;

    StateValue(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
