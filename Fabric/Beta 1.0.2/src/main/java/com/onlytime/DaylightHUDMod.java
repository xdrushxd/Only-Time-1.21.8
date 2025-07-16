package com.onlytime;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DaylightHUDMod implements ClientModInitializer {
    private static KeyBinding configKey;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(DaylightHUDConfig.class, me.shedaniel.autoconfig.serializer.GsonConfigSerializer::new);

        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.daylighthud.config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.daylighthud"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKey.wasPressed()) {
                client.setScreen(DaylightHUDClothConfigScreen.create(client.currentScreen));
            }
        });

        HudRenderCallback.EVENT.register((ctx, tickDelta) -> {
            var config = AutoConfig.getConfigHolder(DaylightHUDConfig.class).getConfig();
            if (!config.daylightHudEnabled) return;

            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.world == null || mc.player == null) return;

            long time = mc.world.getTimeOfDay();
            long timeInDay = time % 24000L;
            boolean isDay = timeInDay < 12000L;
            long ticksLeft = isDay ? (12000L - timeInDay) : (24000L - timeInDay);
            double minutesLeft = ticksLeft / 20.0 / 60.0;
            String phase = isDay ? "Daytime ☀" : "Nighttime ☾";
            String display = String.format("%s %.1f minutes left", phase, minutesLeft);

            ctx.drawTextWithShadow(
                    mc.textRenderer,
                    display,
                    5,
                    5,
                    config.daylightTextColor.getRgb()
            );
        });
    }
}