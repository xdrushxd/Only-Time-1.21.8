package com.onlytime;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DaylightHUDClothConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("HUD Settings"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // Daylight HUD Category
        ConfigCategory daylight = builder.getOrCreateCategory(Text.literal("Daylight HUD"));
        var config = AutoConfig.getConfigHolder(DaylightHUDConfig.class).getConfig();

        daylight.addEntry(entryBuilder
                .startBooleanToggle(Text.literal("Enable Daylight HUD"), config.daylightHudEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> config.daylightHudEnabled = newValue)
                .build());

        daylight.addEntry(entryBuilder
                .startEnumSelector(Text.literal("Daylight Color"), HudColor.class, config.daylightTextColor)
                .setDefaultValue(HudColor.WHITE)
                .setEnumNameProvider(value -> ((HudColor) value).getDisplayName())
                .setSaveConsumer(newValue -> config.daylightTextColor = newValue)
                .build());

        // Day Counter Category
        ConfigCategory dayCounter = builder.getOrCreateCategory(Text.literal("Day Counter"));

        dayCounter.addEntry(entryBuilder
                .startBooleanToggle(Text.literal("Enable Day Counter"), config.dayCounterEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> config.dayCounterEnabled = newValue)
                .build());

        dayCounter.addEntry(entryBuilder
                .startEnumSelector(Text.literal("Counter Color"), HudColor.class, config.dayCounterColor)
                .setDefaultValue(HudColor.WHITE)
                .setEnumNameProvider(value -> ((HudColor) value).getDisplayName())
                .setSaveConsumer(newValue -> config.dayCounterColor = newValue)
                .build());

        builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(DaylightHUDConfig.class).save());
        return builder.build();
    }
}