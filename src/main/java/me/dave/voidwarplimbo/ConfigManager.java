package me.dave.voidwarplimbo;

import com.loohp.limbo.location.Location;
import me.dave.voidwarplimbo.utils.YamlConfiguration;

public class ConfigManager {
    private final YamlConfiguration config = new YamlConfiguration();
    private Location spawnLocation;
    private double yMin;
    private double yMax;
    private String message;

    public ConfigManager() {
        config.saveDefaultConfig();

        reloadConfig();
    }

    public void reloadConfig() {
        config.reloadConfig();

        spawnLocation = VoidwarpLimbo.getInstance().getServer().getServerProperties().getWorldSpawn();
        yMin = config.getDouble("yMin", -Double.MAX_VALUE);
        yMax = config.getDouble("yMax", Double.MAX_VALUE);
        message = config.getString("message", "&7Teleported to &e%location%&7.");
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }

    public String getMessage() {
        return message;
    }
}
