package com.onlytime;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaylightHUDMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("daylight-hud-mod");

    private static boolean hudVisible = true;
    private static KeyBinding toggleKey;

    @Override
    public void onInitialize() {
        LOGGER.info("[DaylightHUDMod]: Mod initializing...");

        // Only run setupClient() if we are in a client environment
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            setupClient();
        }
    }

    private void setupClient() {
        LOGGER.info("[DaylightHUDMod]: Setting up client HUD toggle and renderer...");

        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.daylighttimer.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.daylighttimer"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                hudVisible = !hudVisible;
                if (client.player != null) {
                    client.player.sendMessage(Text.literal("Daylight HUD " + (hudVisible ? "Enabled" : "Disabled")), true);
                }
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (!hudVisible) return;

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null || client.player == null) return;

            long time = client.world.getTimeOfDay();
            String display = getHUDText(time);

            int x = 5;
            int y = 5;
            drawContext.drawText(client.textRenderer, display, x, y, 0xFFFFFF, true);
        });
    }

    private static String getHUDText(long timeOfDay) {
        long timeInDay = timeOfDay % 24000L;
        boolean isDay = timeInDay < 12000L;
        long ticksLeft = isDay ? (12000L - timeInDay) : (24000L - timeInDay);
        double minutesLeft = ticksLeft / 20.0 / 60.0;
        String phase = isDay ? "Daytime" : "Nighttime";
        return String.format("%s %.1f minutes left", phase, minutesLeft);
    }
}
