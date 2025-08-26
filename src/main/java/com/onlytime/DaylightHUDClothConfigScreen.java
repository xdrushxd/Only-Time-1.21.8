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

        daylight.addEntry(entryBuilder
                .startEnumSelector(Text.literal("Daylight Position"), HudPosition.class, config.daylightHudPosition)
                .setDefaultValue(HudPosition.TOP_LEFT)
                .setEnumNameProvider(value -> ((HudPosition) value).getDisplayName())
                .setSaveConsumer(newValue -> config.daylightHudPosition = newValue)
                .build());

        daylight.addEntry(entryBuilder
                .startIntSlider(Text.literal("Daylight X Offset"), -50, 50, config.daylightHudOffsetX)
                .setDefaultValue(5)
                .setSaveConsumer(newValue -> config.daylightHudOffsetX = newValue)
                .build());

        daylight.addEntry(entryBuilder
                .startIntSlider(Text.literal("Daylight Y Offset"), -50, 50, config.daylightHudOffsetY)
                .setDefaultValue(5)
                .setSaveConsumer(newValue -> config.daylightHudOffsetY = newValue)
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

        dayCounter.addEntry(entryBuilder
                .startEnumSelector(Text.literal("Counter Position"), HudPosition.class, config.dayCounterPosition)
                .setDefaultValue(HudPosition.BOTTOM_RIGHT)
                .setEnumNameProvider(value -> ((HudPosition) value).getDisplayName())
                .setSaveConsumer(newValue -> config.dayCounterPosition = newValue)
                .build());

        dayCounter.addEntry(entryBuilder
                .startIntSlider(Text.literal("Counter X Offset"), -50, 50, config.dayCounterOffsetX)
                .setDefaultValue(12)
                .setSaveConsumer(newValue -> config.dayCounterOffsetX = newValue)
                .build());

        dayCounter.addEntry(entryBuilder
                .startIntSlider(Text.literal("Counter Y Offset"), -50, 50, config.dayCounterOffsetY)
                .setDefaultValue(15)
                .setSaveConsumer(newValue -> config.dayCounterOffsetY = newValue)
                .build());

        // Debug Info Category
        ConfigCategory debug = builder.getOrCreateCategory(Text.literal("Debug Info"));
        
        debug.addEntry(entryBuilder
                .startTextDescription(Text.literal("Current HUD Positions:"))
                .build());
        
        debug.addEntry(entryBuilder
                .startTextDescription(Text.literal("Daylight HUD: " + config.daylightHudPosition + " (X:" + config.daylightHudOffsetX + ", Y:" + config.daylightHudOffsetY + ")"))
                .build());
        
        debug.addEntry(entryBuilder
                .startTextDescription(Text.literal("Day Counter: " + config.dayCounterPosition + " (X:" + config.dayCounterOffsetX + ", Y:" + config.dayCounterOffsetY + ")"))
                .build());
        
        debug.addEntry(entryBuilder
                .startTextDescription(Text.literal("Press G key to open this config screen"))
                .build());

        builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(DaylightHUDConfig.class).save());
        return builder.build();
    }
}