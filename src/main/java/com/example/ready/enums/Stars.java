package com.example.ready.enums;

public enum Stars {
    ONE("One"),
    TWO("Two"),
    THREE("Three"),
    FOUR("Four"),
    FIVE("Five");

    Stars(String star) {
        this.star = star;
    }

    private String star;

    public static Stars getStars(String star) {
        for(Stars s : Stars.values()) {
            if(s.getStar().equals(star)) {
                return s;
            }
        }

        throw new IllegalArgumentException("No Such a Matched Enum with" + star);
    }

    public String getStar() {
        return star;
    }
}
