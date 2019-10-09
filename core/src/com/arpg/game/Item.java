package com.arpg.game;

public interface Item {
    Type getItemType();

    String getTitle();

    boolean isUsable();

    boolean isWearable();

    enum Type {
        POTION, WEAPON, ARMOR
    }
}
