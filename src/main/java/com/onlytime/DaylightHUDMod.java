package com.onlytime;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DaylightHUDMod implements ClientModInitializer {
    private static KeyBinding toggleKey;
    private static KeyBinding configKey;
    private static boolean hudEnabled = true;

    @Override
    public void onInitializeClient() {
        System.out.println("DaylightHUDMod: Initializing...");
        
        try {
            // Register configuration
            AutoConfig.register(DaylightHUDConfig.class, me.shedaniel.autoconfig.serializer.GsonConfigSerializer::new);
            System.out.println("DaylightHUDMod: Configuration registered");

            // Register toggle key (G key)
            toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    "key.onlytime.toggle",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_G,
                    "category.onlytime"
            ));
            System.out.println("DaylightHUDMod: Toggle key registered");

            // Register config key (H key)
            configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    "key.onlytime.config",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_H,
                    "category.onlytime"
            ));
            System.out.println("DaylightHUDMod: Config key registered");

            // Register HUD render callback
            HudRenderCallback.EVENT.register((ctx, tickDelta) -> {
                if (!hudEnabled) return;

                try {
                    var config = AutoConfig.getConfigHolder(DaylightHUDConfig.class).getConfig();
                    if (!config.daylightHudEnabled) return;

                    MinecraftClient mc = MinecraftClient.getInstance();
                    if (mc == null || mc.world == null || mc.player == null) return;

                    // Always show some text to verify rendering works
                    String display = "Time HUD Loading...";
                    
                    // Try to get actual time if world is available
                    try {
                        long time = mc.world.getTimeOfDay();
                        long timeInDay = time % 24000L;
                        boolean isDay = timeInDay < 12000L;
                        long ticksLeft = isDay ? (12000L - timeInDay) : (24000L - timeInDay);
                        
                        double minutesLeft = ticksLeft / 20.0 / 60.0;
                        String phase = isDay ? "Daytime ☀" : "Nighttime ☁";
                        display = String.format("%s %.1f minutes left", phase, minutesLeft);
                        
                        if (config.showSeconds) {
                            double secondsLeft = (ticksLeft / 20.0) % 60.0;
                            display = String.format("%s %.1f minutes %.0f seconds left", phase, minutesLeft, secondsLeft);
                        }
                        
                        // Debug: Print time info to console
                        System.out.println("DaylightHUDMod: Time calculation - Total: " + time + 
                                         ", InDay: " + timeInDay + 
                                         ", IsDay: " + isDay + 
                                         ", TicksLeft: " + ticksLeft + 
                                         ", MinutesLeft: " + minutesLeft);
                        System.out.println("DaylightHUDMod: Display text: " + display);
                        
                    } catch (Exception e) {
                        display = "Time: Error getting time";
                        System.err.println("DaylightHUDMod: Error getting time: " + e.getMessage());
                        e.printStackTrace();
                    }

                    // Calculate position based on configuration
                    int[] position = HudPositionCalculator.calculatePosition(
                        config.daylightHudPosition, 
                        config.daylightHudOffsetX, 
                        config.daylightHudOffsetY, 
                        display
                    );
                    
                    // Debug: Print position info
                    System.out.println("DaylightHUDMod: Position calculated - X: " + position[0] + ", Y: " + position[1]);

                    // Draw background if enabled
                    if (config.showBackground) {
                        int bgColor = 0x80000000; // Semi-transparent black
                        ctx.fill(position[0] - 2, position[1] - 2, 
                                position[0] + mc.textRenderer.getWidth(display) + 2, 
                                position[1] + 10, bgColor);
                        System.out.println("DaylightHUDMod: Background drawn at " + position[0] + "," + position[1]);
                    }

                    // MAIN TIME DISPLAY: Use calculated position or fallback to forced top-right
                    try {
                        int textX, textY;
                        
                        // Try to use calculated position first
                        if (position[0] > 0 && position[1] > 0) {
                            textX = position[0];
                            textY = position[1];
                            System.out.println("DaylightHUDMod: Using calculated position: " + textX + "," + textY);
                        } else {
                            // Fallback to forced top-right corner
                            int screenWidth = mc.getWindow().getScaledWidth();
                            textX = screenWidth - mc.textRenderer.getWidth(display) - 10; // 10 px from right
                            textY = 10; // 10 px from top
                            System.out.println("DaylightHUDMod: Using forced top-right position: " + textX + "," + textY);
                        }
                        
                        // Ensure text color contrasts with background and is opaque
                        int textColor = config.daylightTextColor.getRgb();
                        
                        // If background is enabled, force white text for contrast
                        if (config.showBackground) {
                            textColor = 0xFFFFFFFF; // Fully opaque white (ARGB: alpha=255, R=255, G=255, B=255)
                        }
                        // Colors from enum are now properly opaque, so no need to force alpha
                        
                        // Log the color for debugging
                        System.out.println("Text color int: " + Integer.toHexString(textColor) + " (ARGB format)");
                        System.out.println("User configured color: " + Integer.toHexString(config.daylightTextColor.getRgb()) + " (ARGB format)");
                        
                        // Draw the text ON TOP of the background with shadow
                        try {
                            ctx.drawTextWithShadow(
                                    mc.textRenderer,
                                    display,
                                    textX,
                                    textY,
                                    textColor
                            );
                            System.out.println("DaylightHUDMod: MAIN TIME DISPLAY drawn with drawTextWithShadow at: " + textX + "," + textY);
                        } catch (Exception e) {
                            System.err.println("DaylightHUDMod: drawTextWithShadow failed: " + e.getMessage());
                            e.printStackTrace();
                        }
                        
                    } catch (Exception e) {
                        System.err.println("DaylightHUDMod: MAIN TIME DISPLAY failed: " + e.getMessage());
                        e.printStackTrace();
                    }
                    
                } catch (Exception e) {
                    System.err.println("DaylightHUDMod: Error in HUD rendering: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            System.out.println("DaylightHUDMod: HUD callback registered");

            // Register tick event to handle key presses
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                try {
                    // Handle toggle key
                    while (toggleKey.wasPressed()) {
                        hudEnabled = !hudEnabled;
                        if (hudEnabled) {
                            client.player.sendMessage(net.minecraft.text.Text.literal("§aOnly Time HUD enabled!"), false);
                            System.out.println("DaylightHUDMod: HUD enabled");
                        } else {
                            client.player.sendMessage(net.minecraft.text.Text.literal("§cOnly Time HUD disabled!"), false);
                            System.out.println("DaylightHUDMod: HUD disabled");
                        }
                    }
                    
                    // Handle config key
                    while (configKey.wasPressed()) {
                        client.setScreen(AutoConfig.getConfigScreen(DaylightHUDConfig.class, client.currentScreen).get());
                    }
                    
                } catch (Exception e) {
                    System.err.println("DaylightHUDMod: Error in tick event: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            System.out.println("DaylightHUDMod: Tick event registered");
            
            System.out.println("DaylightHUDMod: Initialization complete!");
            
        } catch (Exception e) {
            System.err.println("DaylightHUDMod: Error during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
}