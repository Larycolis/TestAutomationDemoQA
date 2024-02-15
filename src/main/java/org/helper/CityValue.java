package org.helper;

//Todo: Пока не используется (не знаю, как применить)
public enum CityValue {
    DELHI("Delhi"),
    GURGAON("Gurgaon"),
    NOIDA("Noida"),
    AGRA("Agra"),
    LUCKNOW("Lucknow"),
    MERRUT("Merrut"),
    KARNAL("Karnal"),
    PANIPAT("Panipat"),
    JAIPUR("Jaipur"),
    JAISELMER("Jaiselmer");
    private final String description;

    CityValue(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
