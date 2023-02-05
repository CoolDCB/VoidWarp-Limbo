package me.dave.voidwarplimbo;

import com.loohp.limbo.plugins.LimboPlugin;

public final class VoidwarpLimbo extends LimboPlugin {
    private static VoidwarpLimbo plugin;
    public static ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;

        configManager = new ConfigManager();

        getServer().getEventsManager().registerEvents(this, new PlayerEvents());
    }

    public static VoidwarpLimbo getInstance() {
        return plugin;
    }
}
