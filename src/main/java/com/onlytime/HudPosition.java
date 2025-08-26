package com.onlytime;

import net.minecraft.text.Text;

public enum HudPosition {
    TOP_LEFT(Text.literal("Top Left")),
    TOP_CENTER(Text.literal("Top Center")),
    TOP_RIGHT(Text.literal("Top Right")),
    LEFT_CENTER(Text.literal("Left Center")),
    RIGHT_CENTER(Text.literal("Right Center")),
    BOTTOM_LEFT(Text.literal("Bottom Left")),
    BOTTOM_CENTER(Text.literal("Bottom Center")),
    BOTTOM_RIGHT(Text.literal("Bottom Right"));

    private final Text displayName;

    HudPosition(Text displayName) {
        this.displayName = displayName;
    }

    public Text getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName.getString();
    }
}
