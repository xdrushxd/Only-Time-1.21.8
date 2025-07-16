package com.onlytime;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "daylighthud")
public class DaylightHUDConfig implements ConfigData {
    // Daylight HUD settings
    public boolean daylightHudEnabled = true;
    public HudColor daylightTextColor = HudColor.WHITE;

    // Day Counter settings
    public boolean dayCounterEnabled = true;
    public HudColor dayCounterColor = HudColor.WHITE;
}