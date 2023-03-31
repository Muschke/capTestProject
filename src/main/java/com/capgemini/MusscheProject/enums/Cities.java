package com.capgemini.MusscheProject.enums;

public enum Cities {
    BRUSSELS, ANTWERP, BRUGES, PARIS, LONDON, ROME, BERLIN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
