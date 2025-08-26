package com.onlytime;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class DayHUDMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("DayHUDMod: Initializing...");
        
        HudRenderCallback.EVENT.register((ctx, tickDelta) -> {
            try {
                var config = AutoConfig.getConfigHolder(DaylightHUDConfig.class).getConfig();
                if (!config.dayCounterEnabled) return;

                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player == null || mc.world == null) return;

                long day = mc.world.getTimeOfDay() / 24000L + 1;
                String display = "Day: " + day;

                // Calculate position based on configuration
                int[] position = HudPositionCalculator.calculatePosition(
                    config.dayCounterPosition, 
                    config.dayCounterOffsetX, 
                    config.dayCounterOffsetY, 
                    display
                );

                // Draw background if enabled
                if (config.showBackground) {
                    int bgColor = 0x80000000; // Semi-transparent black
                    ctx.fill(position[0] - 2, position[1] - 2, 
                            position[0] + mc.textRenderer.getWidth(display) + 2, 
                            position[1] + 10, bgColor);
                    System.out.println("DayHUDMod: Background drawn at " + position[0] + "," + position[1]);
                }

                // Ensure day counter color is opaque and contrasts with background
                int dayTextColor = config.dayCounterColor.getRgb();
                
                // If background is enabled, force white text for contrast
                if (config.showBackground) {
                    dayTextColor = 0xFFFFFFFF; // Fully opaque white (ARGB: alpha=255, R=255, G=255, B=255)
                }
                // Colors from enum are now properly opaque, so no need to force alpha
                
                // Log the color for debugging
                System.out.println("Day counter color int: " + Integer.toHexString(dayTextColor) + " (ARGB format)");
                System.out.println("User configured day color: " + Integer.toHexString(config.dayCounterColor.getRgb()) + " (ARGB format)");

                // Draw the day counter with shadow for better visibility
                ctx.drawTextWithShadow(
                        mc.textRenderer,
                        display,
                        position[0],
                        position[1],
                        dayTextColor
                );
                System.out.println("DayHUDMod: Day counter drawn at " + position[0] + "," + position[1] + " with color: " + Integer.toHexString(dayTextColor));
                
                // Draw debug info if enabled
                if (config.showDebugInfo) {
                    ctx.drawTextWithShadow(
                            mc.textRenderer,
                            "Day Counter Working!",
                            position[0] + 5,
                            position[1] + 15,
                            0x55FF55
                    );
                }
                
            } catch (Exception e) {
                System.err.println("DayHUDMod: Error in HUD rendering: " + e.getMessage());
                e.printStackTrace();
            }
        });
        
        System.out.println("DayHUDMod: Initialization complete!");
    }
}