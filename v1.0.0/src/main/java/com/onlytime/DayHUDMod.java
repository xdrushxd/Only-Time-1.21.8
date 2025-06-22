package com.onlytime;


import net.minecraft.text.Text;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayHUDMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("day-countr");

    private static boolean hudVisible = true;
    private static KeyBinding toggleKey;

    @Override
    public void onInitialize() {
        LOGGER.info("[DayCountr]: Starting up");

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            setupClient();
        }
    }

    private void setupClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.daycountr.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.daycountr"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                hudVisible = !hudVisible;
                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal("Day Counter " + (hudVisible ? "Enabled" : "Disabled")),
                            true
                    );
                }
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (!hudVisible) return;

            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.world == null) return;

            long day = mc.world.getTimeOfDay() / 24000L + 1;

            int screenHeight = mc.getWindow().getScaledHeight();
            int x = 12;
            int y = screenHeight - 15;

            drawContext.drawTextWithShadow(mc.textRenderer, "Day: " + day, x, y, 0xFFFFFF);
        });

        LOGGER.info("[DayCountr]: Client setup complete");
    }
}

