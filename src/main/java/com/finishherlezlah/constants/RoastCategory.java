package com.finishherlezlah.constants;

import java.util.Arrays;

public enum RoastCategory {
    LEZLAHSAYS,
    FOREHEAD,
    LIKETOHATE,
    FAKEQUEEN,
    WANNABESTRAIGHT,
    LOSERBRAIDKNUCKS;

    public static boolean isValid(String category) {
        return Arrays.stream(values())
                .anyMatch(c -> c.name().equalsIgnoreCase(category));
    }
}
