package com.onlytime;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "onlytime")
public class DaylightHUDConfig implements ConfigData {

    // Daylight HUD settings
    @ConfigEntry.Gui.Tooltip
    public boolean daylightHudEnabled = true;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.DROPDOWN)
    public HudPosition daylightHudPosition = HudPosition.TOP_RIGHT;

    @ConfigEntry.Gui.Tooltip
    public int daylightHudOffsetX = 5;

    @ConfigEntry.Gui.Tooltip
    public int daylightHudOffsetY = 5;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.DROPDOWN)
    public HudColor daylightTextColor = HudColor.WHITE;

    @ConfigEntry.Gui.Tooltip
    public boolean showSeconds = false;

    // Day Counter settings
    @ConfigEntry.Gui.Tooltip
    public boolean dayCounterEnabled = true;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.DROPDOWN)
    public HudPosition dayCounterPosition = HudPosition.BOTTOM_RIGHT;

    @ConfigEntry.Gui.Tooltip
    public int dayCounterOffsetX = 12;

    @ConfigEntry.Gui.Tooltip
    public int dayCounterOffsetY = 15;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.DROPDOWN)
    public HudColor dayCounterColor = HudColor.WHITE;

    // General settings
    @ConfigEntry.Gui.Tooltip
    public boolean showDebugInfo = false;

    @ConfigEntry.BoundedDiscrete(min = 50, max = 200)
    public int hudScale = 100; // Percentage

    @ConfigEntry.Gui.Tooltip
    public boolean showBackground = false;
}
