package com.onlytime;

import net.minecraft.client.MinecraftClient;

public class HudPositionCalculator {

    public static int[] calculatePosition(HudPosition position, int offsetX, int offsetY, String text) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.getWindow() == null) {
            return new int[]{5, 5}; // fallback
        }

        int screenWidth = mc.getWindow().getScaledWidth();
        int screenHeight = mc.getWindow().getScaledHeight();

        int textWidth = mc.textRenderer.getWidth(text);
        int textHeight = mc.textRenderer.fontHeight;

        int x = offsetX;
        int y = offsetY;

        switch (position) {
            case TOP_LEFT:
                x = offsetX;
                y = offsetY;
                break;

            case TOP_CENTER:
                x = (screenWidth - textWidth) / 2 + offsetX;
                y = offsetY;
                break;

            case TOP_RIGHT:
                x = screenWidth - textWidth - offsetX;
                y = offsetY;
                break;

            case LEFT_CENTER:
                x = offsetX;
                y = (screenHeight - textHeight) / 2 + offsetY;
                break;

            case RIGHT_CENTER:
                x = screenWidth - textWidth - offsetX;
                y = (screenHeight - textHeight) / 2 + offsetY;
                break;

            case BOTTOM_LEFT:
                x = offsetX;
                y = screenHeight - textHeight - offsetY;
                break;

            case BOTTOM_CENTER:
                x = (screenWidth - textWidth) / 2 + offsetX;
                y = screenHeight - textHeight - offsetY;
                break;

            case BOTTOM_RIGHT:
                x = screenWidth - textWidth - offsetX;
                y = screenHeight - textHeight - offsetY;
                break;
        }

        // Clamp to screen bounds
        x = Math.max(0, Math.min(x, screenWidth - textWidth));
        y = Math.max(0, Math.min(y, screenHeight - textHeight));

        return new int[]{x, y};
    }
}
