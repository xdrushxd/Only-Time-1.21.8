package com.onlytime;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class DayHUDMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            var config = AutoConfig.getConfigHolder(DaylightHUDConfig.class).getConfig();
            if (!config.dayCounterEnabled) return;

            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.world == null) return;

            long day = mc.world.getTimeOfDay() / 24000L + 1;
            int screenHeight = mc.getWindow().getScaledHeight();

            drawContext.drawTextWithShadow(
                    mc.textRenderer,
                    "Day: " + day,
                    12,
                    screenHeight - 15,
                    config.dayCounterColor.getRgb()
            );
        });
    }
}