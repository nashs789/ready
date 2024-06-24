package com.example.ready.enums;

import java.util.HashMap;
import java.util.Map;

public enum Level {
    UNKNOWN(-1),
    QUESTION(0),
    BRONZE5(1),
    BRONZE4(2),
    BRONZE3(3),
    BRONZE2(4),
    BRONZE1(5),
    SILVER5(6),
    SILVER4(7),
    SILVER3(8),
    SILVER2(9),
    SILVER1(10),
    GOLD5(11),
    GOLD4(12),
    GOLD3(13),
    GOLD2(14),
    GOLD1(15),
    PLATINUM5(16),
    PLATINUM4(17),
    PLATINUM3(18),
    PLATINUM2(19),
    PLATINUM1(20),
    DIAMOND5(21),
    DIAMOND4(22),
    DIAMOND3(23),
    DIAMOND2(24),
    DIAMOND1(25),
    RUBY5(26),
    RUBY4(27),
    RUBY3(28),
    RUBY2(29),
    RUBY1(30);

    private int level;
    private static final Map<Integer, Level> LEVEL_MAP = new HashMap<>();

    static {
        for(Level level : Level.values()) {
            LEVEL_MAP.put(level.getLevel(), level);
        }
    }

    Level(int level) {
        this.level = level;
    }

    private int getLevel() {
        return level;
    }

    public static Level getLevelFromMap(int value) {
        return LEVEL_MAP.getOrDefault(value, UNKNOWN);
    }
}
